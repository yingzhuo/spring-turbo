/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.boot.SpringApplication;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import spring.turbo.core.support.ResourceBasedEnvironmentPostProcessorSupport;
import spring.turbo.io.ResourceOption;
import spring.turbo.io.ResourceOptions;
import spring.turbo.util.StringFormatter;
import spring.turbo.util.StringUtils;

import java.util.ArrayList;

import static spring.turbo.core.Dependencies.IS_HOCON_PRESENT;
import static spring.turbo.core.Dependencies.IS_TOML_PRESENT;

/**
 * @author 应卓
 * @since 2.0.7
 */
public class ApplicationNameEnvironmentPostProcessor extends ResourceBasedEnvironmentPostProcessorSupport {

    private static final String SPRING_APPLICATION_NAME = "spring.application.name";

    /**
     * 默认构造方法
     */
    public ApplicationNameEnvironmentPostProcessor() {
        super(Ordered.LOWEST_PRECEDENCE);
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        var appName = environment.getProperty(SPRING_APPLICATION_NAME);

        if (StringUtils.isBlank(appName)) {
            return;
        }

        final var propertySource = super.toPropertySource(loadResource(appName, application));
        if (propertySource != null) {
            final var propertySources = environment.getPropertySources();
            propertySources.addLast(propertySource);
        }
    }

    private ResourceOption loadResource(String filename, SpringApplication application) {
        final var resourceLocations = new ArrayList<String>();

        // ---
        for (var appDir : super.getApplicationDirectories(application)) {
            System.out.println(appDir);
            resourceLocations.add(StringFormatter.format("file:{}/{}.properties", appDir, filename));
            resourceLocations.add(StringFormatter.format("file:{}/{}.xml", appDir, filename));
            resourceLocations.add(StringFormatter.format("file:{}/{}.yaml", appDir, filename));
            resourceLocations.add(StringFormatter.format("file:{}/{}.yml", appDir, filename));
            if (IS_HOCON_PRESENT) {
                resourceLocations.add(StringFormatter.format("file:{}/{}.conf", appDir));
            }
            if (IS_TOML_PRESENT) {
                resourceLocations.add(StringFormatter.format("file:{}/{}.toml", appDir));
            }
        }

        // ---
        resourceLocations.add(StringFormatter.format("classpath:{}.properties", filename));
        resourceLocations.add(StringFormatter.format("classpath:{}.xml", filename));
        resourceLocations.add(StringFormatter.format("classpath:{}.yaml", filename));
        resourceLocations.add(StringFormatter.format("classpath:{}.yml", filename));
        if (IS_HOCON_PRESENT) {
            resourceLocations.add(StringFormatter.format("classpath:{}.conf", filename));
        }
        if (IS_TOML_PRESENT) {
            resourceLocations.add(StringFormatter.format("classpath:{}.toml", filename));
        }

        // ---
        resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.properties", filename));
        resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.xml", filename));
        resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.yaml", filename));
        resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.yml", filename));
        if (IS_HOCON_PRESENT) {
            resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.conf", filename));
        }
        if (IS_TOML_PRESENT) {
            resourceLocations.add(StringFormatter.format("classpath:META-INF/{}.toml", filename));
        }

        // ---
        resourceLocations.add(StringFormatter.format("classpath:conf/{}.properties", filename));
        resourceLocations.add(StringFormatter.format("classpath:conf/{}.xml", filename));
        resourceLocations.add(StringFormatter.format("classpath:conf/{}.yaml", filename));
        resourceLocations.add(StringFormatter.format("classpath:conf/{}.yml", filename));
        if (IS_HOCON_PRESENT) {
            resourceLocations.add(StringFormatter.format("classpath:conf/{}.conf", filename));
        }
        if (IS_TOML_PRESENT) {
            resourceLocations.add(StringFormatter.format("classpath:conf/{}.toml", filename));
        }

        return ResourceOptions.builder()
                .add(resourceLocations)
                .build();
    }

}
