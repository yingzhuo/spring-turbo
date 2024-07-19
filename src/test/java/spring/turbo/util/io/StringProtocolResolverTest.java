package spring.turbo.util.io;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;

public class StringProtocolResolverTest {

    private StringProtocolResolver resolver = new StringProtocolResolver();

    @Test
    void test1() {
        var resource = resolver.resolve("string:this is a string", new DefaultResourceLoader());
        Assertions.assertNotNull(resource);
        Assertions.assertEquals(StringResource.class, resource.getClass());
        Assertions.assertEquals("this is a string", resource.toString());
    }

}
