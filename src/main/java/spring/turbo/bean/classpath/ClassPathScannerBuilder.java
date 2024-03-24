/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.classpath;

import org.springframework.core.env.Environment;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.lang.Nullable;
import spring.turbo.core.ResourceLoaders;
import spring.turbo.util.ClassUtils;
import spring.turbo.util.collection.CollectionUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.Objects;

/**
 * ClassPath扫描器的创建器
 *
 * @author 应卓
 *
 * @see ClassPathScanner#builder()
 * @see TypeFilter
 * @see TypeFilterFactories
 * @see Environment
 * @see ResourceLoader
 * @see ClassLoader
 *
 * @since 1.0.0
 */
public final class ClassPathScannerBuilder {

    private final List<TypeFilter> includeFilters = new LinkedList<>();
    private final List<TypeFilter> excludeFilters = new LinkedList<>();
    private Environment environment = new StandardEnvironment();
    private ResourceLoader resourceLoader = ResourceLoaders.getDefault();
    private ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    /**
     * 默认构造方法
     */
    ClassPathScannerBuilder() {
        super();
    }

    /**
     * 添加包含类型描述器
     *
     * @param filters
     *            类型描述器
     *
     * @return this
     *
     * @see TypeFilterFactories
     * @see org.springframework.core.type.filter.AbstractClassTestingTypeFilter
     * @see org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter
     */
    public ClassPathScannerBuilder includeFilter(@Nullable TypeFilter... filters) {
        CollectionUtils.nullSafeAddAll(includeFilters, filters);
        return this;
    }

    /**
     * 添加排除类型描述器
     *
     * @param filters
     *            类型描述器
     *
     * @return this
     *
     * @see TypeFilterFactories
     * @see org.springframework.core.type.filter.AbstractClassTestingTypeFilter
     * @see org.springframework.core.type.filter.AbstractTypeHierarchyTraversingFilter
     */
    public ClassPathScannerBuilder excludeFilter(@Nullable TypeFilter... filters) {
        CollectionUtils.nullSafeAddAll(excludeFilters, filters);
        return this;
    }

    /**
     * 添加 {@link Environment} 实例
     *
     * @param environment
     *            {@link Environment} 实例
     *
     * @return this
     *
     * @see org.springframework.context.EnvironmentAware
     */
    public ClassPathScannerBuilder environment(@Nullable Environment environment) {
        environment = Objects.requireNonNullElseGet(environment, StandardEnvironment::new);
        this.environment = environment;
        return this;
    }

    /**
     * 添加 {@link ResourceLoader} 实例
     *
     * @param resourceLoader
     *            {@link ResourceLoader} 实例
     *
     * @return this
     *
     * @see org.springframework.context.ResourceLoaderAware
     */
    public ClassPathScannerBuilder resourceLoader(@Nullable ResourceLoader resourceLoader) {
        resourceLoader = Objects.requireNonNullElseGet(resourceLoader, DefaultResourceLoader::new);
        this.resourceLoader = resourceLoader;
        return this;
    }

    /**
     * 添加 {@link ClassLoader} 实例
     *
     * @param classLoader
     *            {@link ClassLoader} 实例
     *
     * @return this
     *
     * @see ClassUtils#getDefaultClassLoader()
     */
    public ClassPathScannerBuilder classLoader(@Nullable ClassLoader classLoader) {
        classLoader = Objects.requireNonNullElseGet(classLoader, ClassUtils::getDefaultClassLoader);
        this.classLoader = classLoader;
        return this;
    }

    /**
     * 创建 {@link ClassPathScanner} 实例
     *
     * @return {@link ClassPathScanner} 实例
     */
    public ClassPathScanner build() {
        if (includeFilters.isEmpty()) {
            return NullClassPathScanner.getInstance();
        } else {
            var scanner = new DefaultClassPathScanner();
            scanner.setIncludeTypeFilters(includeFilters);
            scanner.setExcludeTypeFilters(excludeFilters);
            scanner.setEnvironment(environment);
            scanner.setResourceLoader(resourceLoader);
            scanner.setClassLoader(classLoader);
            return scanner;
        }
    }

}
