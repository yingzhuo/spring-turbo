package spring.turbo.util.io;

import org.junit.jupiter.api.Test;

import java.io.IOException;

public class RichResourceTest {

    public static final String text = """
            my name is 应卓
            hello
            hello, again
            """;

    @Test
    void test1() throws IOException {
        RichResource.of(new StringResource(text))
                .getLinesAsStream()
                .forEach(System.out::println);
    }

}
