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
import spring.turbo.bean.Builder;
import spring.turbo.util.Asserts;
import spring.turbo.util.CollectionUtils;

import java.util.LinkedList;
import java.util.List;

/**
 * ClassPath扫描器的创建器
 *
 * @author 应卓
 * @see TypeFilter
 * @see TypeFilterFactories
 * @since 1.0.0
 */
public final class ClassPathScannerBuilder implements Builder<ClassPathScanner> {

    private final List<TypeFilter> includeFilters = new LinkedList<>();
    private final List<TypeFilter> excludeFilters = new LinkedList<>();
    private Environment environment = new StandardEnvironment();
    private ResourceLoader resourceLoader = new DefaultResourceLoader();

    /**
     * 构造方法
     */
    ClassPathScannerBuilder() {
        super();
    }

    public ClassPathScannerBuilder includeFilter(TypeFilter... filters) {
        CollectionUtils.nullSafeAddAll(includeFilters, filters);
        return this;
    }

    public ClassPathScannerBuilder excludeFilter(TypeFilter... filters) {
        CollectionUtils.nullSafeAddAll(excludeFilters, filters);
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

    @Override
    public ClassPathScanner build() {
        if (includeFilters.isEmpty()) {
            return NullClassPathScanner.getInstance();
        } else {
            var scanner = new DefaultClassPathScanner();
            scanner.setIncludeTypeFilters(includeFilters);
            scanner.setExcludeTypeFilters(excludeFilters);
            scanner.setEnvironment(environment);
            scanner.setResourceLoader(resourceLoader);
            return scanner;
        }
    }

}
