package spring.turbo.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.env.ConfigurableEnvironment;

/**
 * @author 应卓
 * @since 2.0.9
 */
class SpringApplicationHoldersEnvironmentPostProcessor implements EnvironmentPostProcessor {

    /**
     * 默认构造方法
     */
    public SpringApplicationHoldersEnvironmentPostProcessor() {
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        SpringApplicationHolders.SA = application;
    }

}
