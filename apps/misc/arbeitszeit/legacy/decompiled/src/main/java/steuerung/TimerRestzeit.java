/*
 * Decompiled with CFR 0.152.
 */
package steuerung;

import gui.Hauptseite;
import java.util.Timer;
import java.util.TimerTask;
import steuerung.Hauptsteuerung;

public class TimerRestzeit
extends TimerTask {
    public static Timer timer;
    public static Hauptsteuerung hauptsteuerung;
    public static Hauptseite hauptseite;
    public static boolean isRunning;
    public static long REPEAT;

    static {
        isRunning = false;
        REPEAT = 0L;
    }

    @Override
    public void run() {
    }

    public TimerRestzeit() {
        if (isRunning) {
            this.TimerStop();
        }
        REPEAT = Hauptsteuerung.getRefresh();
        timer = new Timer();
        timer.schedule(new TimerTask(){

            @Override
            public void run() {
                Hauptsteuerung.createRestzeit();
            }
        }, 0L, REPEAT);
        isRunning = true;
    }

    public void TimerStop() {
        timer.cancel();
        isRunning = false;
    }
}

