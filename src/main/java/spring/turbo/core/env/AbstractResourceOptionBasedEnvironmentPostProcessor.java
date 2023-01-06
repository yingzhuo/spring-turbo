/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertiesPropertySource;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.support.EncodedResource;
import spring.turbo.core.ApplicationHomeDir;
import spring.turbo.io.PropertiesFormat;
import spring.turbo.io.ResourceOption;
import spring.turbo.io.ResourceOptionDiscriminator;
import spring.turbo.io.ResourceOptions;
import spring.turbo.util.Asserts;
import spring.turbo.util.CollectionUtils;
import spring.turbo.util.RandomStringUtils;
import spring.turbo.util.StringUtils;
import spring.turbo.util.propertysource.HoconPropertySourceFactory;
import spring.turbo.util.propertysource.TomlPropertySourceFactory;

import javax.annotation.Nullable;
import java.util.List;
import java.util.Objects;

import static spring.turbo.core.Dependencies.*;
import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * @author 应卓
 * @since 2.0.7
 */
public abstract class AbstractResourceOptionBasedEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private int order = Ordered.LOWEST_PRECEDENCE;

    @Override
    public final void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        var applicationHomeDir = getApplicationHomeDir(application);
        var groups = getResourceOptionGroups(environment, applicationHomeDir);

        if (groups == null || groups.isEmpty()) {
            return;
        }

        for (var group : groups) {
            var option = loadResource(group);
            if (option == null || option.isAbsent()) {
                continue;
            }

            var propertySource = toPropertySourceQuietly(option);
            if (propertySource != null) {
                environment.getPropertySources()
                        .addLast(propertySource);
            }
        }
    }

    @Nullable
    public abstract List<ResourceOptionGroup> getResourceOptionGroups(Environment environment, ApplicationHomeDir applicationHomeDir);

    @Override
    public final int getOrder() {
        return order;
    }

    public final void setOrder(int order) {
        this.order = order;
    }

    private ApplicationHomeDir getApplicationHomeDir(SpringApplication application) {
        final var sourceClasses = application.getAllSources()
                .stream()
                .filter(o -> o instanceof Class<?>)
                .map(o -> (Class<?>) o)
                .toList();

        if (CollectionUtils.isEmpty(sourceClasses)) {
            return ApplicationHomeDir.of();
        }

        if (sourceClasses.size() == 1) {
            return ApplicationHomeDir.of(sourceClasses.get(0));
        }

        return ApplicationHomeDir.of();
    }

    @Nullable
    private ResourceOption loadResource(ResourceOptionGroup group) {
        var option = ResourceOptions.builder()
                .add(group.getLocations())
                .discriminator(group.getDiscriminator())
                .build();

        return option.isPresent() ? option : null;
    }

    @Nullable
    private PropertySource<?> toPropertySourceQuietly(@Nullable ResourceOption resourceOption) {
        try {
            return toPropertySource(resourceOption);
        } catch (Throwable ignored) {
            return null;
        }
    }

    @Nullable
    private PropertySource<?> toPropertySource(@Nullable ResourceOption resourceOption) throws Throwable {
        if (resourceOption == null || resourceOption.isAbsent()) {
            return null;
        }

        final String filename = resourceOption.get().getFilename();
        final String propertySourceName = generatePropertySourceName(filename);

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

        if (YAML_PRESENT && (filename.endsWith(".yaml") || filename.endsWith(".yml"))) {
            return new PropertiesPropertySource(
                    propertySourceName,
                    resourceOption.toProperties(PropertiesFormat.YAML));
        }

        if (HOCON_PRESENT && filename.endsWith(".conf")) {
            return new HoconPropertySourceFactory().createPropertySource(propertySourceName,
                    new EncodedResource(resourceOption.get(), UTF_8));
        }

        if (TOML_PRESENT && filename.endsWith(".toml")) {
            return new TomlPropertySourceFactory().createPropertySource(propertySourceName,
                    new EncodedResource(resourceOption.get(), UTF_8));
        }

        return null;
    }

    private String generatePropertySourceName(@Nullable String filename) {
        if (StringUtils.isBlank(filename)) {
            filename = RandomStringUtils.randomUUID();
        }
        return filename;
    }

    // -----------------------------------------------------------------------------------------------------------------

    protected final class ResourceOptionGroup {

        private final String name;
        private final List<String> locations;
        private final ResourceOptionDiscriminator discriminator;

        public ResourceOptionGroup(String name, List<String> locations) {
            this(name, locations, null);
        }

        public ResourceOptionGroup(String name, List<String> locations, @Nullable ResourceOptionDiscriminator discriminator) {
            Asserts.hasText(name);
            Asserts.notNull(locations);
            Asserts.isTrue(locations.size() > 0);
            this.name = name;
            this.locations = locations;
            this.discriminator = Objects.requireNonNullElseGet(discriminator, ResourceOptionDiscriminator::newDefault);
        }

        public String getName() {
            return name;
        }

        public List<String> getLocations() {
            return locations;
        }

        public ResourceOptionDiscriminator getDiscriminator() {
            return discriminator;
        }
    }

}
