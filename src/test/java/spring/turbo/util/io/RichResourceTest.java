package spring.turbo.util.io;

import org.junit.jupiter.api.Test;
import spring.turbo.util.StringUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class RichResourceTest {

    public static final String text = """
            my name is 应卓
            hello
            hello, again
            """;

    @Test
    void test1() throws IOException {
        RichResource.of(new StringResource(text))
                .getLinesAsStream(StandardCharsets.UTF_8)
                .filter(StringUtils::isNotBlank)
                .forEach(System.out::println);
    }

}
