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

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static spring.turbo.util.CollectionUtils.nullSafeAddAll;
import static spring.turbo.util.StringUtils.isNotBlank;

/**
 * ClassPath 扫描器默认实现
 *
 * @author 应卓
 * @since 1.0.0
 */
final class DefaultClassPathScanner implements ClassPathScanner {

    private final ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider();

    @Override
    public List<ClassDef> scan(Iterable<String> basePackages) {
        final List<BeanDefinition> list = new ArrayList<>();

        for (String basePackage : basePackages) {
            if (isNotBlank(basePackage)) {
                nullSafeAddAll(list, provider.findCandidateComponents(basePackage));
            }
        }

        return list.stream()
                .map(ClassDef::new)
                .distinct()
                .sorted(Comparator.<ClassDef>naturalOrder())
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

}
