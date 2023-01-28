/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.classpath;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.type.filter.TypeFilter;
import org.springframework.lang.Nullable;
import spring.turbo.util.ClassUtils;

import java.util.*;

import static spring.turbo.util.CollectionUtils.nullSafeAddAll;
import static spring.turbo.util.StringUtils.isNotBlank;

/**
 * {@link ClassPathScanner} 默认实现
 *
 * @author 应卓
 * @since 1.0.0
 */
final class DefaultClassPathScanner implements ClassPathScanner {

    private final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider();
    private ClassLoader classLoader = ClassUtils.getDefaultClassLoader();

    @Override
    public List<ClassDef> scan(@Nullable Iterable<String> basePackages) {
        final List<BeanDefinition> list = new ArrayList<>();

        for (String basePackage : betterBasePackages(basePackages)) {
            nullSafeAddAll(list, provider.findCandidateComponents(basePackage));
        }

        return list.stream()
                .map(bd -> new ClassDef(bd, classLoader))
                .distinct()
                .sorted(Comparator.naturalOrder())
                .toList();
    }

    // 去除重复并排序
    private Set<String> betterBasePackages(@Nullable Iterable<String> basePackages) {
        var set = new TreeSet<String>(Comparator.naturalOrder());
        if (basePackages != null) {
            for (var pkg : basePackages) {
                if (isNotBlank(pkg)) {
                    set.add(pkg);
                }
            }
        }
        return set;
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

}
