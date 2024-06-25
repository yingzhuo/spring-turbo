/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core.env;

import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;

import java.util.HashMap;
import java.util.Map;

import static spring.turbo.core.SpringApplicationUtils.getHomeDirAsString;

/**
 * 杂项设置
 *
 * @author 应卓
 * @see spring.turbo.bean.injection.ApplicationHome
 * @since 2.0.8
 */
class MiscellaneousSetupEnvironmentPostProcessor extends EnvironmentPostProcessorSupport {

    private static final String PROPERTY_SOURCE_NAME = "miscellaneous";

    public MiscellaneousSetupEnvironmentPostProcessor(
            DeferredLogFactory logFactory,
            ConfigurableBootstrapContext bootstrapContext) {
        super(logFactory, bootstrapContext);
        setOrder(LOWEST_PRECEDENCE);
    }

    @Override
    public void execute(ConfigurableEnvironment environment, SpringApplication application) {
        final Map<String, Object> map = new HashMap<>();
        map.put("spring.application.home", getHomeDirAsString(application));

        environment.getPropertySources().addLast(new MapPropertySource(PROPERTY_SOURCE_NAME, map));
    }

}
