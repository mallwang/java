package collision;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.Timer;

public class Board extends JPanel implements ActionListener {

	private Timer timer;
	private Craft craft;
	private ArrayList aliens;
	public boolean ingame;
	private int B_WIDTH;
	private int B_HEIGHT;
	private int killed, highscore;

	public Board() {

		addKeyListener(new TAdapter());
		setFocusable(true);
		setBackground(Color.BLACK);
		setDoubleBuffered(true);
		ingame = true;

		setSize(400, 300);

		craft = new Craft();

		initAliens();

		getHighscore();

		timer = new Timer(5, this);
		timer.start();
	}

	private void getHighscore() {
		File file = new File("highscore.txt");

		if (file.exists()) {
			try {
				FileReader fr = new FileReader(file);
				BufferedReader br = new BufferedReader(fr);
				int high = (killed * 24 + 4) * 17 + 1;
				highscore = ((Integer.valueOf(br.readLine()) - 1) / 17 + 4) / 24;
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				file.createNewFile();
				FileWriter writer = new FileWriter(file, true);
				writer.write("0");
				writer.flush();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public boolean getIngame() {
		return ingame;
	}

	public void addNotify() {
		super.addNotify();
		B_WIDTH = getWidth();
		B_HEIGHT = getHeight();
	}

	public void initAliens() {
		aliens = new ArrayList();
		createAliens(0, 20);

		killed = 0;
		highscore = 0;
	}

	public void paint(Graphics g) {
		super.paint(g);

		if (ingame) {

			Graphics2D g2d = (Graphics2D) g;

			if (craft.isVisible())
				g2d.drawImage(craft.getImage(), craft.getX(), craft.getY(),
						this);

			ArrayList ms = craft.getMissiles();

			for (int i = 0; i < ms.size(); i++) {
				Missile m = (Missile) ms.get(i);
				g2d.drawImage(m.getImage(), m.getX(), m.getY(), this);
			}

			for (int i = 0; i < aliens.size(); i++) {
				Alien a = (Alien) aliens.get(i);
				if (a.isVisible())
					g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}

			g2d.setColor(Color.WHITE);
			g2d.drawString("Aliens zerstört: " + killed, 5, 15);
			g2d.drawString("Leer/F = Feuern", 300, 15);
			g2d.drawString("Highscore: " + highscore, 5, 267);
			g2d.drawString("Esc = Beenden", 300, 267);

		} else {
			String msg;
			Font small = new Font("Helvetica", Font.BOLD, 14);
			FontMetrics metr = this.getFontMetrics(small);

			g.setColor(Color.white);
			g.setFont(small);
			if (killed > highscore) {
				File file = new File("highscore.txt");
				FileWriter writer;
				try {
					writer = new FileWriter(file, false);
					int high = (killed * 24 + 4) * 17 + 1;
					writer.write("" + high);
					writer.flush();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

			msg = "Game Over";
			g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
					B_HEIGHT / 3);
			if (killed > highscore) {
				msg = "Neuer Highscore: " + killed;
				g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
						(B_HEIGHT / 2));
			} else {
				msg = "Zerstörte Aliens: " + killed;
				g.drawString(msg, (B_WIDTH - metr.stringWidth(msg)) / 2,
						B_HEIGHT / 2);
			}

			aliens.clear();
		}

		Toolkit.getDefaultToolkit().sync();
		g.dispose();
	}

	public void actionPerformed(ActionEvent e) {

		if (aliens.size() == 0) {
			ingame = false;
		}

		ArrayList ms = craft.getMissiles();

		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);
			if (m.isVisible())
				m.move();
			else
				ms.remove(i);
		}

		for (int i = 0; i < aliens.size(); i++) {
			Alien a = (Alien) aliens.get(i);
			if (a.isVisible())
				a.move();
			else {
				aliens.remove(i);
				killed++;
				createAliens(1, 1);
			}
		}

		craft.move();
		checkCollisions();
		repaint();
	}

	private void createAliens(int art, int amount) {

		int x1, x2;

		if (art == 0) {
			x1 = 1000;
			x2 = 800;
		} else {
			x1 = x2 = 500;
		}

		for (int i = 0; i < amount; i++) {
			int posX = (int) (Math.random() * x1) + x2;
			int posY = (int) (Math.random() * 230) + 30;
			// System.out.println("Neuer Alien: x:" + posX + "/ y:" + posY);
			aliens.add(new Alien(posX, posY));
		}
	}

	public void checkCollisions() {

		Rectangle r3 = craft.getBounds();

		for (int j = 0; j < aliens.size(); j++) {
			Alien a = (Alien) aliens.get(j);
			Rectangle r2 = a.getBounds();

			if (r3.intersects(r2)) {
				craft.setVisible(false);
				a.setVisible(false);
				ingame = false;
			}
		}

		ArrayList ms = craft.getMissiles();

		for (int i = 0; i < ms.size(); i++) {
			Missile m = (Missile) ms.get(i);

			Rectangle r1 = m.getBounds();

			for (int j = 0; j < aliens.size(); j++) {
				Alien a = (Alien) aliens.get(j);
				Rectangle r2 = a.getBounds();

				if (r1.intersects(r2)) {
					m.setVisible(false);
					a.setVisible(false);
				}
			}
		}
	}

	private class TAdapter extends KeyAdapter {

		public void keyReleased(KeyEvent e) {

			/*if (e.getKeyCode() == KeyEvent.VK_F2) {
				System.out.println("F2 gedrückt");
				if (ingame == false){
					String[] a = new String[0];
					Collision.main(a);
				}
			} else
				
				*/
			craft.keyReleased(e);
		}

		public void keyPressed(KeyEvent e) {
			craft.keyPressed(e);
		}
	}
}