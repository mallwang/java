import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.logging.Level;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Window extends JFrame {

	private static final long serialVersionUID = 1L;
	public static Log log;
	public static Window window;

	public static void main(final String[] args) {
		try {
			window = new Window();
		} catch (final Throwable t) {
			// StackTrace zu einem String zusammenfügen
			StringBuilder sb = new StringBuilder();
			sb = sb.append("Error type:");
			sb = sb.append(t.toString());
			sb = sb.append("\n");
			final StackTraceElement[] ste = t.getStackTrace();
			for (final StackTraceElement element : ste) {
				sb = sb.append(element);
				sb = sb.append("\n");
			}
			log = new Log(Level.SEVERE, sb.toString());
		}
	}

	private JLabel xmlLabel;
	private JTextField xmlInput;

	private JButton xmlButton;
	private JLabel xsltLabel;
	private JTextField xsltInput;

	private JButton xsltButton;
	private JLabel saveLabel;
	private JTextField saveInput;

	private JButton saveButton;
	private JButton workButton;

	private JButton closeButton;

	public Window() {
		// Windowtitel
		super("Calibration");
		// Initiates fields
		this.init();
		// Adds components
		this.initWindow();
		// Defines size
		this.setSize(730, 150);
		// Centralizes window
		final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		final Dimension frameSize = this.getSize();
		if (frameSize.height > screenSize.height) {
			frameSize.height = screenSize.height;
		}
		if (frameSize.width > screenSize.width) {
			frameSize.width = screenSize.width;
		}
		this.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
		// Defines windowsettings
		this.setLayout(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setVisible(true);
	}

	public JTextField getSaveInput() {
		return saveInput;
	}

	public JTextField getXmlInput() {
		return xmlInput;
	}

	public JTextField getXsltInput() {
		return xsltInput;
	}

	private void init() {
		/*
		 * Initiation
		 */
		xmlLabel = new JLabel("XML-ZIP-Archiv:");
		xmlInput = new JTextField("");
		xmlInput.setEditable(false);
		xmlButton = new JButton("XML-Archiv wählen");

		xsltLabel = new JLabel("XSLT-ZIP-Archiv:");
		xsltInput = new JTextField("");
		xsltInput.setEditable(false);
		xsltButton = new JButton("XSLT-Archiv wählen");

		saveLabel = new JLabel("Speicherpfad:");
		saveInput = new JTextField("");
		saveInput.setEditable(false);
		saveButton = new JButton("Speicherpfad wählen");

		workButton = new JButton("Verarbeiten");
		closeButton = new JButton("Beenden");
	}

	private void initWindow() {
		/*
		 * Locationing
		 */
		xmlLabel.setBounds(10, 10, 160, 20);
		xmlInput.setBounds(120, 10, 430, 20);
		xmlButton.setBounds(560, 10, 160, 20);

		xsltLabel.setBounds(10, 40, 160, 20);
		xsltInput.setBounds(120, 40, 430, 20);
		xsltButton.setBounds(560, 40, 160, 20);

		saveLabel.setBounds(10, 70, 100, 20);
		saveInput.setBounds(120, 70, 430, 20);
		saveButton.setBounds(560, 70, 160, 20);

		workButton.setBounds(390, 100, 160, 20);
		closeButton.setBounds(560, 100, 160, 20);

		/*
		 * Adding to Window
		 */
		this.add(xmlLabel);
		this.add(xmlInput);
		this.add(xmlButton);

		this.add(xsltLabel);
		this.add(xsltInput);
		this.add(xsltButton);

		this.add(saveLabel);
		this.add(saveInput);
		this.add(saveButton);

		this.add(workButton);
		this.add(closeButton);

		/*
		 * Button-Listener
		 */
		getRootPane().setDefaultButton(workButton);
		// opens XML-selecting menu
		xmlButton.addActionListener(new XmlListener(xmlInput));
		// opens XSLT-selecting menu
		xsltButton.addActionListener(new XsltListener(xsltInput));
		// opens Savepath-selecting menu
		saveButton.addActionListener(new SaveListener(saveInput));
		// starts transformation
		workButton.addActionListener(new WorkListener());
		// closing the application
		closeButton.addActionListener(new CloseListener());
	}
}
