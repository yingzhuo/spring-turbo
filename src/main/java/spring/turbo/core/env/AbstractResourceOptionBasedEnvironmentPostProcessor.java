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
import org.springframework.boot.logging.DeferredLog;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.DefaultPropertySourceFactory;
import org.springframework.core.io.support.EncodedResource;
import spring.turbo.core.ApplicationHomeDir;
import spring.turbo.io.RichResource;
import spring.turbo.util.Asserts;
import spring.turbo.util.CollectionUtils;
import spring.turbo.util.RandomStringUtils;
import spring.turbo.util.StringUtils;
import spring.turbo.util.propertysource.HoconPropertySourceFactory;
import spring.turbo.util.propertysource.TomlPropertySourceFactory;
import spring.turbo.util.propertysource.YamlPropertySourceFactory;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

import static spring.turbo.core.Dependencies.*;
import static spring.turbo.util.CharsetPool.UTF_8;
import static spring.turbo.util.StringUtils.endsWithIgnoreCase;

/**
 * @author 应卓
 * @since 2.0.7
 */
public abstract class AbstractResourceOptionBasedEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    protected final DeferredLog log = new DeferredLog();
    private int order = LOWEST_PRECEDENCE;

    @Override
    public final void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        // 初始化日志
        application.addInitializers(ctx -> log.replayTo(getClass()));

        var home = getApplicationHomeDir(application);
        var groups = getResourceOptionGroups(environment, home);

        if (groups == null || groups.isEmpty()) {
            return;
        }

        for (var group : groups) {
            var option = loadResource(group);
            if (option == null) {
                continue;
            }

            var propertySource = toPropertySourceQuietly(option);
            if (propertySource != null) {
                environment.getPropertySources()
                        .addLast(propertySource);
            }
        }

        afterEnvironmentSetup(environment, application);
    }

    protected void afterEnvironmentSetup(ConfigurableEnvironment environment, SpringApplication application) {
        // nop
    }

    @Nullable
    public abstract List<ResourceOptionGroup> getResourceOptionGroups(Environment environment, ApplicationHomeDir home);

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
    private RichResource loadResource(ResourceOptionGroup group) {
        var option = RichResource.builder()
                .blankSafeAddLocations(group.getLocations())
                .discriminator(group.getDiscriminator())
                .build();
        return option.orElse(null);
    }

    @Nullable
    private PropertySource<?> toPropertySourceQuietly(@Nullable RichResource resource) {
        try {
            return toPropertySource(resource);
        } catch (Throwable ignored) {
            return null;
        }
    }

    @Nullable
    private PropertySource<?> toPropertySource(@Nullable RichResource resource) throws Throwable {
        if (resource == null || !resource.exists()) {
            return null;
        }

        final String filename = resource.getFilename();
        final String propertySourceName = generatePropertySourceName(filename);

        if (filename == null) {
            return null;
        }

        if (endsWithIgnoreCase(filename, ".properties") || endsWithIgnoreCase(filename, ".xml")) {
            return new DefaultPropertySourceFactory().createPropertySource(propertySourceName,
                    new EncodedResource(resource));
        }

        if (YAML_PRESENT && (endsWithIgnoreCase(filename, ".yaml") || endsWithIgnoreCase(filename, ".yml"))) {
            return new YamlPropertySourceFactory().createPropertySource(propertySourceName,
                    new EncodedResource(resource, UTF_8)
            );
        }

        if (HOCON_PRESENT && endsWithIgnoreCase(filename, ".conf")) {
            return new HoconPropertySourceFactory().createPropertySource(propertySourceName,
                    new EncodedResource(resource, UTF_8)
            );
        }

        if (TOML_PRESENT && endsWithIgnoreCase(filename, ".toml")) {
            return new TomlPropertySourceFactory().createPropertySource(propertySourceName,
                    new EncodedResource(resource, UTF_8)
            );
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

    public static final class ResourceOptionGroup implements Serializable {

        private final String name;
        private final List<String> locations;
        private final Predicate<Resource> discriminator;

        public ResourceOptionGroup(String name, List<String> locations) {
            this(name, locations, null);
        }

        public ResourceOptionGroup(String name, List<String> locations, @Nullable Predicate<Resource> discriminator) {
            Asserts.hasText(name);
            Asserts.notNull(locations);
            this.name = name;
            this.locations = locations;
            this.discriminator = Objects.requireNonNullElse(discriminator, r -> r != null && r.exists() && r.isReadable());
        }

        public String getName() {
            return name;
        }

        public List<String> getLocations() {
            return locations;
        }

        public Predicate<Resource> getDiscriminator() {
            return discriminator;
        }

        public boolean isEmpty() {
            return locations.isEmpty();
        }
    }

}
