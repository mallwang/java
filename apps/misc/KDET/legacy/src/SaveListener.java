import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JTextField;

public class SaveListener implements ActionListener {

	private JFileChooser fileChooser;
	private JTextField textField;

	public SaveListener(JTextField textField) {
		/*
		 * choosing the filepath to save the xml's
		 */
		this.textField = textField;
		this.fileChooser = new JFileChooser();
		this.fileChooser.setAcceptAllFileFilterUsed(false);
		this.fileChooser.setApproveButtonToolTipText("Durch klicken wird der Speicherpfad der erzeugten XML-Dateien festgelegt");
		this.fileChooser.setCurrentDirectory(new File(textField.getText()));
		this.fileChooser.setDialogTitle("Speicherpfad wählen");
		this.fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	}

	public void actionPerformed(ActionEvent e) {
		// opens the savedialog
		this.fileChooser.showSaveDialog(null);
		// checking for xml-filetype
		if (this.fileChooser.getSelectedFile().isDirectory()) {
			this.textField.setText(this.fileChooser.getSelectedFile().getAbsolutePath().toString());
		}
	}
}