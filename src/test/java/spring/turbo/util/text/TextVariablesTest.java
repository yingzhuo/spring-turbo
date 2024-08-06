package spring.turbo.util.text;

import lombok.Data;
import org.junit.jupiter.api.Test;
import spring.turbo.core.DataBinderUtils;

import java.io.Serializable;

public class TextVariablesTest {

    private static final String text = """
            name  = yingzhuo
            email = yingzhor@gmail.com
            """;

    @Test
    void test1() {
        var binder = DataBinderUtils.createDataBinder(new Pojo());

        binder.bind(TextVariables.of(text).toPropertyValues());
        var pojo = (Pojo) binder.getTarget();

        System.out.println(pojo.getName());
        System.out.println(pojo.getEmail());
    }

    @Data
    private static class Pojo implements Serializable {
        private String name;
        private String email;
    }

}
