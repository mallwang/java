import javax.swing.JOptionPane;

public class ErrorWindow {

	private String error;

	private String title;

	public ErrorWindow(String error, String title) {
		this.error = error;
		this.title = title;
	}

	// Generates Error(warning)Window
	public void errorWithoutExit() {
		JOptionPane.showMessageDialog(null, this.error, this.title,
				JOptionPane.WARNING_MESSAGE);
	}

	//Generates ErrorWindow with Sys.exit
	public void errorExit(){
		JOptionPane.showMessageDialog(null, this.error, this.title,
				JOptionPane.ERROR_MESSAGE);
		System.exit(0);
	}
}