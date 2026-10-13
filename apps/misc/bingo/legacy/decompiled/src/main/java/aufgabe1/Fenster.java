/*
 * Decompiled with CFR 0.152.
 */
package aufgabe1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

public class Fenster
extends JFrame {
    private JProgressBar pbFortschritt;
    private JButton btnFarbenwechsel;
    private int klicks;

    public Fenster() {
        this.setLayout(null);
        this.setDefaultCloseOperation(3);
        this.setTitle("Gr\u00fcnes Fenster");
        this.getContentPane().setBackground(Color.GREEN);
        this.setSize(200, 150);
        this.setResizable(false);
        Dimension winDim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation((winDim.width - this.getWidth()) / 2, (winDim.height - this.getHeight()) / 3);
        this.klicks = 0;
        this.pbFortschritt = new JProgressBar();
        this.pbFortschritt.setBounds(10, 75, 180, 25);
        this.pbFortschritt.setValue(0);
        this.pbFortschritt.setStringPainted(true);
        this.pbFortschritt.setString(String.valueOf(this.klicks * 10) + "%");
        this.btnFarbenwechsel = new JButton();
        this.btnFarbenwechsel.setBounds(25, 20, 150, 25);
        this.btnFarbenwechsel.setText("Klick Mich!");
        this.btnFarbenwechsel.addMouseListener(new MouseListener(){

            @Override
            public void mouseReleased(MouseEvent arg0) {
                if (Fenster.this.klicks < 10) {
                    Fenster fenster = Fenster.this;
                    fenster.klicks = fenster.klicks + 1;
                    Fenster.this.pbFortschritt.setValue(Fenster.this.klicks * 10);
                    Fenster.this.pbFortschritt.setString(String.valueOf(Fenster.this.klicks * 10) + "%");
                    if (Fenster.this.klicks == 10) {
                        Fenster.this.getContentPane().setBackground(Color.RED);
                        int n = JOptionPane.showConfirmDialog(null, "BINGO!!", "Meldung", 0);
                        if (n == 0) {
                            System.exit(0);
                        }
                    }
                } else {
                    int n = JOptionPane.showConfirmDialog(null, "BINGO!!", "Meldung", 0);
                    if (n == 0) {
                        System.exit(0);
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent arg0) {
            }

            @Override
            public void mouseExited(MouseEvent arg0) {
            }

            @Override
            public void mouseEntered(MouseEvent arg0) {
            }

            @Override
            public void mouseClicked(MouseEvent arg0) {
            }
        });
        this.add(this.pbFortschritt);
        this.add(this.btnFarbenwechsel);
        this.setVisible(true);
    }

    public static void main(String[] args) {
        new Fenster();
    }
}

