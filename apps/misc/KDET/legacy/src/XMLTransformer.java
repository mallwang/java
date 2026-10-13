import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.logging.Level;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XMLTransformer {

	public static Log log;
	private final String xmlPath;
	private final String xsltPath;
	private final String savePath;
	private final ArrayList<String> sections = new ArrayList<String>();

	public XMLTransformer(final String xmlPath, final String xsltPath, final String savePath) {
		this.xmlPath = xmlPath;
		this.xsltPath = xsltPath;
		this.savePath = savePath;
	}

	public void deleteFiles() {

		// Alle temporären .xml-Dateien löschen
		final File xmlFile = new File(savePath);
		for (final File file : xmlFile.listFiles()) {
			if (file.toString().endsWith(".xml")) {
				file.delete();
			}
		}

		// Alle temporären .xslt-Dateien löschen
		final File xsltFile = new File(savePath);
		for (final File file : xsltFile.listFiles()) {
			if (file.toString().endsWith(".xslt")) {
				file.delete();
			}
		}
	}

	public void saveXML() {
		try {
			/*** Vorbereitung ***/

			// Zeitausgabe
			final Long startAll = System.currentTimeMillis();
			String outputTime = "Dauer:\n";
			Long start = System.currentTimeMillis();

			// XMLs entzippen
			ArrayList<File> xmlFileList;
			try {
				xmlFileList = UnZip.unZipIt(xmlPath, savePath); // tempPath zu savePath geändert
			} catch (final Exception e) {
				StringBuilder sb = new StringBuilder();
				sb = sb.append("Exception type:");
				sb = sb.append(e.toString());
				sb = sb.append("\n");
				final StackTraceElement[] ste = e.getStackTrace();
				for (final StackTraceElement element : ste) {
					sb = sb.append(element);
					sb = sb.append("\n");
				}

				// Alle temporären Dateien löschen
				deleteFiles();

				// Log-file füllen
				log = new Log(Level.SEVERE, sb.toString());

				new ErrorWindow(
						"Das Entpacken der XML-Dateien ist fehlgeschlagen.\n\nEine nähere Fehlerbeschreibung befindet sich in der .log-Datei",
						"Fehler").errorWithoutExit();
				return;
			}
			// XSLTs entzippen
			ArrayList<File> xsltFileList;
			try {
				xsltFileList = UnZip.unZipIt(xsltPath, savePath); // tempPath zu savePath geändert
			} catch (final Exception e) {

				StringBuilder sb = new StringBuilder();
				sb = sb.append("Exception type:");
				sb = sb.append(e.toString());
				sb = sb.append("\n");
				final StackTraceElement[] ste = e.getStackTrace();
				for (final StackTraceElement element : ste) {
					sb = sb.append(element);
					sb = sb.append("\n");
				}

				// Alle temporären Dateien löschen
				deleteFiles();

				// Log-file füllen
				log = new Log(Level.SEVERE, sb.toString());

				new ErrorWindow(
						"Das Entpacken der XSLT-Dateien ist fehlgeschlagen.\n\nEine nähere Fehlerbeschreibung befindet sich in der .log-Datei",
						"Fehler").errorWithoutExit();
				return;
			}
			// Main Transformer Factory erstellen
			final File mainXslt = new File(savePath + "\\main.xslt"); // tempPath zu savePath geändert
			if (!mainXslt.exists()) {

				new ErrorWindow("Es wurde keine 'main.xslt' im XSLT-Archiv gefunden.", "Fehler").errorWithoutExit();
				return;
			}
			xsltFileList.remove(mainXslt);
			final TransformerFactory transFact = TransformerFactory.newInstance();
			Transformer trans = transFact.newTransformer(new StreamSource(mainXslt));
			// Sections filtern
			for (final File xsltFile : xsltFileList) {
				final String name = xsltFile.getName();
				final int dot = name.lastIndexOf(".");
				if (name.substring(dot).equals(".xslt")) {
					sections.add(name.substring(0, dot));
				}
			}
			if (sections.isEmpty()) {
				new ErrorWindow("Es wurden außer der 'main.xslt' keine XSLTs im XSLT-Archiv gefunden!", "Fehler")
						.errorWithoutExit();
				return;
			}

			/*** XML-Transformation Step 1: Aufteilung der XMLs nach Section ***/

			// Output-Streams erstellen
			final FileOutputStream[] fileOutputStreams = new FileOutputStream[sections.size()];
			final ArrayList<File> tempXmlFileList = new ArrayList<File>();
			for (int i = 0; i < sections.size(); i++) {
				final File file = new File(savePath + "\\" + sections.get(i) + ".xml"); // tempPath zu savePath geändert
				tempXmlFileList.add(file);
				if (file.exists()) {
					file.delete();
				}
				fileOutputStreams[i] = new FileOutputStream(file, true);
				fileOutputStreams[i].write(new String("<calibrationDataFile>").getBytes());
			}

			// XML-Transformation
			for (final File xmlFile : xmlFileList) {
				final String name = xmlFile.getName();
				final int dot = name.lastIndexOf(".");
				if (name.substring(dot).equals(".xml")) {
					final Source xmlSource = new StreamSource(xmlFile);
					for (int i = 0; i < sections.size(); i++) {
						trans.setParameter("Section", sections.get(i));
						trans.transform(xmlSource, new StreamResult(fileOutputStreams[i]));
					}
					xmlFile.delete();
				}
			}
			mainXslt.delete();

			// Output Stream schließen
			for (int i = 0; i < sections.size(); i++) {
				fileOutputStreams[i].write(new String("</calibrationDataFile>").getBytes());
				fileOutputStreams[i].close();
			}

			// Zeitausgabe
			Long end = System.currentTimeMillis();
			outputTime += "Entpacken & Vorbereiten: " + ((double) (end - start) / 1000) + " Sekunden\n";

			/*** XML-Transformation Step 2: Umwandlung von XML nach csv ***/

			for (int i = 0; i < sections.size(); i++) {
				// Zeitausgabe
				start = System.currentTimeMillis();

				// Output-Streams erstellen
				final File file = new File(savePath + "\\" + sections.get(i) + ".csv");
				if (file.exists()) {
					file.delete();
				}
				final FileOutputStream fileOutputStream = new FileOutputStream(file, true);

				// XML-Transformation
				final Source xmlSource = new StreamSource(tempXmlFileList.get(i));
				trans = transFact.newTransformer(new StreamSource(xsltFileList.get(i)));
				trans.transform(xmlSource, new StreamResult(fileOutputStream));
				xsltFileList.get(i).delete();
				tempXmlFileList.get(i).delete();

				// Output Stream schließen
				fileOutputStream.close();

				// Zeitausgabe
				end = System.currentTimeMillis();
				outputTime += sections.get(i) + ": " + ((double) (end - start) / 1000) + " Sekunden\n";
			}

			/*** Nachbereitung ***/

			// Alle temporären Dateien löschen
			deleteFiles();

			// Zeitausgabe
			outputTime += "------------------------------------------------------------\n";
			final Long endAll = System.currentTimeMillis();
			outputTime += "Insgesamt: " + ((double) (endAll - startAll) / 1000) + " Sekunden";

			// Log-file füllen
			log = new Log(Level.SEVERE, "Die Konvertierung wurde erfolgreich abgeschlossen.\n\n" + outputTime);

			// Ausgabe
			new ErrorWindow("Die Konvertierung wurde erfolgreich abgeschlossen.\n\n" + outputTime, "Fertig")
					.errorWithoutExit();

		} catch (final FileNotFoundException fnfe) {
			// StackTrace zu einem String zusammenfügen
			StringBuilder sb = new StringBuilder();
			sb = sb.append("Exception type:");
			sb = sb.append(fnfe.toString());
			sb = sb.append("\n");
			final StackTraceElement[] ste = fnfe.getStackTrace();
			for (final StackTraceElement element : ste) {
				sb = sb.append(element);
				sb = sb.append("\n");
			}

			// Alle temporären Dateien löschen
			deleteFiles();

			// Log-file füllen
			log = new Log(Level.SEVERE, sb.toString());

			// Fehlerfenster anzeigen
			new ErrorWindow(
					"Es ist ein unbekannter Fehler aufgetreten. Bitte überprüfen Sie, ob alle Zieldokumente geschlossen sind.\n\nEine nähere Fehlerbeschreibung befindet sich in der .log-Datei",
					"Fehler").errorWithoutExit();
		} catch (final Exception e) {
			StringBuilder sb = new StringBuilder();
			sb = sb.append("Exception type:");
			sb = sb.append(e.toString());
			sb = sb.append("\n");
			final StackTraceElement[] ste = e.getStackTrace();
			for (final StackTraceElement element : ste) {
				sb = sb.append(element);
				sb = sb.append("\n");
			}

			// Alle temporären Dateien löschen
			deleteFiles();

			// Log-file füllen
			log = new Log(Level.SEVERE, sb.toString());

			// Fehlerfenster anzeigen
			new ErrorWindow(
					"Es ist ein Fehler aufgetreten!\n\nEine nähere Fehlerbeschreibung befindet sich in der .log-Datei",
					"Fehler").errorWithoutExit();
		} catch (final Error er) {
			StringBuilder sb = new StringBuilder();
			sb = sb.append("Error type:");
			sb = sb.append(er.toString());
			sb = sb.append("\n");
			final StackTraceElement[] ste = er.getStackTrace();
			for (final StackTraceElement element : ste) {
				sb = sb.append(element);
				sb = sb.append("\n");
			}

			// Alle temporären Dateien löschen
			deleteFiles();

			// Log-file füllen
			log = new Log(Level.SEVERE, sb.toString());

			System.exit(-12222);
		}
	}
}
