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
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.core.OrderComparator;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.util.*;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class SpringContext {

    private final ApplicationContext applicationContext;

    SpringContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return (BeanDefinitionRegistry) applicationContext.getAutowireCapableBeanFactory();
    }

    public <T> ObjectProvider<T> getObjectProvider(Class<T> beanType) {
        return applicationContext.getBeanProvider(beanType);
    }

    public ResourceLoader getResourceLoader() {
        return applicationContext;
    }

    public ResourcePatternResolver getResourcePatternResolver() {
        return applicationContext;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public void registerBean(BeanDefinition beanDefinition, String beanName, String... aliases) {
        final BeanDefinitionRegistry registry = getBeanDefinitionRegistry();
        registry.registerBeanDefinition(beanName, beanDefinition);
        if (aliases != null) {
            Arrays.stream(aliases).forEach(alias -> registry.registerAlias(beanName, alias));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    public <T> Optional<T> getBean(Class<T> beanType) {
        try {
            return Optional.of(applicationContext.getBean(beanType));
        } catch (BeansException e) {
            return Optional.empty();
        }
    }

    public <T> Optional<T> getBean(Class<T> beanType, String beanName) {
        try {
            return Optional.of(applicationContext.getBean(beanName, beanType));
        } catch (BeansException e) {
            return Optional.empty();
        }
    }

    public <T> List<T> getBeanList(Class<T> beanType) {
        try {
            final List<T> list = new ArrayList<>(applicationContext.getBeansOfType(beanType).values());
            OrderComparator.sort(list);
            return Collections.unmodifiableList(list);
        } catch (BeansException e) {
            return Collections.emptyList();
        }
    }

    public <T> boolean containsBean(String beanName) {
        return applicationContext.containsBean(beanName);
    }

    public boolean containsBean(Class<?> beanType) {
        try {
            applicationContext.getBean(beanType);
            return true;
        } catch (NoUniqueBeanDefinitionException e) {
            return true;
        } catch (NoSuchBeanDefinitionException e) {
            return false;
        }
    }

}
