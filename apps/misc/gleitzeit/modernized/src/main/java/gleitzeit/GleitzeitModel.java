package gleitzeit;

import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/** Pure flextime arithmetic of GZR v3.5 (target 7:48 net, 30 min break after 6 h, 15 min after 9:30 h). */
public final class GleitzeitModel {
    public static final LocalTime EARLIEST = LocalTime.of(6, 30);
    public static final LocalTime CORE_START = LocalTime.of(9, 0);
    public static final LocalTime LATEST_END = LocalTime.of(19, 30);
    private static final int TARGET = 7 * 60 + 48;

    /** Pause2 is null if it would end after {@link #LATEST_END}. Saldo/worked in minutes. */
    public record Result(LocalTime arrival, LocalTime leave, LocalTime maxEnd,
                         LocalTime pause1, LocalTime pause2, long worked, long saldo,
                         int percentTarget, int percentOvertime, List<String> warnings) {}

    private GleitzeitModel() {}

    public static Result compute(LocalTime input, LocalTime now) {
        List<String> w = new ArrayList<>();
        LocalTime arrival = input;
        if (input.isBefore(EARLIEST)) {
            arrival = EARLIEST;
            w.add("Kommenzeit zu früh, es wird ab 06:30 gerechnet!");
        }
        if (arrival.isAfter(CORE_START)) {
            w.add("Kommenzeit nach Beginn der Kernzeit (09:00)!");
        }
        LocalTime leave = arrival.plusMinutes(TARGET + 30);
        LocalTime maxEnd = arrival.plusHours(10).plusMinutes(45);
        if (maxEnd.isAfter(LATEST_END) || maxEnd.isBefore(arrival)) {
            maxEnd = LATEST_END;
            w.add("Maximale Arbeitszeit auf 19:30 begrenzt!");
        }
        LocalTime p1 = arrival.plusHours(6);
        LocalTime p2 = arrival.plusHours(9).plusMinutes(30);
        if (p2.plusMinutes(15).isAfter(LATEST_END) || p2.isBefore(arrival)) {
            p2 = null;
            w.add("Pause 2 nicht mehr möglich (N/A)!");
        }
        long worked = 0;
        if (now.isBefore(arrival)) {
            w.add("Kommenzeit liegt in der Zukunft!");
        } else {
            LocalTime end = now.isAfter(maxEnd) ? maxEnd : now;
            worked = Duration.between(arrival, end).toMinutes() - overlap(arrival, end, p1, 30);
            if (p2 != null) {
                worked -= overlap(arrival, end, p2, 15);
            }
        }
        long saldo = now.isBefore(arrival) ? -TARGET : worked - TARGET;
        int pt = (int) Math.min(100, worked * 100 / TARGET);
        int po = (int) Math.max(0, Math.min(100, (worked - TARGET) * 100 / Math.max(1, Duration.between(leave, maxEnd).toMinutes())));
        return new Result(arrival, leave, maxEnd, p1, p2, worked, saldo, pt, po, w);
    }

    /** Minutes of the break [start, start+len) that lie within [from, to). */
    private static long overlap(LocalTime from, LocalTime to, LocalTime start, int len) {
        LocalTime s = start.isBefore(from) ? from : start;
        LocalTime e = start.plusMinutes(len).isAfter(to) ? to : start.plusMinutes(len);
        return Math.max(0, Duration.between(s, e).toMinutes());
    }

    /** "+hh:mm" / "-hh:mm". */
    public static String formatSaldo(long minutes) {
        return "%s%02d:%02d".formatted(minutes < 0 ? "-" : "+", Math.abs(minutes) / 60, Math.abs(minutes) % 60);
    }

    /** Pay for the time worked: net monthly salary / 168.48 h. */
    public static double pay(int netto, long workedMinutes) {
        return netto / 168.48 * workedMinutes / 60.0;
    }
}
