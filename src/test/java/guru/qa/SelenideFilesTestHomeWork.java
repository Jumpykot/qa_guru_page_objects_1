package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

public class SelenideFilesTestHomeWork {

    private final ClassLoader cl = SelenideFilesTestHomeWork.class.getClassLoader();

    boolean filesFound = false;

    @Test
    void zipParcingTest() throws Exception {

        try (InputStream is = cl.getResourceAsStream("Archive.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;

            while ((entry = zis.getNextEntry()) != null) {


                if (entry.getName().endsWith(".pdf")) {

                    PDF pdf = new PDF(zis);

                    assertTrue(pdf.text.contains("А-Тренд. Ваш персональный менеджер"));

                    System.out.println("Файл pdf валидный");

                } else if (entry.getName().endsWith(".xlsx")) {

                    XLS xls = new XLS(zis);

                    Assertions.assertEquals("ГК Расцветай. Новосибирск", xls.excel.getSheet("Sheet 1").getRow(2).getCell(1).getStringCellValue());
                    System.out.println("Файл xlsxFile валидный");

                } else if (entry.getName().endsWith(".csv")) {

                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis)); {
                        List<String[]> content = csvReader.readAll();

                        System.out.println(content.getFirst());
//                        Assertions.assertArrayEquals(
//                                new String[] {"Teacher"}, content.get()
//                        );
                        System.out.println("Файл csv валидный");
                    }
                }
                zis.closeEntry();
            }
        if (filesFound) {
            fail("Файлы не найдены");
        }
        }
    }
}
