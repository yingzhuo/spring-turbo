/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.List;
import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class SpringUtils {

    private SpringUtils() {
        super();
    }

    public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return SpringApplicationAware.SC.getBeanDefinitionRegistry();
    }

    public static <T> ObjectProvider<T> getObjectProvider(Class<T> beanType) {
        return SpringApplicationAware.SC.getObjectProvider(beanType);
    }

    public static ResourceLoader getResourceLoader() {
        return SpringApplicationAware.SC.getResourceLoader();
    }

    public static ResourcePatternResolver getResourcePatternResolver() {
        return SpringApplicationAware.SC.getResourcePatternResolver();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static void registerBean(BeanDefinition beanDefinition, String beanName, String... aliases) {
        SpringApplicationAware.SC.registerBean(beanDefinition, beanName, aliases);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static <T> Optional<T> getBean(Class<T> beanType) {
        return SpringApplicationAware.SC.getBean(beanType);
    }

    public static <T> Optional<T> getBean(Class<T> beanType, String beanName) {
        return SpringApplicationAware.SC.getBean(beanType, beanName);
    }

    public static <T> List<T> getBeanList(Class<T> beanType) {
        return SpringApplicationAware.SC.getBeanList(beanType);
    }

    public static <T> boolean containsBean(String beanName) {
        return SpringApplicationAware.SC.containsBean(beanName);
    }

    public boolean containsBean(Class<?> beanType) {
        return SpringApplicationAware.SC.containsBean(beanType);
    }

}
