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
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.validation.Validator;

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

    private SpringUtils() {
        super();
    }

    public static BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getBeanDefinitionRegistry)
                .orElseThrow(UNSUPPORTED);
    }

    public static <T> ObjectProvider<T> getObjectProvider(final Class<T> beanType) {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.getObjectProvider(beanType))
                .orElseThrow(UNSUPPORTED);
    }

    public static ResourceLoader getResourceLoader() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getResourceLoader)
                .orElse(new DefaultResourceLoader());
    }

    public static ResourcePatternResolver getResourcePatternResolver() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getResourcePatternResolver)
                .orElseThrow(UNSUPPORTED);
    }

    public static Environment getEnvironment() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getEnvironment)
                .orElseThrow(UNSUPPORTED);
    }

    public static ConversionService getConversionService() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getConversionService)
                .orElseThrow(UNSUPPORTED);
    }

    public static Validator getValidator() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getValidator)
                .orElseThrow(UNSUPPORTED);
    }

    public static MessageSource getMessageSource() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getMessageSource)
                .orElseThrow(UNSUPPORTED);
    }

    public static MessageSourceAccessor getMessageSourceAccessor() {
        return new MessageSourceAccessor(getMessageSource());
    }

    public static <T> Optional<T> getBean(final Class<T> beanType) {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.getBean(beanType))
                .orElseThrow(UNSUPPORTED);
    }

    public static <T> Optional<T> getBean(final Class<T> beanType, final String beanName) {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.getBean(beanType, beanName))
                .orElseThrow(UNSUPPORTED);
    }

    public static <T> List<T> getBeanList(final Class<T> beanType) {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.getBeanList(beanType))
                .orElseThrow(UNSUPPORTED);
    }

    public static <T> boolean containsBean(final String beanName) {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.containsBean(beanName))
                .orElseThrow(UNSUPPORTED);
    }

    public boolean containsBean(final Class<?> beanType) {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(sc -> sc.containsBean(beanType))
                .orElseThrow(UNSUPPORTED);
    }

}
