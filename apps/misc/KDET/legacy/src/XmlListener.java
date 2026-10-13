import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class XmlListener implements ActionListener {

	private JFileChooser fileChooser;
	private JTextField textField;

	// comments @ SaveListener
	public XmlListener(JTextField textField) {
		this.textField = textField;
		this.fileChooser = new JFileChooser();
		this.fileChooser.setAcceptAllFileFilterUsed(false);
		this.fileChooser.setApproveButtonToolTipText("Festlegen des XML-ZIP-Archivs");
		this.fileChooser.setCurrentDirectory(new File("\\\\HomedirFiler01.zentrale.in.bundesbank.de.\\homedirs01$\\o1504d3\\Eigene Dateien"));
		this.fileChooser.setSelectedFile(new File(textField.getText()));
		this.fileChooser.setMultiSelectionEnabled(false);
		this.fileChooser.setDialogTitle("XML-ZIP-Archiv wählen");
		this.fileChooser.setFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return file.getName().toLowerCase().endsWith(".zip") || file.isDirectory();
			}

			public String getDescription() {
				return "ZIP-Datei(*.zip)";
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		this.fileChooser.showOpenDialog(null);
		if (this.fileChooser.getSelectedFile() != null && this.fileChooser.getSelectedFile().getName().toLowerCase().endsWith(".zip")) {
			this.textField.setText(this.fileChooser.getSelectedFile().getAbsolutePath().toString());
		}
	}
}