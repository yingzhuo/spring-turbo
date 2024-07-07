package spring.turbo.core.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import spring.turbo.util.CurrentProcess;

import java.util.Map;

import static spring.turbo.core.SpringApplicationUtils.getHomeDirAsString;

/**
 * 杂项设置
 *
 * @author 应卓
 * @see spring.turbo.bean.injection.ApplicationHome
 * @since 3.3.1
 */
public class MiscPropertiesEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        var propertySource = new MapPropertySource(
                "spring-turbo-misc",
                Map.of(
                        "spring.application.home", getHomeDirAsString(application),
                        "spring.process.pid", CurrentProcess.pid(),
                        "spring.process.parent-pid", CurrentProcess.parentPid(),
                        "spring.process.user", CurrentProcess.user()
                )
        );

        environment.getPropertySources()
                .addLast(propertySource);
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

}
