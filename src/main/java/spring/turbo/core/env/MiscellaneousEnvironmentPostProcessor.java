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
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import spring.turbo.util.CollectionUtils;

import java.util.Map;

import static spring.turbo.util.RandomStringUtils.randomUUID;

/**
 * 杂项设置
 *
 * @author 应卓
 * @since 2.0.8
 */
public class MiscellaneousEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final String PROPERTY_SOURCE_NAME = "miscellaneous";
    private static final String SPRING_ID = randomUUID();

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        var map = Map.<String, Object>of(
                "spring.application.id", SPRING_ID,
                "spring.application.home", getApplicationHome(application)
        );

        environment
                .getPropertySources()
                .addLast(new MapPropertySource(PROPERTY_SOURCE_NAME, map));
    }

    private String getApplicationHome(SpringApplication application) {
        var sourceClasses = application.getAllSources()
                .stream()
                .filter(o -> o instanceof Class<?>)
                .map(o -> (Class<?>) o)
                .toList();

        if (CollectionUtils.size(sourceClasses) == 1) {
            return new ApplicationHome(sourceClasses.get(0))
                    .getDir()
                    .getAbsolutePath();
        } else {
            return new ApplicationHome()
                    .getDir()
                    .getAbsolutePath();
        }
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

}
