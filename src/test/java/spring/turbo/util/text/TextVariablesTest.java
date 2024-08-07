package spring.turbo.util.text;

import lombok.Data;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.turbo.core.DataBinderUtils;

import java.io.Serializable;

public class TextVariablesTest {

    private static final String text = """
            name  = yingzhuo
            age = 1;
            email = yingzhor@gmail.com
            """;

    @Test
    void test1() {
        var binder = DataBinderUtils.createDataBinder(new Pojo());

        binder.bind(TextVariables.valueOf(text));
        var pojo = (Pojo) binder.getTarget();

        Assertions.assertEquals("yingzhuo", pojo.getName());
        Assertions.assertEquals("yingzhor@gmail.com", pojo.getEmail());
        Assertions.assertEquals(1, pojo.getAge());
    }

    @Data
    private static class Pojo implements Serializable {
        private String name;
        private String email;
        private int age;
    }

}
