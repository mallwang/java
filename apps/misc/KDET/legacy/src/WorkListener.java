import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkListener implements ActionListener {

	private String xmlPath;
	private String xsltPath;
	private String savePath;

	public WorkListener() {
	}

	public void update() {
		this.xmlPath = Window.window.getXmlInput().getText();
		this.xsltPath = Window.window.getXsltInput().getText();
		this.savePath = Window.window.getSaveInput().getText();
	}

	public void actionPerformed(ActionEvent e) {
		update();
		// if the filetypes are correct - perform transformation
		if ((xsltPath.toLowerCase().endsWith(".zip")) && xmlPath.toLowerCase().endsWith(".zip")) {
			Window.window.setCursor(new Cursor(Cursor.WAIT_CURSOR));
			new XMLTransformer(this.xmlPath, this.xsltPath, this.savePath).saveXML();
//			Window.window.setStatusLabel("Bereit.");
			Window.window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
			System.out.println("fertig");
		} else {
			new ErrorWindow("<html>Keine Quelldaten gewählt<br>Bitte wählen sie passende Quelldaten</html>", "Achtung - Quelldaten fehlen").errorWithoutExit();
		}
	}
}
