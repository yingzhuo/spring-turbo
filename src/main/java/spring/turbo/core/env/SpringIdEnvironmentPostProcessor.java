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
import org.springframework.core.env.MapPropertySource;
import spring.turbo.util.RandomStringUtils;

import java.util.Map;

/**
 * @author 应卓
 * @see spring.turbo.bean.injection.SpringId
 * @see org.springframework.beans.factory.annotation.Value
 * @since 2.0.6
 */
public class SpringIdEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    public static final String SPRING_ID_ENVIRONMENT_KEY = "spring.id";
    public static final String VALUE_ANNOTATION_VALUE = "${" + SPRING_ID_ENVIRONMENT_KEY + "}";

    private final String springId = RandomStringUtils.randomUUID();

    /**
     * 默认构造方法
     */
    public SpringIdEnvironmentPostProcessor() {
        super();
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        final var propertySource = new MapPropertySource(
                "springIdPropertySource",
                Map.of(SPRING_ID_ENVIRONMENT_KEY, springId)
        );

        environment.getPropertySources()
                .addFirst(propertySource);
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE + 1;
    }

}
