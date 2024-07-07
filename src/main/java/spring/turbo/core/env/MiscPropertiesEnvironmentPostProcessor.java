package spring.turbo.core.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import spring.turbo.util.CurrentProcess;

import java.util.HashMap;
import java.util.Map;

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
        final Map<String, Object> stringObjectMap = new HashMap<>();
        stringObjectMap.put("spring.application.home", SpringApplicationHolders.getApplicationHome().toString());
        stringObjectMap.put("spring.process.pid", CurrentProcess.pid());
        stringObjectMap.put("spring.process.parent-pid", CurrentProcess.parentPid());
        stringObjectMap.put("spring.process.user", CurrentProcess.user());

        var propertySource = new MapPropertySource(
                "spring-turbo-misc",
                stringObjectMap
        );

        environment.getPropertySources()
                .addLast(propertySource);
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

}
