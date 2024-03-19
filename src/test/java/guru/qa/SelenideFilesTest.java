package guru.qa;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.opencsv.CSVReader;
import guru.qa.model.Human;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SelenideFilesTest {

    private final ClassLoader cl = SelenideFilesTest.class.getClassLoader();
    private final Gson gson = new Gson();

    @Test
    void downLoadFileTest() throws Exception {
        open("https://github.com/junit-team/junit5/blob/main/README.md");
        File dowloaded = $("a[data-testid='raw-button']")
                .download();

        InputStream is = new FileInputStream(dowloaded);
        byte[] content = is.readAllBytes();
        String contentAsString = new String(content, StandardCharsets.UTF_8);
        assertTrue(contentAsString.contains("Contributions to JUnit 5 are both welcomed and appreciated."));
        is.close();
    }

    @Test
    public void testPdfContainsText() throws IOException {

        PDF pdf = new PDF(cl.getResourceAsStream("files/comparison.pdf"));

        assertTrue(pdf.text.contains("А-Тренд. Ваш персональный менеджер"));
    }

    @Test
    void xlsxParsingTest() throws IOException {
        XLS xls = new XLS(cl.getResourceAsStream("files/parkingPrices.xlsx"));

        Assertions.assertEquals("ГК Расцветай. Новосибирск", xls.excel.getSheet("Sheet 1").getRow(2).getCell(1).getStringCellValue());
    }


    @Test
    void csvParsingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files/qa_guru.csv");
             CSVReader csvReader = new CSVReader(new InputStreamReader(is))) {
            List<String[]> content = csvReader.readAll();
            Assertions.assertArrayEquals(
                    new String[]{"Teacher", "lesson"}, content.get(0)
            );
            Assertions.assertArrayEquals(
                    new String[]{"Tuchs", "Files"}, content.get(1)
            );
        }
    }

    @Test
    void zipParcingTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("A.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                System.out.println(entry.getName());
            }
        }
    }

    @Test
    void jsonParcingTestNextLevel() throws Exception {
        try (InputStream is = cl.getResourceAsStream("human.json");
            Reader reader = new InputStreamReader(is)) {
            Human object = gson.fromJson(reader, Human.class);

            Assertions.assertEquals("Vlad", object.name);
            Assertions.assertEquals(29, object.age);
            Assertions.assertArrayEquals(new String[] {"football", "video games"},
                   object.hobby.toArray()
            );
            Assertions.assertEquals("МВД", object.passport.issuer);
        }
    }

}


