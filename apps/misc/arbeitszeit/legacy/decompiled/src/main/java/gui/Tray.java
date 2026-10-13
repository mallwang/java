/*
 * Decompiled with CFR 0.152.
 */
package gui;

import gui.Hauptseite;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.Toolkit;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JOptionPane;
import steuerung.Hauptsteuerung;

public class Tray {
    private static SystemTray tray;
    private static TrayIcon trayIcon;
    private static PopupMenu popup;
    private static MenuItem restoreItem;
    private static MenuItem exitItem;

    public Tray() {
        Tray.createTray();
    }

    private static void createTray() {
        if (SystemTray.isSupported()) {
            popup = new PopupMenu();
            trayIcon = new TrayIcon(Toolkit.getDefaultToolkit().getImage(Hauptseite.class.getResource("/evosoftLogoTray.png")));
            trayIcon.setImageAutoSize(true);
            tray = SystemTray.getSystemTray();
            restoreItem = new MenuItem("Wiederherstellen");
            exitItem = new MenuItem("Beenden");
            popup.add(restoreItem);
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);
            Tray.setTooltip(Hauptsteuerung.getRestzeitString());
            MouseAdapter mouseAdapter = new MouseAdapter(){

                @Override
                public void mouseClicked(MouseEvent e) {
                    if (e.getClickCount() == 2) {
                        Tray.restoreWindow(tray, trayIcon);
                    }
                }
            };
            trayIcon.addMouseListener(mouseAdapter);
            restoreItem.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    Tray.restoreWindow(tray, trayIcon);
                }
            });
            exitItem.addActionListener(new ActionListener(){

                @Override
                public void actionPerformed(ActionEvent e) {
                    tray.remove(trayIcon);
                    System.exit(0);
                }
            });
            try {
                tray.add(trayIcon);
                Tray.showMessage(Hauptsteuerung.getRestzeitString());
                Hauptseite.frame.setVisible(false);
            }
            catch (Exception e) {
                JOptionPane.showMessageDialog(Hauptseite.frame, "Das Minimieren schlug fehl, sorry!");
            }
        } else {
            JOptionPane.showMessageDialog(Hauptseite.frame, "Dein System unterst\u00fctzt leider kein Tray, sorry!");
        }
    }

    private static void restoreWindow(SystemTray tray, TrayIcon trayIcon) {
        Hauptseite.frame.setVisible(true);
        tray.remove(trayIcon);
    }

    public static void showMessage(String message) {
        trayIcon.displayMessage("Arbeitszeitrechner", message, TrayIcon.MessageType.INFO);
    }

    public static void setTooltip(String message) {
        trayIcon.setToolTip(message);
    }
}

