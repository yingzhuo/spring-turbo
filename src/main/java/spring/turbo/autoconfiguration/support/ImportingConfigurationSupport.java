/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.autoconfiguration.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanNameGenerator;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.lang.Nullable;
import spring.turbo.bean.classpath.ClassDef;
import spring.turbo.bean.classpath.ClassPathScanner;
import spring.turbo.util.Asserts;
import spring.turbo.util.CollectionUtils;

import java.lang.annotation.Annotation;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author 应卓
 * @see org.springframework.context.annotation.Import
 * @see ImportBeanDefinitionRegistrar
 * @since 2.0.10
 */
public abstract class ImportingConfigurationSupport<A extends Annotation> implements
        ImportBeanDefinitionRegistrar,
        ResourceLoaderAware,
        EnvironmentAware,
        BeanClassLoaderAware,
        BeanFactoryAware {

    private final Class<A> importAnnotationClass;

    private ClassLoader classLoader;
    private Environment environment;
    private ResourceLoader resourceLoader;
    private BeanFactory beanFactory;

    public ImportingConfigurationSupport(Class<A> importAnnotationClass) {
        Asserts.notNull(importAnnotationClass);
        this.importAnnotationClass = importAnnotationClass;
    }

    @Override
    public final void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry, BeanNameGenerator importBeanNameGenerator) {
        Asserts.notNull(classLoader);
        Asserts.notNull(environment);
        Asserts.notNull(resourceLoader);
        Asserts.notNull(beanFactory);

        var annotationAttributes = AnnotationAttributes.fromMap(
                importingClassMetadata.getAnnotationAttributes(importAnnotationClass.getName())
        );

        if (annotationAttributes != null) {
            doRegister(annotationAttributes, importBeanNameGenerator, registry);
        }
    }

    protected abstract void doRegister(AnnotationAttributes importingAnnotationAttributes, BeanNameGenerator beanNameGenerator, BeanDefinitionRegistry registry);

    @Override
    public final void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        // noop
    }

    // -----------------------------------------------------------------------------------------------------------------

    protected final List<ClassDef> scanClassPath(Set<String> packages, List<TypeFilter> includeFilters) {
        return scanClassPath(packages, includeFilters, List.of());
    }

    protected final List<ClassDef> scanClassPath(Set<String> packages, List<TypeFilter> includeFilters, @Nullable List<TypeFilter> excludeFilters) {

        if (CollectionUtils.isEmpty(includeFilters) || CollectionUtils.isEmpty(packages)) {
            return List.of();
        }

        excludeFilters = Objects.requireNonNullElseGet(excludeFilters, List::of);

        var scannerBuilder = ClassPathScanner.builder()
                .classLoader(getClassLoader())
                .environment(getEnvironment())
                .resourceLoader(getResourceLoader());

        scannerBuilder.includeFilter(includeFilters.toArray(new TypeFilter[0]));
        scannerBuilder.excludeFilter(excludeFilters.toArray(new TypeFilter[0]));

        return scannerBuilder
                .build()
                .scan(packages);
    }

    @Override
    public final void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public final void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public final void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public final void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    protected final ClassLoader getClassLoader() {
        return classLoader;
    }

    protected final Environment getEnvironment() {
        return environment;
    }

    protected final ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    protected final BeanFactory getBeanFactory() {
        return beanFactory;
    }
}
