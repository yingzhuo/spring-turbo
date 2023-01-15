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
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import spring.turbo.util.CollectionUtils;

import java.util.Map;

import static spring.turbo.util.StringPool.EMPTY;

/**
 * @author 应卓
 * @see spring.turbo.bean.injection.ApplicationHome
 * @since 2.0.8
 */
public class ApplicationHomeEnvironmentPostProcessor implements EnvironmentPostProcessor {

    public static final String SPRING_APPLICATION_HOME_ENVIRONMENT_KEY = "spring.application.home";
    public static final String VALUE_ANNOTATION_VALUE = "${" + SPRING_APPLICATION_HOME_ENVIRONMENT_KEY + "}";

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        var home = EMPTY;
        var sourceClasses = application.getAllSources()
                .stream()
                .filter(o -> o instanceof Class<?>)
                .map(o -> (Class<?>) o)
                .toList();

        if (CollectionUtils.size(sourceClasses) == 1) {
            home = new ApplicationHome(sourceClasses.get(0)).getDir().getAbsolutePath();
        } else {
            home = new ApplicationHome().getDir().getAbsolutePath();
        }

        var propertySource = new MapPropertySource("application-home", Map.of(SPRING_APPLICATION_HOME_ENVIRONMENT_KEY, home));
        environment
                .getPropertySources()
                .addLast(propertySource);
    }

}
