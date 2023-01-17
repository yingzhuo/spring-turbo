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
import org.springframework.core.env.MapPropertySource;

import java.util.Map;

import static spring.turbo.util.RandomStringUtils.randomUUID;

/**
 * 杂项设置
 *
 * @author 应卓
 * @since 2.0.8
 */
public class MiscellaneousSetupEnvironmentPostProcessor extends EnvironmentPostProcessorSupport {

    private static final String PROPERTY_SOURCE_NAME = "miscellaneous";
    private static final String SPRING_ID = randomUUID();

    /**
     * 默认构造方法
     */
    public MiscellaneousSetupEnvironmentPostProcessor() {
        setOrder(LOWEST_PRECEDENCE);
    }

    @Override
    public void execute(ConfigurableEnvironment environment, SpringApplication application) {

        var map = Map.<String, Object>of(
                "spring.application.id", SPRING_ID,
                "spring.application.home", getHomeDir(application).getAbsolutePath()
        );

        environment
                .getPropertySources()
                .addLast(new MapPropertySource(PROPERTY_SOURCE_NAME, map));
    }

}
