package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("Чтения и проверка файлов PDF, ZIP, XLSX из zip")
public class ZipTestHomeWork {
    private final ClassLoader cl = ZipTestHomeWork.class.getClassLoader();

    @Test
    @DisplayName("Чтения и проверка CSV файла из архива zip")
    void zipParsingCsvTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("A.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    assertThat(content.get(0)).isEqualTo(new String[]{"make", "model"});
                }
            }
        }
    }


    @Test
    @DisplayName("Чтения и проверка XLSX файла из архива ZIP")
    void zipXlsxParsingTest() throws IOException {
        try (InputStream is = cl.getResourceAsStream("A.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".xlsx") && !entry.getName().startsWith("__MACOSX/")) {
                    XLS xls = new XLS(zis);
                    String name = xls.excel.getSheet("Sheet1").getRow(2).getCell(1).getStringCellValue();
                    assertThat(name).isEqualTo("Doug");
                }
            }
        }
    }

    @Test
    @DisplayName("Чтение и проверка  PDF файла из архива ZIP")
    void zipPdfParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("A.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().endsWith(".pdf") && !entry.getName().startsWith("__MACOSX/")) {
                    PDF pdf = new PDF(zis);
                    assertTrue(pdf.text.contains("JUnit 5 User Guide"));
                }
            }
        }
    }
}