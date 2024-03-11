package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.Car;
import guru.qa.model.Human;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;

import static org.junit.jupiter.api.Assertions.*;

public class JsonTestHomeWork {

    private final ClassLoader cl = JsonTestHomeWork.class.getClassLoader();

    @Test
    void parsingJsonTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files/car.json");
             Reader reader = new InputStreamReader(is, "UTF-8")) {
            ObjectMapper objectMapper = new ObjectMapper();
            Car car = objectMapper.readValue(reader, Car.class);
            assertEquals("BMW", car.getMake());
            assertEquals("1 Series", car.getModel());

        }
    }
}
