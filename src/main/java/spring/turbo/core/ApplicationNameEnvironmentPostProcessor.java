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
import spring.turbo.util.StringFormatter;
import spring.turbo.util.StringUtils;
import spring.turbo.util.propertysource.HoconPropertySourceFactory;

import java.io.IOException;
import java.util.ArrayList;

import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * @author 应卓
 * @since 2.0.7
 */
public class ApplicationNameEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final Logger log = LoggerFactory.getLogger(ApplicationNameEnvironmentPostProcessor.class);

    private static final String SPRING_APPLICATION_NAME = "spring.application.name";
    private static final boolean HOCON_DEP_PRESENT = ClassUtils.isPresent("com.typesafe.config.Config");

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        var appName = environment.getProperty(SPRING_APPLICATION_NAME);

        if (StringUtils.isBlank(appName)) {
            return;
        }

        final var resourceOption = this.loadResource(appName);
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

        if (HOCON_DEP_PRESENT && filename.endsWith(".conf")) {
            return new HoconPropertySourceFactory().createPropertySource(propertySourceName,
                    new EncodedResource(resourceOption.get(), UTF_8));
        }

        return null;
    }

    private ResourceOption loadResource(String appName) {
        final var resourceLocations = new ArrayList<String>();
        resourceLocations.add(StringFormatter.format("classpath:{}.properties", appName));
        resourceLocations.add(StringFormatter.format("classpath:{}.xml", appName));
        resourceLocations.add(StringFormatter.format("classpath:{}.yaml", appName));
        resourceLocations.add(StringFormatter.format("classpath:{}.yml", appName));
        if (HOCON_DEP_PRESENT) {
            resourceLocations.add(StringFormatter.format("classpath:{}.conf", appName));
        }
        // ---
        resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.properties", appName));
        resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.xml", appName));
        resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.yaml", appName));
        resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.yml", appName));
        if (HOCON_DEP_PRESENT) {
            resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.conf", appName));
        }
        // ---
        resourceLocations.add(StringFormatter.format("classpath:conf/{}.properties", appName));
        resourceLocations.add(StringFormatter.format("classpath:conf/{}.xml", appName));
        resourceLocations.add(StringFormatter.format("classpath:conf/{}.yaml", appName));
        resourceLocations.add(StringFormatter.format("classpath:conf/{}.yml", appName));
        if (HOCON_DEP_PRESENT) {
            resourceLocations.add(StringFormatter.format("classpath:conf/{}.conf", appName));
        }

        return ResourceOptions.builder()
                .add(resourceLocations)
                .build();
    }

    private String getPropertySourceName(@Nullable String filename) {
        if (StringUtils.isBlank(filename)) {
            filename = RandomStringUtils.randomUUID();
        }
        return filename;
    }

    @Override
    public final int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

}
