package slider;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * Demo application panel to display a range slider.
 */
public class RangeSliderDemo extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6622507485798847139L;

	/**
	 * Main application method.
	 * 
	 * @param args
	 *            String[]
	 */
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				new RangeSliderDemo().display();
			}
		});
	}

	private JLabel rangeSliderLabel1 = new JLabel();
	private JLabel rangeSliderValue1 = new JLabel();
	private JLabel rangeSliderLabel2 = new JLabel();
	private JLabel rangeSliderValue2 = new JLabel();

	private RangeSlider rangeSlider = new RangeSlider();

	public RangeSliderDemo() {
		setBorder(BorderFactory.createEmptyBorder(6, 6, 6, 6));
		rangeSliderLabel1.setBounds(105, 125, 62, 14);

		rangeSliderLabel1.setText("Lower value:");
		rangeSliderLabel2.setBounds(105, 142, 62, 14);
		rangeSliderLabel2.setText("Upper value:");
		rangeSliderValue1.setBounds(0, 0, 0, 0);
		rangeSliderValue1.setHorizontalAlignment(JLabel.LEFT);
		rangeSliderValue2.setBounds(0, 0, 0, 0);
		rangeSliderValue2.setHorizontalAlignment(JLabel.LEFT);
		rangeSlider.setBounds(105, 159, 240, 16);

		rangeSlider.setPreferredSize(new Dimension(240, rangeSlider
				.getPreferredSize().height));
		rangeSlider.setMinimum(0);
		rangeSlider.setMaximum(10);

		// Add listener to update display.
		rangeSlider.addChangeListener(new ChangeListener() {
			@Override
			public void stateChanged(ChangeEvent e) {
				RangeSlider slider = (RangeSlider) e.getSource();
				rangeSliderValue1.setText(String.valueOf(slider.getValue()));
				rangeSliderValue2.setText(String.valueOf(slider.getUpperValue()));
			}
		});
		setLayout(null);

		add(rangeSliderLabel1);
		add(rangeSliderValue1);
		add(rangeSliderLabel2);
		add(rangeSliderValue2);
		add(rangeSlider);
	}

	public void display() {
		// Initialize values.
		rangeSlider.setValue(3);
		rangeSlider.setUpperValue(7);

		// Initialize value display.
		rangeSliderValue1.setText(String.valueOf(rangeSlider.getValue()));
		rangeSliderValue2.setText(String.valueOf(rangeSlider.getUpperValue()));

		// Create window frame.
		JFrame frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setTitle("Range Slider Demo");

		// Set window content and validate.
		frame.getContentPane().setLayout(new BorderLayout());
		frame.getContentPane().add(this, BorderLayout.CENTER);
		frame.pack();

		// Set window location and display.
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

}
