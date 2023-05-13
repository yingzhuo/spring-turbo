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
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.ApplicationArguments;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.OrderComparator;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.CollectionUtils;
import org.springframework.validation.Validator;
import spring.turbo.util.Asserts;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * {@link ApplicationContext} 的装饰器
 *
 * @author 应卓
 *
 * @see ApplicationContext
 *
 * @since 1.0.0
 */
public final class SpringContext {

    private final ApplicationContext applicationContext;

    /**
     * 构造方法
     *
     * @param applicationContext
     *            spring上下文实例
     */
    public SpringContext(ApplicationContext applicationContext) {
        Asserts.notNull(applicationContext, "applicationContext is null");
        this.applicationContext = applicationContext;
    }

    /**
     * 创建实例
     *
     * @param applicationContext
     *            spring上下文实例
     *
     * @return 实例
     */
    public static SpringContext of(ApplicationContext applicationContext) {
        return new SpringContext(applicationContext);
    }

    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

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

    public ApplicationEventPublisher getApplicationEventPublisher() {
        return applicationContext;
    }

    public Environment getEnvironment() {
        return getBean(Environment.class).orElseThrow(NullPointerException::new);
    }

    public ApplicationArguments getApplicationArguments() {
        return getBean(ApplicationArguments.class).orElseThrow(NullPointerException::new);
    }

    public ConversionService getConversionService() {
        return getBean(ConversionService.class).orElseThrow(NullPointerException::new);
    }

    public Validator getValidator() {
        return getBean(Validator.class).orElseThrow(NullPointerException::new);
    }

    public MessageSource getMessageSource() {
        return getBean(MessageSource.class).orElseThrow(NullPointerException::new);
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
            var list = new ArrayList<>(applicationContext.getBeansOfType(beanType).values());
            OrderComparator.sort(list);
            return list;
        } catch (BeansException e) {
            return new ArrayList<>(0);
        }
    }

    public <T> List<T> getBeanList(Class<T> beanType, T defaultIfNotNull) {
        Asserts.notNull(defaultIfNotNull);

        List<T> list = getBeanList(beanType);
        if (CollectionUtils.isEmpty(list)) {
            list = new ArrayList<>();
            list.add(defaultIfNotNull);
        }
        return list;
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
