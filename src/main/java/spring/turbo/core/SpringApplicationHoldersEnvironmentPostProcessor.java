package spring.turbo.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author 应卓
 * @since 2.0.9
 */
public class SpringApplicationHoldersEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        SpringApplicationHolders.SA = application;
    }

    @Override
    public int getOrder() {
        return HIGHEST_PRECEDENCE;
    }

}
