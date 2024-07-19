package spring.turbo.core;

import org.junit.jupiter.api.Test;

public class ResourceUtilsTest {

    @Test
    void test1() {
        var resources = ResourceUtils.loadResources("classpath*:/*.pem");
        resources.forEach(System.out::println);
    }

}
