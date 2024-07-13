package spring.turbo.core;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.MessageSource;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.Assert;
import org.springframework.validation.Validator;
import spring.turbo.core.env.SpringApplicationHolders;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
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

    public static final Supplier<? extends RuntimeException> NOT_SUPPORTED =
            () -> new UnsupportedOperationException("operation not supported yet");

    /**
     * 私有构造方法
     */
    private SpringUtils() {
    }

    /**
     * 获得{@link ApplicationContext}实例
     *
     * @return {@link ApplicationContext}实例
     */
    public static ApplicationContext getApplicationContext() {
        return Optional.ofNullable(SpringApplicationHolders.getApplicationContext())
                .orElseThrow(NOT_SUPPORTED);
    }

    /**
     * 获取启动目录
     *
     * @return 启动目录
     */
    public static Path getApplicationHome() {
        return Optional.ofNullable(SpringApplicationHolders.getApplicationHome())
                .orElseThrow(NOT_SUPPORTED);
    }

    /**
     * 获取启动目录绝对路径
     *
     * @return 启动目录绝对路径
     */
    public static String getApplicationHomeAsString() {
        return getApplicationHome().toString();
    }

    /**
     * 获取启动的Source
     *
     * @return 启动的Source
     */
    public static Set<Object> getApplicationSources() {
        return Optional.ofNullable(SpringApplicationHolders.getApplicationSources())
                .orElseGet(HashSet::new);
    }

    /**
     * 获取 {@link WebApplicationType}
     *
     * @return WebApplicationType 实例
     */
    public static WebApplicationType getApplicationWebApplicationType() {
        return Optional.ofNullable(SpringApplicationHolders.getApplicationWebApplicationType())
                .orElseThrow(NOT_SUPPORTED);
    }

    /**
     * 获取{@link ApplicationContext}的ID，一般情况下这个ID可以当做应用的程序的实例ID来使用。
     *
     * @return {@link ApplicationContext}的ID
     */
    public static String getApplicationContextId() {
        var id = getApplicationContext().getId();
        return Optional.ofNullable(id)
                .orElseThrow(NOT_SUPPORTED);
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
        Assert.notNull(beanType, "beanType is required");

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
        Assert.notNull(beanType, "beanType is required");
        Assert.hasText(beanName, "beanName is required");

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
                .orElseThrow(NOT_SUPPORTED);
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
                .orElseThrow(NOT_SUPPORTED);
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

}
