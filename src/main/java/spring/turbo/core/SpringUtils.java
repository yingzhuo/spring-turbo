/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.validation.Validator;
import spring.turbo.util.Asserts;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author 应卓
 * @see org.springframework.context.ApplicationContext
 * @since 1.0.0
 */
public final class SpringUtils {

    public static final Supplier<? extends RuntimeException> UNSUPPORTED =
            () -> new UnsupportedOperationException("this operation is unsupported without ApplicationContext instance");

    public static final Supplier<? extends RuntimeException> BEAN_NOT_FOUND =
            () -> new NoSuchBeanDefinitionException("bean not found");

    /**
     * 私有构造方法
     */
    private SpringUtils() {
        super();
    }

    /**
     * 获得{@link BeanDefinitionRegistry}实例
     *
     * @return {@link BeanDefinitionRegistry}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see BeanDefinitionRegistry
     */
    public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getBeanDefinitionRegistry)
                .orElseThrow(UNSUPPORTED);
    }

    /**
     * 获取{@link ObjectProvider}实例
     *
     * @param beanType beanType
     * @param <T>      需要查找或生成的Bean类型泛型
     * @return {@link ObjectProvider}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see ObjectProvider
     */
    public static <T> ObjectProvider<T> getObjectProvider(final Class<T> beanType) {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.getObjectProvider(beanType))
                .orElseThrow(UNSUPPORTED);
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
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getResourceLoader)
                .orElse(new DefaultResourceLoader());
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
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getResourcePatternResolver)
                .orElseThrow(UNSUPPORTED);
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
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getEnvironment)
                .orElseThrow(UNSUPPORTED);
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
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getConversionService)
                .orElseThrow(UNSUPPORTED);
    }

    /**
     * 获取{@link ApplicationEventPublisher}实例
     *
     * @return {@link ApplicationEventPublisher}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     *
     */
    public static ApplicationEventPublisher getApplicationEventPublisher() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getApplicationEventPublisher)
                .orElseThrow(UNSUPPORTED);
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
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getValidator)
                .orElseThrow(UNSUPPORTED);
    }

    /**
     * 获取{@link MessageSource}实例
     *
     * @return {@link MessageSource}实例
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @see MessageSource
     * @see org.springframework.context.support.MessageSourceAccessor
     * @see MessageUtils
     */
    public static MessageSource getMessageSource() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getMessageSource)
                .orElseThrow(UNSUPPORTED);
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
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.getBean(beanType))
                .orElseThrow(UNSUPPORTED);
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

        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.getBean(beanType, beanName))
                .orElseThrow(UNSUPPORTED);
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
        return getBean(beanType).orElseThrow(BEAN_NOT_FOUND);
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
        return getBean(beanType, beanName).orElseThrow(BEAN_NOT_FOUND);
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
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.getBeanList(beanType))
                .orElseThrow(UNSUPPORTED);
    }

    /**
     * 判断{@link org.springframework.context.ApplicationContext}是否包含指定bean
     *
     * @param beanName bean名称
     * @return 结果
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    public static boolean containsBean(String beanName) {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.containsBean(beanName))
                .orElseThrow(UNSUPPORTED);
    }

    /**
     * 判断{@link org.springframework.context.ApplicationContext}是否包含指定bean
     *
     * @param beanType bean类型
     * @param <T>      bean类型泛型
     * @return 结果
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    public static <T> boolean containsBean(Class<?> beanType) {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.containsBean(beanType))
                .orElseThrow(UNSUPPORTED);
    }

}
