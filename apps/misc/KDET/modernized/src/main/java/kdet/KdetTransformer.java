package kdet;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.Map;
import java.util.stream.Stream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

/**
 * Splits calibration XML files into sections and turns each section into a CSV file.
 * Step 1 runs {@code main.xslt} once per XML file and section; step 2 runs the section stylesheet.
 */
public final class KdetTransformer {

    /** Thrown for problems the user can fix (missing stylesheets, broken archives). */
    public static class KdetException extends Exception {
        public KdetException(String message, Throwable cause) {
            super(message, cause);
        }
        public KdetException(String message) {
            super(message);
        }
    }

    public record SectionTime(String section, double seconds) {}

    public record Result(List<SectionTime> sections, double totalSeconds) {
        public String format() {
            StringBuilder sb = new StringBuilder("Dauer:\n");
            for (SectionTime t : sections) {
                sb.append(t.section()).append(": ").append(t.seconds()).append(" Sekunden\n");
            }
            return sb.append("Insgesamt: ").append(totalSeconds).append(" Sekunden").toString();
        }
    }

    private KdetTransformer() {}

    /** Writes {@code <section>.csv} for every section stylesheet in {@code xsltZip} into {@code outDir}. */
    public static Result run(Path xmlZip, Path xsltZip, Path outDir) throws KdetException {
        long startAll = System.nanoTime();
        Path tmp = null;
        try {
            Files.createDirectories(outDir);
            tmp = Files.createTempDirectory("kdet");
            List<Path> xmls = unzip(xmlZip, tmp.resolve("xml"), ".xml");
            Map<String, Path> xslts = new TreeMap<>();
            for (Path p : unzip(xsltZip, tmp.resolve("xslt"), ".xslt")) {
                String name = p.getFileName().toString();
                xslts.put(name.substring(0, name.length() - ".xslt".length()), p);
            }
            Path main = xslts.remove("main");
            if (main == null) {
                throw new KdetException("Es wurde keine 'main.xslt' im XSLT-Archiv gefunden.");
            }
            if (xslts.isEmpty()) {
                throw new KdetException("Es wurden außer der 'main.xslt' keine XSLTs im XSLT-Archiv gefunden!");
            }

            TransformerFactory factory = TransformerFactory.newInstance();
            long start = System.nanoTime();
            Map<String, Path> sectionXml = splitBySection(factory, main, xmls, xslts.keySet(), tmp);
            List<SectionTime> times = new ArrayList<>();
            times.add(new SectionTime("Entpacken & Vorbereiten", seconds(start)));

            for (var entry : xslts.entrySet()) {
                start = System.nanoTime();
                Transformer t = factory.newTransformer(new StreamSource(entry.getValue().toFile()));
                try (OutputStream out = Files.newOutputStream(outDir.resolve(entry.getKey() + ".csv"))) {
                    t.transform(new StreamSource(sectionXml.get(entry.getKey()).toFile()), new StreamResult(out));
                }
                times.add(new SectionTime(entry.getKey(), seconds(start)));
            }
            return new Result(times, seconds(startAll));
        } catch (IOException | TransformerException e) {
            throw new KdetException("Die Konvertierung ist fehlgeschlagen: " + e.getMessage(), e);
        } finally {
            if (tmp != null) {
                deleteTree(tmp);
            }
        }
    }

    /** Step 1: one {@code <calibrationDataFile>} per section, filled by main.xslt for every input XML. */
    private static Map<String, Path> splitBySection(TransformerFactory factory, Path main, List<Path> xmls,
            Iterable<String> sections, Path tmp) throws IOException, TransformerException {
        Transformer t = factory.newTransformer(new StreamSource(main.toFile()));
        Map<String, Path> result = new TreeMap<>();
        for (String section : sections) {
            Path file = tmp.resolve(section + ".xml");
            try (OutputStream out = Files.newOutputStream(file)) {
                out.write("<calibrationDataFile>".getBytes(StandardCharsets.UTF_8));
                for (Path xml : xmls) {
                    t.setParameter("Section", section);
                    t.transform(new StreamSource(xml.toFile()), new StreamResult(out));
                }
                out.write("</calibrationDataFile>".getBytes(StandardCharsets.UTF_8));
            }
            result.put(section, file);
        }
        return result;
    }

    /** Extracts {@code zip} into {@code dir} and returns the files ending in {@code suffix}; rejects paths leaving {@code dir}. */
    static List<Path> unzip(Path zip, Path dir, String suffix) throws IOException {
        Files.createDirectories(dir);
        Path root = dir.toAbsolutePath().normalize();
        List<Path> files = new ArrayList<>();
        try (InputStream in = Files.newInputStream(zip); ZipInputStream zis = new ZipInputStream(in)) {
            for (ZipEntry e = zis.getNextEntry(); e != null; e = zis.getNextEntry()) {
                Path target = root.resolve(e.getName()).normalize();
                if (!target.startsWith(root)) {
                    throw new IOException("Ungültiger Pfad im Archiv: " + e.getName());
                }
                if (e.isDirectory()) {
                    Files.createDirectories(target);
                    continue;
                }
                Files.createDirectories(target.getParent());
                Files.copy(zis, target);
                if (target.getFileName().toString().toLowerCase().endsWith(suffix)) {
                    files.add(target);
                }
            }
        }
        return files;
    }

    private static double seconds(long startNanos) {
        return (System.nanoTime() - startNanos) / 1e9;
    }

    private static void deleteTree(Path dir) {
        try (Stream<Path> paths = Files.walk(dir)) {
            paths.sorted(java.util.Comparator.reverseOrder()).forEach(p -> {
                try {
                    Files.delete(p);
                } catch (IOException e) {
                    throw new UncheckedIOException(e);
                }
            });
        } catch (IOException | UncheckedIOException ignored) {
            // best effort: temp dir is cleaned by the OS eventually
        }
    }
}
