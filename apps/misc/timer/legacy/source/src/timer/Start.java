package timer;

import gui.Oberflaeche;

import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class Start {

	public static Oberflaeche oberflaeche;
	public static Timer timer;

	public static void main(String[] args) {
		oberflaeche = new Oberflaeche();
		// Neuen Timer erstellen
		oberflaeche.newTimer();
		// Neuen echten Timer erstellen
		timer = new Timer();
		// MouseAdapter für Starten
		MouseAdapter maStartTimer = new MouseAdapter() {
			@Override
			public void mouseReleased(MouseEvent e) {
				String text = oberflaeche.getBtnText();
				if (text == "Start"){
				startTimer();
				// Schriftzug ändern
				oberflaeche.setBtnText("Stop");
				// Feld ausgrauen
				oberflaeche.txtMinutes.setEditable(false);
				}
				else{
					// Timer beenden
					timer.cancel();
					// Schriftzug ändern
					oberflaeche.setBtnText("Start");
					// Feld entgrauen
					oberflaeche.txtMinutes.setEditable(true);
					// Feld leeren
					oberflaeche.txtMinutes.setText("");
				}
			}
		};
		oberflaeche.setListenerStartTimer(maStartTimer);
	}

	public static void startTimer() {
		// Zeit rückwärts ablaufen lassen
		timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() {
			int min = Integer.parseInt(Oberflaeche.getMinutes());
			int sec = min * 60;
			public void run() {
				String time = getDurationString(sec);
				oberflaeche.setTxtText(time);
				sec--;
				if (sec < 0) {
					String plural = "";
					if (min == 1){
						plural = " minute is";
					}
					else{
						plural = " minutes are";
					}
					timer.cancel();
					msgbox("The " + min + plural + " over!");
					// Schriftzug ändern
					oberflaeche.setBtnText("Start");
					// Feld entgrauen
					oberflaeche.txtMinutes.setEditable(true);
					// Feld leeren
					oberflaeche.txtMinutes.setText("");
				}
			}
		}, 0, 1000);
	}
	
	private static String getDurationString(int seconds) {
	    int hours = seconds / 3600;
	    int minutes = (seconds % 3600) / 60;
	    seconds = seconds % 60;
	    return twoDigitString(hours) + " : " + twoDigitString(minutes) + " : " + twoDigitString(seconds);
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
		JOptionPane.showMessageDialog(null, message,"Time over!", JOptionPane.INFORMATION_MESSAGE);
		JOptionPane.getRootFrame().toFront();
		JOptionPane.getRootFrame().repaint();
	}

}
