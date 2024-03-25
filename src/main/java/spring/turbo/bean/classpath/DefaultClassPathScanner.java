/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.classpath;

import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.lang.Nullable;
import spring.turbo.util.ClassUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static spring.turbo.util.collection.CollectionUtils.nullSafeAddAll;

/**
 * {@link ClassPathScanner} 默认实现
 *
 * @author 应卓
 *
 * @since 1.0.0
 */
public final class DefaultClassPathScanner implements ClassPathScanner {

    private final ClassPathScannerCore provider = new ClassPathScannerCore();
    private ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    /**
     * 构造方法
     */
    DefaultClassPathScanner() {
        super();
    }

    @Override
    public List<ClassDef> scan(@Nullable PackageSet packageSet) {
        if (packageSet == null) {
            return List.of();
        }

        final List<BeanDefinition> list = new ArrayList<>();

        for (String basePackage : packageSet) {
            nullSafeAddAll(list, provider.findCandidateComponents(basePackage));
        }

        return list.stream().map(bd -> new ClassDef(bd, classLoader)).distinct().sorted(Comparator.naturalOrder())
                .toList();
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        provider.setResourceLoader(resourceLoader);
    }

    public void setEnvironment(Environment environment) {
        provider.setEnvironment(environment);
    }

    public void setIncludeTypeFilters(List<TypeFilter> filters) {
        filters.forEach(provider::addIncludeFilter);
    }

    public void setExcludeTypeFilters(List<TypeFilter> filters) {
        filters.forEach(provider::addExcludeFilter);
    }

    public void setClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 扫描器核心
     */
    private static final class ClassPathScannerCore extends ClassPathScanningCandidateComponentProvider {

        /**
         * 私有构造方法
         */
        private ClassPathScannerCore() {
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
