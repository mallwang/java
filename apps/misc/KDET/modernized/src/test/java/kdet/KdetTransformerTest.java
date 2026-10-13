package kdet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

class KdetTransformerTest {

    private static final String NS = "http://de.bundesbank.de/cocas/export/calibrationdata";
    private static final String[] SECTIONS =
            {"main", "accounts", "additionals", "companies", "defaults", "externalRatings", "ratings"};

    @TempDir Path dir;

    private static Path zip(Path file, Map<String, byte[]> entries) throws IOException {
        try (OutputStream out = Files.newOutputStream(file); ZipOutputStream zos = new ZipOutputStream(out)) {
            for (var e : entries.entrySet()) {
                zos.putNextEntry(new ZipEntry(e.getKey()));
                zos.write(e.getValue());
                zos.closeEntry();
            }
        }
        return file;
    }

    private Path xsltZip() throws IOException {
        Map<String, byte[]> entries = new java.util.LinkedHashMap<>();
        for (String s : SECTIONS) {
            try (var in = getClass().getResourceAsStream("/xslt/" + s + ".xslt")) {
                entries.put(s + ".xslt", in.readAllBytes());
            }
        }
        return zip(dir.resolve("xslt.zip"), entries);
    }

    private Path xmlZip() throws IOException {
        String xml = """
                <?xml version="1.0" encoding="UTF-8"?>
                <calibrationDataFile xmlns="%s">
                  <account companyID="C1" schemaCountry="DE" schemaAccountingType="HGB" schemaVersion="1"
                           accountID="A1" identNumber="42" status="ok" certificationDate="2020-01-01" referenceDate="2019-12-31">
                    <column name="revenue">100;5</column>
                    <column name="revenue" scenario="plan">200</column>
                    <column name="profit">7</column>
                  </account>
                  <account companyID="C2" schemaCountry="DE" schemaAccountingType="HGB" schemaVersion="1"
                           accountID="A2" identNumber="43" status="ok" certificationDate="2020-01-01" referenceDate="2019-12-31">
                    <column name="profit">9</column>
                  </account>
                  <company companyID="C1" identNumber="42" validFrom="2000" validTo="2030"/>
                </calibrationDataFile>
                """.formatted(NS);
        return zip(dir.resolve("xml.zip"), Map.of("a.xml", xml.getBytes()));
    }

    @Test
    void producesOneCsvPerSection() throws Exception {
        Path out = dir.resolve("out");
        KdetTransformer.Result result = KdetTransformer.run(xmlZip(), xsltZip(), out);

        String accounts = Files.readString(out.resolve("accounts.csv"), Charset.forName("windows-1252"));
        String[] lines = accounts.split("\n");
        assertEquals(3, lines.length);
        assertTrue(lines[0].endsWith("referenceDate;revenue;plan_revenue;profit"), lines[0]);
        assertTrue(lines[1].startsWith("C1;DE;HGB;1;A1;42;ok;2020-01-01;2019-12-31;"), lines[1]);
        assertTrue(lines[1].contains("100,5"), "';' inside values is replaced by ','");
        assertTrue(lines[2].startsWith("C2;"), lines[2]);

        String companies = Files.readString(out.resolve("companies.csv"));
        assertTrue(companies.contains("C1;42;2000;2030"), companies);
        for (String s : SECTIONS) {
            if (!s.equals("main")) {
                assertTrue(Files.exists(out.resolve(s + ".csv")), s);
            }
        }
        assertEquals(1 + 6, result.sections().size());
    }

    @Test
    void missingMainStylesheetIsReported() throws Exception {
        Path onlyAccounts = zip(dir.resolve("x.zip"), Map.of("accounts.xslt", "<x/>".getBytes()));
        var e = assertThrows(KdetTransformer.KdetException.class,
                () -> KdetTransformer.run(xmlZip(), onlyAccounts, dir.resolve("out")));
        assertTrue(e.getMessage().contains("main.xslt"));
    }

    @Test
    void zipSlipIsRejected() throws Exception {
        Path evil = zip(dir.resolve("evil.zip"), Map.of("../evil.xml", new byte[0]));
        assertThrows(IOException.class, () -> KdetTransformer.unzip(evil, dir.resolve("x"), ".xml"));
    }
}
