package spring.turbo.core.resource;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.ProtocolResolver;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.Nullable;
import spring.turbo.util.HexUtils;

/**
 * @author 应卓
 * @see ByteArrayResource
 * @since 3.3.2
 */
public class HexProtocolResolver implements ProtocolResolver {

    private static final String HEX_PREFIX = "hex:";

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public Resource resolve(String location, ResourceLoader resourceLoader) {
        if (location.startsWith(HEX_PREFIX)) {
            var value = location.substring(HEX_PREFIX.length());
            return new ByteArrayResource(HexUtils.decodeToBytes(value));
        }
        return null;
    }

}
