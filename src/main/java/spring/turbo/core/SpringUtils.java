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
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.validation.Validator;
import spring.turbo.util.Asserts;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Spring上下文工具
 *
 * @author 应卓
 * @see SpringApplication
 * @see org.springframework.context.ApplicationContext
 * @since 1.0.0
 */
public final class SpringUtils {

    public static final Supplier<? extends RuntimeException> UNSUPPORTED = () -> new UnsupportedOperationException(
            "this operation is NOT supported without ApplicationContext instance");

    public static final Supplier<? extends RuntimeException> BEAN_NOT_FOUND = () -> new NoSuchBeanDefinitionException(
            "bean not found");

    /**
     * 私有构造方法
     */
    private SpringUtils() {
        super();
    }

    /**
     * 获得{@link ApplicationContext}实例
     *
     * @return {@link ApplicationContext}实例
     */
    public static ApplicationContext getApplicationContext() {
        if (SpringApplicationHolders.AC == null) {
            throw BEAN_NOT_FOUND.get();
        }
        return SpringApplicationHolders.AC;
    }

    /**
     * 获取{@link ApplicationContext}的ID，一般情况下这个ID可以当做应用的程序的实例ID来使用。
     *
     * @return {@link ApplicationContext}的ID
     */
    public static String getApplicationContextId() {
        var id = getApplicationContext().getId();
        if (id == null) {
            throw BEAN_NOT_FOUND.get();
        }
        return id;
    }

    /**
     * 获得{@link BeanDefinitionRegistry}实例
     *
     * @return {@link BeanDefinitionRegistry}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see BeanDefinitionRegistry
     */
    public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return (BeanDefinitionRegistry) getApplicationContext().getAutowireCapableBeanFactory();
    }

    /**
     * 获取{@link ResourceLoader}实例
     *
     * @return {@link ResourceLoader}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see org.springframework.core.io.Resource
     * @see ResourceLoader
     * @see ResourcePatternResolver
     * @see #getResourcePatternResolver()
     */
    public static ResourceLoader getResourceLoader() {
        return getApplicationContext();
    }

    /**
     * 获取{@link ResourcePatternResolver}实例
     *
     * @return {@link ResourcePatternResolver}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see org.springframework.core.io.Resource
     * @see ResourceLoader
     * @see ResourcePatternResolver
     * @see #getResourceLoader()
     */
    public static ResourcePatternResolver getResourcePatternResolver() {
        return getApplicationContext();
    }

    /**
     * 获取{@link Environment}实例
     *
     * @return {@link Environment}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see Environment
     * @see EnvironmentUtils
     * @see ProfilesUtils
     */
    public static Environment getEnvironment() {
        return getApplicationContext().getEnvironment();
    }

    /**
     * 获取{@link ApplicationArguments}实例
     *
     * @return {@link ApplicationArguments}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see Environment
     * @see EnvironmentUtils
     * @see ProfilesUtils
     */
    public static ApplicationArguments getApplicationArguments() {
        return getApplicationContext().getBean(ApplicationArguments.class);
    }

    /**
     * 获取{@link ConversionService}实例
     *
     * @return {@link ConversionService}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see ConversionService
     * @see org.springframework.core.convert.converter.Converter
     * @see org.springframework.core.convert.converter.GenericConverter
     * @see org.springframework.format.support.DefaultFormattingConversionService
     * @see ConversionUtils
     */
    public static ConversionService getConversionService() {
        return getApplicationContext().getBean(ConversionService.class);
    }

    /**
     * 获取{@link ApplicationEventPublisher}实例
     *
     * @return {@link ApplicationEventPublisher}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    public static ApplicationEventPublisher getApplicationEventPublisher() {
        return getApplicationContext();
    }

    /**
     * 获取{@link Validator}实例
     *
     * @return {@link Validator}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see Validator
     * @see ValidatorUtils
     */
    public static Validator getValidator() {
        return getApplicationContext().getBean(Validator.class);
    }

    /**
     * 获取{@link MessageSource}实例
     *
     * @return {@link MessageSource}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see MessageSource
     * @see org.springframework.context.support.MessageSourceAccessor
     */
    public static MessageSource getMessageSource() {
        return getApplicationContext();
    }

    /**
     * 获取指定类型Bean
     *
     * @param beanType 指定类型
     * @param <T>      指定类型泛型
     * @return Bean实例 (Optional)
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    public static <T> Optional<T> getBean(Class<T> beanType) {
        Asserts.notNull(beanType);

        try {
            return Optional.of(getApplicationContext().getBean(beanType));
        } catch (BeansException e) {
            return Optional.empty();
        }
    }

    /**
     * 获取指定类型Bean
     *
     * @param beanType 指定类型
     * @param beanName bean名称
     * @param <T>      指定类型泛型
     * @return Bean实例 (Optional)
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    public static <T> Optional<T> getBean(Class<T> beanType, String beanName) {
        Asserts.notNull(beanName);
        Asserts.notNull(beanType);

        try {
            return Optional.of(getApplicationContext().getBean(beanName, beanType));
        } catch (BeansException e) {
            return Optional.empty();
        }
    }

    /**
     * 获取指定类型Bean
     *
     * @param beanType 指定类型
     * @param <T>      指定类型泛型
     * @return Bean实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @throws NoSuchBeanDefinitionException 无法查找到Bean
     */
    public static <T> T getRequiredBean(Class<T> beanType) {
        return getBean(beanType)
                .orElseThrow(BEAN_NOT_FOUND);
    }

    /**
     * 获取指定类型Bean
     *
     * @param beanType 指定类型
     * @param beanName bean名称
     * @param <T>      指定类型泛型
     * @return Bean实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @throws NoSuchBeanDefinitionException 无法查找到Bean
     */
    public static <T> T getRequiredBean(Class<T> beanType, String beanName) {
        return getBean(beanType, beanName)
                .orElseThrow(BEAN_NOT_FOUND);
    }

    /**
     * 获取所有指定类型Bean的实例
     *
     * @param beanType 指定类型
     * @param <T>      指定类型泛型
     * @return Bean实例列表
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    public static <T> List<T> getBeanList(final Class<T> beanType) {
        return getApplicationContext()
                .getBeansOfType(beanType)
                .values()
                .stream()
                .toList();
    }

    /**
     * 获取{@link SpringApplication} 实例
     *
     * @return 实例
     */
    public static SpringApplication getSpringApplication() {
        return Optional.ofNullable(SpringApplicationHolders.SA)
                .orElseThrow(UNSUPPORTED);
    }

}
