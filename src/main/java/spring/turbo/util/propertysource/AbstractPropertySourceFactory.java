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
import java.util.List;

/**
 * (内部使用)
 *
 * @author 应卓
 * @since 2.0.6
 */
abstract class AbstractPropertySourceFactory implements PropertySourceFactory {

    private final PropertySourceLoader loader;

    public AbstractPropertySourceFactory(PropertySourceLoader loader) {
        this.loader = loader;
    }

    @Override
    public final PropertySource<?> createPropertySource(String name, EncodedResource resource) throws IOException {
        List<PropertySource<?>> list = loader.load(getPropertySourceName(name), resource.getResource());

        if (list.isEmpty()) {
            return new EmptyPropertySource();
        } else if (list.size() == 1) {
            return list.get(0);
        } else {
            throw new IOException("multiple document is NOT supported yet.");
        }
    }

    private String getPropertySourceName(@Nullable String name) {
        if (StringUtils.isBlank(name)) {
            return RandomStringUtils.randomUUID(true);
        }
        return name;
    }

    // -----------------------------------------------------------------------------------------------------------------

    protected static class EmptyPropertySource extends PropertySource<Object> {
        public EmptyPropertySource() {
            super("empty", new Object());
        }

        @Override
        public Object getProperty(String name) {
            return null;
        }
    }
}
