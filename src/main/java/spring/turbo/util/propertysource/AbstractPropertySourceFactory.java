/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.propertysource;

import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PropertySourceFactory;
import org.springframework.lang.Nullable;
import spring.turbo.util.RandomStringUtils;
import spring.turbo.util.StringUtils;

import java.io.IOException;

/**
 * (内部使用)
 *
 * @author 应卓
 * @since 2.0.6
 */
public abstract class AbstractPropertySourceFactory implements PropertySourceFactory {

    private final PropertySourceLoader loader;

    public AbstractPropertySourceFactory(PropertySourceLoader loader) {
        this.loader = loader;
    }

    @Override
    public final PropertySource<?> createPropertySource(@Nullable String name, EncodedResource resource) throws IOException {
        final var propertySourceName = enforcePropertySourceName(name, resource);
        final var list = loader.load(propertySourceName, resource.getResource());

        if (list.isEmpty()) {
            return EmptyPropertySource.of(propertySourceName);
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new IOException("multiple document is NOT supported yet.");
        }
    }

    private String enforcePropertySourceName(@Nullable String name, EncodedResource resource) {
        if (name == null) {
            name = resource.getResource().getFilename();
        }

        if (StringUtils.isBlank(name)) {
            return RandomStringUtils.randomUUID();
        }
        return name;
    }

}
