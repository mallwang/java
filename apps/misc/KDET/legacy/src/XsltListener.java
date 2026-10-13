import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;

public class XsltListener implements ActionListener {

	private JFileChooser fileChooser;
	private JTextField textField;

	// comments @ SaveListener
	public XsltListener(JTextField textField) {
		this.textField = textField;
		this.fileChooser = new JFileChooser();
		this.fileChooser.setAcceptAllFileFilterUsed(false);
		this.fileChooser.setApproveButtonToolTipText("Festlegen des XSLT-ZIP-Archivs");
		this.fileChooser.setCurrentDirectory(new File(textField.getText()));
		this.fileChooser.setSelectedFile(new File(textField.getText()));
		this.fileChooser.setMultiSelectionEnabled(false);
		this.fileChooser.setDialogTitle("XSLT-ZIP-Archiv wählen");
		this.fileChooser.setFileFilter(new FileFilter() {
			public boolean accept(File file) {
				return file.getName().toLowerCase().endsWith(".zip") || file.isDirectory();
			}

			public String getDescription() {
				return "XSL(T)-Datei(*.zip)";
			}
		});
	}

	public void actionPerformed(ActionEvent e) {
		this.fileChooser.showOpenDialog(null);
		if (this.fileChooser.getSelectedFile() != null && (this.fileChooser.getSelectedFile().getName().toLowerCase().endsWith(".zip"))) {
			this.textField.setText(this.fileChooser.getSelectedFile().getAbsolutePath().toString());
		}
	}
}