package arbeitszeit;

import java.time.Duration;
import java.time.LocalTime;

/** Pure working-time arithmetic (weekly hours are spread over 5 days). */
public final class WorkTime {
    private WorkTime() {}

    public static int dailyMinutes(int weeklyHours) {
        return weeklyHours * 60 / 5;
    }

    /** Statutory break: 30 min from 6 h, 45 min from 9 h of work per day. */
    public static int breakMinutes(int dailyMinutes) {
        if (dailyMinutes >= 540) {
            return 45;
        }
        return dailyMinutes >= 360 ? 30 : 0;
    }

    public static LocalTime leaveTime(LocalTime arrival, int weeklyHours) {
        int daily = dailyMinutes(weeklyHours);
        return arrival.plusMinutes(daily + breakMinutes(daily));
    }

    /** Positive while the target time is still ahead of now, negative or zero afterwards. */
    public static Duration remaining(LocalTime now, LocalTime target) {
        return Duration.between(now.withSecond(0).withNano(0), target);
    }

    /** "hh:mm Std", always unsigned like the legacy label. */
    public static String format(Duration d) {
        Duration abs = d.abs();
        return "%02d:%02d Std".formatted(abs.toHours(), abs.toMinutesPart());
    }
}
