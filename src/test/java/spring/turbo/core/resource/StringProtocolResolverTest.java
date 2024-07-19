package spring.turbo.core.resource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.DefaultResourceLoader;
import spring.turbo.core.resource.StringProtocolResolver;
import spring.turbo.util.io.StringResource;

public class StringProtocolResolverTest {

    private final StringProtocolResolver resolver = new StringProtocolResolver();

    @Test
    void test1() {
        var resource = resolver.resolve("string:this is a string", new DefaultResourceLoader());
        Assertions.assertNotNull(resource);
        Assertions.assertEquals(StringResource.class, resource.getClass());
        Assertions.assertEquals("this is a string", resource.toString());
    }

}
