/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
@NonNullApi
@NonNullFields
package spring.turbo.core;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.lang.NonNullApi;
import org.springframework.lang.NonNullFields;

/**
 * @author 应卓
 * @since 1.0.10
 */
class SpringBootAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    SpringContextAwareBeanPostProcessor springContextAwareBeanPostProcessor(ApplicationContext applicationContext) {
        return new SpringContextAwareBeanPostProcessor(applicationContext);
    }

    @Bean
    @ConditionalOnMissingBean
    InstanceCacheAwareBeanPostProcessor instanceCacheAwareBeanPostProcessor(ApplicationContext applicationContext) {
        return new InstanceCacheAwareBeanPostProcessor(applicationContext);
    }

}