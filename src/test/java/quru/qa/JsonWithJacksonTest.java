package quru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import quru.qa.model.Cat;
import java.io.File;

import static com.codeborne.pdftest.assertj.Assertions.assertThat;

public class JsonWithJacksonTest {

    @Test
    void jsonCheckValueTest() throws Exception {
        File file = new File("src/test/resources/json/exampleJson.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Cat cat = objectMapper.readValue(file, Cat.class);
        assertThat(cat.name).isEqualTo("Tom");
        assertThat(cat.age).isEqualTo(3);
    }
}
