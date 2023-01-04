/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.lang.Nullable;
import spring.turbo.io.PropertiesFormat;
import spring.turbo.io.ResourceOption;
import spring.turbo.io.ResourceOptions;
import spring.turbo.util.ClassUtils;
import spring.turbo.util.RandomStringUtils;
import spring.turbo.util.StringUtils;
import spring.turbo.util.propertysource.HoconPropertySourceFactory;

import java.io.IOException;

import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * @author 应卓
 * @since 2.0.7
 */
public class SpringTurboEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final Logger log = LoggerFactory.getLogger(SpringTurboEnvironmentPostProcessor.class);

    private static final String[] RESOURCE_LOCATIONS;

    static {
        if (ClassUtils.isPresent("com.typesafe.config.Config")) {
            RESOURCE_LOCATIONS = new String[]{
                    "classpath:spring-turbo.properties",
                    "classpath:spring-turbo.xml",
                    "classpath:spring-turbo.yaml",
                    "classpath:spring-turbo.yml",
                    "classpath:spring-turbo.conf",
                    // ---
                    "classpath:META-INF/spring-turbo.properties",
                    "classpath:META-INF/spring-turbo.xml",
                    "classpath:META-INF/spring-turbo.yaml",
                    "classpath:META-INF/spring-turbo.yml",
                    "classpath:META-INF/spring-turbo.conf",
                    // ----
                    "classpath:config/spring-turbo.properties",
                    "classpath:config/spring-turbo.xml",
                    "classpath:config/spring-turbo.yaml",
                    "classpath:config/spring-turbo.yml",
                    "classpath:config/spring-turbo.conf"
            };
        } else {
            RESOURCE_LOCATIONS = new String[]{
                    "classpath:spring-turbo.properties",
                    "classpath:spring-turbo.xml",
                    "classpath:spring-turbo.yaml",
                    "classpath:spring-turbo.yml",
                    // ---
                    "classpath:META-INF/spring-turbo.properties",
                    "classpath:META-INF/spring-turbo.xml",
                    "classpath:META-INF/spring-turbo.yaml",
                    "classpath:META-INF/spring-turbo.yml",
                    // ---
                    "classpath:config/spring-turbo.properties",
                    "classpath:config/spring-turbo.xml",
                    "classpath:config/spring-turbo.yaml",
                    "classpath:config/spring-turbo.yml"
            };
        }
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        final ResourceOption resourceOption = ResourceOptions.builder()
                .add(RESOURCE_LOCATIONS)
                .build();

        if (resourceOption.isAbsent()) {
            return;
        }

        final var propertySource = toPropertySource(resourceOption);
        if (propertySource != null) {
            final var propertySources = environment.getPropertySources();
            propertySources.addLast(propertySource);
        }
    }

    @Nullable
    private PropertySource<?> toPropertySource(ResourceOption resourceOption) {
        try {
            return doToPropertySource(resourceOption);
        } catch (Exception e) {
            log.warn(e.getMessage(), e);
            return null;
        }
    }

    @Nullable
    private PropertySource<?> doToPropertySource(ResourceOption resourceOption) throws IOException {
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

        if (filename.endsWith(".conf")) {
            return new HoconPropertySourceFactory().createPropertySource(propertySourceName,
                    new EncodedResource(resourceOption.get(), UTF_8));
        }

        return null;
    }

    private String getPropertySourceName(@Nullable String filename) {
        if (StringUtils.isBlank(filename)) {
            filename = RandomStringUtils.randomUUID();
        }
        return filename;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

}
