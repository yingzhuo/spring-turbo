/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.lang.NonNull;
import spring.turbo.util.Asserts;

import java.util.*;
import java.util.stream.Collectors;

/**
 * ClassPath扫描器的创建器
 *
 * @author 应卓
 * @see TypeFilter
 * @see TypeFilterFactories
 * @since 1.0.0
 */
public final class ClassPathScannerBuilder {

    private final List<TypeFilter> includeFilters = new LinkedList<>();
    private final List<TypeFilter> excludeFilters = new LinkedList<>();
    private Environment environment = new StandardEnvironment();
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    ClassPathScannerBuilder() {
        super();
    }

    public ClassPathScannerBuilder includeFilter(TypeFilter... typeFilters) {
        Collections.addAll(this.includeFilters, typeFilters);
        return this;
    }

    public ClassPathScannerBuilder excludeFilter(TypeFilter... typeFilters) {
        Collections.addAll(this.excludeFilters, typeFilters);
        return this;
    }

    public ClassPathScannerBuilder environment(Environment environment) {
        Asserts.notNull(environment);
        this.environment = environment;
        return this;
    }

    public ClassPathScannerBuilder resourceLoader(ResourceLoader resourceLoader) {
        Asserts.notNull(resourceLoader);
        this.resourceLoader = resourceLoader;
        return this;
    }

    public ClassPathScanner build() {
        if (includeFilters.isEmpty()) {
            return new NullClassPathScanner();
        } else {
            final DefaultClassPathScanner scanner = new DefaultClassPathScanner();
            scanner.setIncludeTypeFilters(includeFilters);
            scanner.setEnvironment(environment);
            scanner.setResourceLoader(resourceLoader);
            scanner.setExcludeTypeFilters(excludeFilters);
            return scanner;
        }
    }

    // ----------------------------------------------------------------------------------------------------------------

    private static final class NullClassPathScanner implements ClassPathScanner {

        @NonNull
        @Override
        public List<ClassDefinition> scan(@NonNull Iterable<String> basePackages) {
            Asserts.notNull(basePackages);
            return Collections.emptyList();
        }
    }

    private static class DefaultClassPathScanner implements ClassPathScanner {
        private final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider();

        @NonNull
        @Override
        public List<ClassDefinition> scan(@NonNull Iterable<String> basePackages) {
            final Set<BeanDefinition> set = new HashSet<>();

            for (String basePackage : basePackages) {
                set.addAll(provider.findCandidateComponents(basePackage));
            }

            return Collections.unmodifiableList(set.stream().map(ClassDefinition::new).collect(Collectors.toList()));
        }

        public void setResourceLoader(ResourceLoader resourceLoader) {
            provider.setResourceLoader(resourceLoader);
        }

        public void setEnvironment(Environment environment) {
            provider.setEnvironment(environment);
        }

        public void setIncludeTypeFilters(List<TypeFilter> typeFilters) {
            for (TypeFilter typeFilter : typeFilters) {
                provider.addIncludeFilter(typeFilter);
            }
        }

        public void setExcludeTypeFilters(List<TypeFilter> typeFilters) {
            for (TypeFilter typeFilter : typeFilters) {
                provider.addExcludeFilter(typeFilter);
            }
        }
    }

    private static class ClassPathScanningCandidateComponentProvider
            extends org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider {

        private ClassPathScanningCandidateComponentProvider() {
            super(false);
        }

        @Override
        protected boolean isCandidateComponent(AnnotatedBeanDefinition beanDefinition) {
            boolean isCandidate = false;
            if (beanDefinition.getMetadata().isIndependent()) {
                if (!beanDefinition.getMetadata().isAnnotation()) {
                    isCandidate = true;
                }
            }
            return isCandidate;
        }
    }

}
