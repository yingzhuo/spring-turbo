package spring.turbo.util.text;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.validation.DataBinder;

import java.io.Serializable;

public class TextVariablesTest {

    private static final String text = """
            name  = yingzhuo
            email = yingzhor@gmail.com
            """;

    @Test
    void test1() {
        var tv = TextVariables.of(text);
        System.out.println(tv);

        var pojo = new Pojo();
        var dataBinder = new DataBinder(pojo);
        dataBinder.setConversionService(new DefaultFormattingConversionService(true));
        dataBinder.bind(tv.toPropertyValues());

        Assertions.assertEquals("yingzhuo", pojo.getName());
        Assertions.assertEquals("yingzhor@gmail.com", pojo.getEmail());
    }

    @Data
    private static class Pojo implements Serializable {
        private String name;
        private String email;
    }

}
