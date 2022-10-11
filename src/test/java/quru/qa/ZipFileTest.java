package quru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Objects;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class ZipFileTest {

    ClassLoader classLoader = ZipFileTest.class.getClassLoader();

    @Test
    @DisplayName("ZipCsv test")
    void zipCsvTest() throws Exception {
        try (ZipFile zipFile = new ZipFile(new File("src/test/resources/zip/csvData.zip"))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(classLoader.getResourceAsStream("zip/csvData.zip")));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".csv")) {
                    try (InputStream inputStream = zipFile.getInputStream(entry)) {
                        CSVReader reader = new CSVReader(new InputStreamReader(inputStream));
                        List<String[]> content = reader.readAll();
                        String[] row = content.get(0);
                        assertThat(row[0]).isEqualTo("Tom");
                        assertThat(row[1]).isEqualTo("10");
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("ZipPdf test")
    void zipPdfTest() throws Exception {
        try (ZipFile zipFile = new ZipFile(new File("src/test/resources/zip/pdfData.zip"))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(classLoader.getResourceAsStream("zip/pdfData.zip")));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".pdf")) {
                    try (InputStream inputStream = zipFile.getInputStream(entry)) {
                        PDF pdf = new PDF(inputStream);
                        assertThat(pdf.author).isEqualTo("Артур Мустафин");
                    }
                }
            }
        }
    }

    @Test
    @DisplayName("ZipXlsx test")
    void zipXlsxTest() throws Exception {
        try (ZipFile zipFile = new ZipFile(new File("src/test/resources/zip/xlsxData.zip"))) {
            ZipInputStream is = new ZipInputStream(Objects.requireNonNull(classLoader.getResourceAsStream("zip/xlsxData.zip")));
            ZipEntry entry;
            while ((entry = is.getNextEntry()) != null) {
                if (entry.getName().contains(".xlsx")) {
                    try (InputStream inputStream = zipFile.getInputStream(entry)) {
                        XLS xls = new XLS(inputStream);
                        assertThat(
                                xls.excel.getSheetAt(0)
                                        .getRow(2)
                                        .getCell(2)
                                        .getStringCellValue()
                        ).isEqualTo("Walker");
                    }
                }
            }
        }
    }
}
