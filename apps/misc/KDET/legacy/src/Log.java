import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Log {

	private static Logger jlog = Logger.getLogger("Kalibierungstool");
	private static File file;
	private static FileWriter writer;
	private final Level level;
	private final String nachricht;

	public Log(final Level level, final String nachricht) {
		// File anlegen
		file = new File("Kalibrierungtool.log");
		this.level = level;
		this.nachricht = nachricht;
		try {
			if (file.exists()) {
				writer = new FileWriter(file, false);
				writer.write("");
				writer.flush();
			} else {
				writer = new FileWriter(file, true);
			}
			final SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
			final Date currentTime = new Date();
			final String a = nachricht.replace("\n", System.getProperty("line.separator"));
			writer.write(formatter.format(currentTime) + System.getProperty("line.separator")
					+ "------------------------------------------------------------"
					+ System.getProperty("line.separator"));
			writer.write(a);
			writer.flush();
			writer.close();
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}
}
