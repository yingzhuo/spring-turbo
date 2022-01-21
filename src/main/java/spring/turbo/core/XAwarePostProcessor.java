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
import spring.turbo.util.InstanceCache;
import spring.turbo.util.InstanceCacheAware;

/**
 * 处理 XxxAware
 *
 * @author 应卓
 * @see InstanceCacheAware
 * @see SpringContextAware
 * @since 1.0.10
 */
class XAwarePostProcessor implements BeanPostProcessor {

    private final ApplicationContext applicationContext;

    public XAwarePostProcessor(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    @Nullable
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        // 处理InstanceCacheAware
        if (bean instanceof InstanceCacheAware) {
            ((InstanceCacheAware) bean).setInstanceCache(InstanceCache.newInstance(applicationContext));
        }

        // 处理SpringContextAware
        if (bean instanceof SpringContextAware) {
            ((SpringContextAware) bean).setSpringContext(SpringContext.of(applicationContext));
        }

        return bean;
    }

}
