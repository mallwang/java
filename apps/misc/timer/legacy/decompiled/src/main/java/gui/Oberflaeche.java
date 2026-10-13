/*
 * Decompiled with CFR 0.152.
 */
package gui;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Oberflaeche {
    public static JFrame frame;
    public static JPanel panel;
    public static JLabel lblDauer;
    public static JButton btnStarten;
    public static JTextField txtMinutes;
    public static JLabel lblEnterMinutes;

    public void newTimer() {
        this.initialize();
    }

    private void initialize() {
        frame = new JFrame();
        frame.setTitle("Timer v. 1.0");
        frame.getContentPane().setLayout(null);
        frame.setResizable(false);
        Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
        int screenWidth = (int)screen.getWidth();
        int screenHeight = (int)screen.getHeight();
        int gameWidth = 300;
        int gameHeight = 200;
        frame.setBounds(screenWidth / 2 - gameWidth / 2, screenHeight / 2 - gameHeight / 2 - 100, 299, 180);
        panel = new JPanel();
        panel.setBounds(0, 0, 293, 147);
        panel.setLayout(null);
        btnStarten = new JButton("Start");
        btnStarten.setBounds(100, 74, 97, 25);
        txtMinutes = new JTextField();
        txtMinutes.setBounds(165, 39, 71, 22);
        txtMinutes.setColumns(10);
        txtMinutes.setHorizontalAlignment(0);
        lblEnterMinutes = new JLabel("Enter Minutes:");
        lblEnterMinutes.setBounds(64, 39, 89, 22);
        panel.add(txtMinutes);
        panel.add(btnStarten);
        panel.add(lblEnterMinutes);
        frame.getContentPane().add(panel);
        frame.setVisible(true);
    }

    public void setListenerStartTimer(MouseAdapter ma) {
        btnStarten.addMouseListener(ma);
    }

    public static void setBtnText(String text) {
        btnStarten.setText(text);
    }

    public static void setTxtText(String text) {
        txtMinutes.setText(text);
    }

    public static String getMinutes() {
        return txtMinutes.getText().toString();
    }

    public static String getBtnText() {
        return btnStarten.getText().toString();
    }
}

