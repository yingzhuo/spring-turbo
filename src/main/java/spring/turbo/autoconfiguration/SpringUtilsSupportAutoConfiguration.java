/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.autoconfiguration;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import spring.turbo.core.XAwarePostProcessor;
import spring.turbo.lang.BetaWarningBeanPostProcessor;

/**
 * @author 应卓
 * @since 1.3.0
 */
@AutoConfiguration
public class SpringUtilsSupportAutoConfiguration {

    @Bean
    public XAwarePostProcessor xAwarePostProcessor(ApplicationContext applicationContext) {
        return new XAwarePostProcessor(applicationContext);
    }

    @Bean
    public BetaWarningBeanPostProcessor betaWarningBeanPostProcessor() {
        return new BetaWarningBeanPostProcessor();
    }

}
