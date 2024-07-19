package spring.turbo.util.io;

import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;

/**
 * @author 应卓
 * @see StringResource
 * @since 3.3.2
 */
public class StringProtocolResolver implements ProtocolResolver {

    private static final String STRING_PREFIX = "string:";

    @Nullable
    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {
        if (location.startsWith(STRING_PREFIX)) {
            var value = location.substring(STRING_PREFIX.length());
            return new StringResource(value);
        }
        return null;
    }

}
