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
import org.springframework.core.env.ConfigurableEnvironment;
import spring.turbo.io.ResourceOptions;

/**
 * @author 应卓
 * @since 2.0.7
 */
public class GitInformationEnvironmentPostProcessor extends AbstractResourceBasedEnvironmentPostProcessor {

    public GitInformationEnvironmentPostProcessor() {
        super(0);
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        final var propertySource = toPropertySource(
                ResourceOptions.builder()
                        .add("classpath:git.properties")
                        .add("classpath:config/git.properties")
                        .add("classpath:META-INF/git.properties")
                        .build()
        );
        if (propertySource == null) {
            return;
        }
        environment.getPropertySources()
                .addFirst(propertySource);
    }

}
