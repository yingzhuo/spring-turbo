/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core.support;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;
import spring.turbo.io.PropertiesFormat;
import spring.turbo.io.ResourceOption;
import spring.turbo.util.RandomStringUtils;
import spring.turbo.util.StringUtils;
import spring.turbo.util.propertysource.HoconPropertySourceFactory;
import spring.turbo.util.propertysource.TomlPropertySourceFactory;

import java.io.IOException;

import static spring.turbo.core.Dependencies.IS_HOCON_PRESENT;
import static spring.turbo.core.Dependencies.IS_TOML_PRESENT;
import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * (内部使用)
 *
 * @author 应卓
 * @since 2.0.7
 */
public abstract class ResourceBasedEnvironmentPostProcessorSupport implements EnvironmentPostProcessor, Ordered {

    private static final Logger log = LoggerFactory.getLogger(ResourceBasedEnvironmentPostProcessorSupport.class);

    private final int order;

    public ResourceBasedEnvironmentPostProcessorSupport(int order) {
        this.order = order;
    }

    @Nullable
    protected final PropertySource<?> toPropertySource(@Nullable ResourceOption resourceOption) {
        try {
            return doToPropertySource(resourceOption);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return null;
        }
    }

    @Nullable
    private PropertySource<?> doToPropertySource(@Nullable ResourceOption resourceOption) throws IOException {
        if (resourceOption == null || resourceOption.isAbsent()) {
            return null;
        }

        final String filename = resourceOption.get().getFilename();
        final String propertySourceName = getPropertySourceName(filename);

        if (filename == null) {
            return null;
        }

        if (filename.endsWith(".properties")) {
            return new PropertiesPropertySource(
                    propertySourceName,
                    resourceOption.toProperties(PropertiesFormat.PROPERTIES));
        }

        if (filename.endsWith(".xml")) {
            return new PropertiesPropertySource(
                    propertySourceName,
                    resourceOption.toProperties(PropertiesFormat.XML));
        }

        if (filename.endsWith(".yaml") || filename.endsWith(".yml")) {
            return new PropertiesPropertySource(
                    propertySourceName,
                    resourceOption.toProperties(PropertiesFormat.YAML));
        }

        if (IS_HOCON_PRESENT && filename.endsWith(".conf")) {
            return new HoconPropertySourceFactory().createPropertySource(propertySourceName,
                    new EncodedResource(resourceOption.get(), UTF_8));
        }

        if (IS_TOML_PRESENT && filename.endsWith(".toml")) {
            return new TomlPropertySourceFactory().createPropertySource(propertySourceName,
                    new EncodedResource(resourceOption.get(), UTF_8));
        }

        return null;
    }

    protected final String getPropertySourceName(@Nullable String filename) {
        if (StringUtils.isBlank(filename)) {
            filename = RandomStringUtils.randomUUID();
        }
        return filename;
    }

    @Override
    public final int getOrder() {
        return this.order;
    }

}
