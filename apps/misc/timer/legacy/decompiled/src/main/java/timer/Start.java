/*
 * Decompiled with CFR 0.152.
 */
package timer;

import gui.Oberflaeche;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JOptionPane;

public class Start {
    public static Oberflaeche oberflaeche;
    public static Timer timer;

    public static void main(String[] args) {
        oberflaeche = new Oberflaeche();
        oberflaeche.newTimer();
        timer = new Timer();
        MouseAdapter maStartTimer = new MouseAdapter(){

            @Override
            public void mouseReleased(MouseEvent e) {
                String text = Oberflaeche.getBtnText();
                if (text == "Start") {
                    Start.startTimer();
                    Oberflaeche.setBtnText("Stop");
                    Oberflaeche.txtMinutes.setEditable(false);
                } else {
                    timer.cancel();
                    Oberflaeche.setBtnText("Start");
                    Oberflaeche.txtMinutes.setEditable(true);
                    Oberflaeche.txtMinutes.setText("");
                }
            }
        };
        oberflaeche.setListenerStartTimer(maStartTimer);
    }

    public static void startTimer() {
        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask(){
            int min = Integer.parseInt(Oberflaeche.getMinutes());
            int sec = this.min * 60;

            @Override
            public void run() {
                String time = Start.getDurationString(this.sec);
                Oberflaeche.setTxtText(time);
                --this.sec;
                if (this.sec < 0) {
                    String plural = "";
                    plural = this.min == 1 ? " minute is" : " minutes are";
                    timer.cancel();
                    Start.msgbox("The " + this.min + plural + " over!");
                    Oberflaeche.setBtnText("Start");
                    Oberflaeche.txtMinutes.setEditable(true);
                    Oberflaeche.txtMinutes.setText("");
                }
            }
        }, 0L, 1000L);
    }

    private static String getDurationString(int seconds) {
        int hours = seconds / 3600;
        int minutes = seconds % 3600 / 60;
        return String.valueOf(Start.twoDigitString(hours)) + " : " + Start.twoDigitString(minutes) + " : " + Start.twoDigitString(seconds %= 60);
    }

    private static String twoDigitString(int number) {
        if (number == 0) {
            return "00";
        }
        if (number / 10 == 0) {
            return "0" + number;
        }
        return String.valueOf(number);
    }

    public static void msgbox(String message) {
        JOptionPane.showMessageDialog(null, message, "Time over!", 1);
        JOptionPane.getRootFrame().toFront();
        JOptionPane.getRootFrame().repaint();
    }
}

