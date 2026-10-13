package collision;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

public class Missile {

    private int x, y;
    private Image image;
    boolean visible;
    private int width, height;

    private final int BOARD_WIDTH = 390;
    private final int MISSILE_SPEED = 5;

    public Missile(int x, int y) {

    	try {
			ImageIcon ii = new ImageIcon(this.getClass().getResource("/img/rakete.png"));
			image = ii.getImage();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,
					"Es wurden keine Bilder gefunden!\nDas Programm wird beendet.",
				    "Bilder nicht gefunden",
				    JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
        visible = true;
        width = image.getWidth(null);
        height = image.getHeight(null);
        this.x = x;
        this.y = y;
    }


    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(Boolean visible) {
        this.visible = visible;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }

    public void move() {
        x += MISSILE_SPEED;
        if (x > BOARD_WIDTH)
            visible = false;
    }
}