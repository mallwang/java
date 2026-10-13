package collision;

import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Craft {

	private String craft = "/img/raumschiff.png";

	private int dx;
	private int dy;
	private int x;
	private int y;
	private int width;
	private int height;
	private boolean visible;
	private Image image;
	private ArrayList missiles;
	
	public Craft() {
		try {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
			image = ii.getImage();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,
					"Es wurden keine Bilder gefunden!\nDas Programm wird beendet.",
					"Bilder nicht gefunden", JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
		width = image.getWidth(null);
		height = image.getHeight(null);
		missiles = new ArrayList();
		visible = true;
		x = 40;
		y = 60;
	}

	public void move() {

		x += dx;
		y += dy;

		if (x < 1) {
			x = 1;
		}

		if (y < 1) {
			y = 1;
		}
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Image getImage() {
		return image;
	}

	public ArrayList getMissiles() {
		return missiles;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public boolean isVisible() {
		return visible;
	}

	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_SPACE || key == KeyEvent.VK_F) {
			fire();
		}
		
		if (key == KeyEvent.VK_ESCAPE){
			int n = JOptionPane.showConfirmDialog(
				    null,
				    "Möchtest du wirklich beenden?",
				    "Space Invasion beenden",
				    JOptionPane.YES_NO_OPTION);
			if (n == 0)
				System.exit(0);
		}

		if (key == KeyEvent.VK_LEFT) {
			dx = -2;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 2;
		}

		if (key == KeyEvent.VK_UP) {
			dy = -2;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 2;
		}
	}

	public void fire() {
		missiles.add(new Missile(x + width, y + height / 2));
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_LEFT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_RIGHT) {
			dx = 0;
		}

		if (key == KeyEvent.VK_UP) {
			dy = 0;
		}

		if (key == KeyEvent.VK_DOWN) {
			dy = 0;
		}
	}
}