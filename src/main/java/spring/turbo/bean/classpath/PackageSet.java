/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.classpath;

import org.springframework.lang.Nullable;
import spring.turbo.util.StringUtils;

import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 * @author 应卓
 * @since 2.0.10
 */
public final class PackageSet implements Iterable<String> {

    private final TreeSet<String> set = new TreeSet<>(Comparator.naturalOrder());

    /**
     * 私有构造方法
     */
    private PackageSet() {
        super();
    }

    /**
     * 获取实例
     *
     * @return 实例
     */
    public static PackageSet newInstance() {
        return new PackageSet();
    }

    public PackageSet add(@Nullable String... packages) {
        if (packages != null) {
            Stream.of(packages)
                    .filter(StringUtils::isNotBlank)
                    .map(String::trim)
                    .forEach(set::add);
        }
        return this;
    }

    public PackageSet add(@Nullable Collection<String> packages) {
        if (packages != null) {
            packages.stream()
                    .filter(StringUtils::isNotBlank)
                    .map(String::trim)
                    .forEach(set::add);
        }
        return this;
    }

    @Override
    public Iterator<String> iterator() {
        return set.iterator();
    }

    public boolean isEmpty() {
        return set.isEmpty();
    }

    public boolean isNotEmpty() {
        return !set.isEmpty();
    }

    public int size() {
        return set.size();
    }

}
