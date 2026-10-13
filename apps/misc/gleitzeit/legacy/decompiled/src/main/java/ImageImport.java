/*
 * Decompiled with CFR 0.152.
 */
import java.net.URL;
import javax.swing.ImageIcon;

public class ImageImport {
    public static ImageIcon createImageIcon(Object caller, String path) {
        URL imgURL = caller.getClass().getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        }
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}

