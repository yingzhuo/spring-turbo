package spring.turbo.core.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MapPropertySource;
import spring.turbo.util.CurrentProcess;

import java.util.HashMap;
import java.util.Optional;

/**
 * 杂项设置
 *
 * @author 应卓
 * @see spring.turbo.bean.injection.ApplicationHome
 * @see spring.turbo.bean.injection.ApplicationPid
 * @see spring.turbo.bean.injection.ApplicationParentPid
 * @see spring.turbo.bean.injection.ApplicationProcessUser
 * @since 3.3.1
 */
public class MiscPropertiesEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        final var variables = new HashMap<String, Object>();
        variables.put("spring.process.pid", CurrentProcess.pid());
        variables.put("spring.process.parent-pid", CurrentProcess.parentPid());
        variables.put("spring.process.user", CurrentProcess.user());

        Optional.ofNullable(SpringApplicationHolders.getApplicationHome()).ifPresent(
                home -> variables.put("spring.application.home", home.toAbsolutePath().toString())
        );

        var propertySource = new MapPropertySource(
                "spring-turbo-misc",
                variables
        );

        environment.getPropertySources()
                .addLast(propertySource);
    }

    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE - 100;
    }

}
