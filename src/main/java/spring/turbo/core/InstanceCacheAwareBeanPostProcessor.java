/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;
import spring.turbo.util.InstanceCache;

/**
 * @author 应卓
 * @see InstanceCache
 * @since 1.0.10
 */
public class InstanceCacheAwareBeanPostProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public InstanceCacheAwareBeanPostProcessor(ApplicationContext applicationContext) {
        Asserts.notNull(applicationContext);
        this.applicationContext = applicationContext;
    }

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (bean instanceof InstanceCacheAware) {
            ((InstanceCacheAware) bean).setInstanceCache(InstanceCache.newInstance(applicationContext));
        }
        return bean;
    }

}
