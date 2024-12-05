package spring.turbo.core;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.Map;

public class SpELTest {

    @Test
    public void test1() {
        var rootObject = new Cat("米来", "F");
        var variables = Map.<String, Object>of(
                "author", "应卓",
                "authorEmail", "yingzhor@gmail.com"
        );

        var exp = "#authorEmail";

        var ret = SpEL.getValue(exp, rootObject, variables);
        System.out.println(ret);
    }

    private record Cat(String name, String gender) implements Serializable {
    }

}
