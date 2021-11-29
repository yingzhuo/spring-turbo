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
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.converter.ConverterFactory;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import spring.turbo.integration.Modules;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author 应卓
 * @see org.springframework.context.ApplicationContext
 * @since 1.0.0
 */
public final class SpringUtils {

    private static final Supplier<? extends RuntimeException> UNSUPPORTED =
            () -> new UnsupportedOperationException("this operation not supported without ApplicationContext instance");

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
                .orElse(new PathMatchingResourcePatternResolver());
    }

    public static ConversionService getConversionService() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getConversionService)
                .orElseGet(new BackupConversionServiceSupplier());
    }

    public static Environment getEnvironment() {
        return Optional.ofNullable(SpringApplicationAware.SC)
                .map(SpringContext::getEnvironment)
                .orElseGet(new BackupEnvironmentSupplier());
    }

    // -----------------------------------------------------------------------------------------------------------------

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

    // -----------------------------------------------------------------------------------------------------------------

    private static class BackupConversionServiceSupplier implements Supplier<ConversionService> {

        private final static DefaultConversionService CONVERSION_SERVICE = new DefaultConversionService();

        static {
            for (Converter<?, ?> it : Modules.ALL_CONVERTERS_CONVERTERS) {
                CONVERSION_SERVICE.addConverter(it);
            }
            for (GenericConverter it : Modules.ALL_GENERIC_CONVERTERS) {
                CONVERSION_SERVICE.addConverter(it);
            }
            for (ConverterFactory<?, ?> it : Modules.ALL_CONVERTER_FACTORIES) {
                CONVERSION_SERVICE.addConverterFactory(it);
            }
        }

        @Override
        public ConversionService get() {
            return CONVERSION_SERVICE;
        }
    }

    private static class BackupEnvironmentSupplier implements Supplier<Environment> {

        private final static StandardEnvironment ENVIRONMENT = new StandardEnvironment();

        @Override
        public Environment get() {
            return ENVIRONMENT;
        }
    }

}
