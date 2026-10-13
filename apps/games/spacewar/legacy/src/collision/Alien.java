package collision;

import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;


public class Alien {

    private String craft = "/img/alien.png";

    private int x;
    private int y;
    private int width;
    private int height;
    private boolean visible;
    private Image image;

    public Alien(int x, int y) {
    	try {
			ImageIcon ii = new ImageIcon(this.getClass().getResource(craft));
			image = ii.getImage();
		} catch (NullPointerException e) {
			JOptionPane.showMessageDialog(null,
				    "Es wurden keine Bilder gefunden!\nDas Programm wird beendet.",
				    "Bilder nicht gefunden",
				    JOptionPane.ERROR_MESSAGE);
			System.exit(0);
		}
        width = image.getWidth(null);
        height = image.getHeight(null);
        visible = true;
        this.x = x;
        this.y = y;
    }


    public void move() {
        if (x < 0) 
            x = 400;
        x -= 1;
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

    public Image getImage() {
        return image;
    }

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}