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

import java.util.ArrayList;

import static spring.turbo.core.Dependencies.IS_HOCON_PRESENT;
import static spring.turbo.core.Dependencies.IS_TOML_PRESENT;

/**
 * @author 应卓
 * @since 2.0.7
 */
public class SpringTurboEnvironmentPostProcessor extends ResourceBasedEnvironmentPostProcessorSupport {

    /**
     * 默认构造方法
     */
    public SpringTurboEnvironmentPostProcessor() {
        super(Ordered.LOWEST_PRECEDENCE - 100);
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        final ResourceOption resourceOption = loadResource();

        if (resourceOption.isAbsent()) {
            return;
        }

        final var propertySource = toPropertySource(resourceOption);
        if (propertySource != null) {
            final var propertySources = environment.getPropertySources();
            propertySources.addLast(propertySource);
        }
    }

    private ResourceOption loadResource() {
        final var resourceLocations = new ArrayList<String>();
        resourceLocations.add("classpath:spring-turbo.properties");
        resourceLocations.add("classpath:spring-turbo.xml");
        resourceLocations.add("classpath:spring-turbo.yaml");
        resourceLocations.add("classpath:spring-turbo.yml");
        if (IS_HOCON_PRESENT) {
            resourceLocations.add("classpath:spring-turbo.conf");
        }
        if (IS_TOML_PRESENT) {
            resourceLocations.add("classpath:spring-turbo.toml");
        }
        // ---
        resourceLocations.add("classpath:META-INF/spring-turbo.properties");
        resourceLocations.add("classpath:META-INF/spring-turbo.xml");
        resourceLocations.add("classpath:META-INF/spring-turbo.yaml");
        resourceLocations.add("classpath:META-INF/spring-turbo.yml");
        if (IS_HOCON_PRESENT) {
            resourceLocations.add("classpath:META-INF/spring-turbo.conf");
        }
        if (IS_TOML_PRESENT) {
            resourceLocations.add("classpath:META-INF/spring-turbo.toml");
        }
        // ---
        resourceLocations.add("classpath:conf/spring-turbo.properties");
        resourceLocations.add("classpath:conf/spring-turbo.xml");
        resourceLocations.add("classpath:conf/spring-turbo.yaml");
        resourceLocations.add("classpath:conf/spring-turbo.yml");
        if (IS_HOCON_PRESENT) {
            resourceLocations.add("classpath:conf/spring-turbo.conf");
        }
        if (IS_TOML_PRESENT) {
            resourceLocations.add("classpath:conf/spring-turbo.toml");
        }

        return ResourceOptions.builder()
                .add(resourceLocations)
                .build();
    }

}
