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
import spring.turbo.util.CollectionUtils;
import spring.turbo.util.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

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
            if (StringUtils.isNotBlank(basePackage)) {
                CollectionUtils.nullSafeAddAll(list, provider.findCandidateComponents(basePackage));
            }
        }

        return list.stream()
                .map(ClassDef::new)
                .sorted(Comparator.<ClassDef>naturalOrder())
                .toList();
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        provider.setResourceLoader(resourceLoader);
    }

    public void setEnvironment(Environment environment) {
        provider.setEnvironment(environment);
    }

    public void setIncludeTypeFilters(List<TypeFilter> typeFilters) {
        typeFilters.forEach(provider::addIncludeFilter);
    }

    public void setExcludeTypeFilters(List<TypeFilter> typeFilters) {
        typeFilters.forEach(provider::addExcludeFilter);
    }

}
