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
import spring.turbo.lang.Mutable;
import spring.turbo.util.StringUtils;

import java.util.*;
import java.util.stream.Stream;

/**
 * 辅助工具封装多个搜索起点
 *
 * @author 应卓
 * @see #newInstance()
 * @since 2.0.10
 */
@Mutable
public final class PackageSet implements Iterable<String> {

    // 已排序
    private final SortedSet<String> set = new TreeSet<>(Comparator.naturalOrder());

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

    public PackageSet addPackages(@Nullable String... packages) {
        if (packages != null) {
            Stream.of(packages)
                    .filter(StringUtils::isNotBlank)
                    .map(String::trim)
                    .forEach(set::add);
        }
        return this;
    }

    public PackageSet addPackages(@Nullable Collection<String> packages) {
        if (packages != null) {
            packages.stream()
                    .filter(StringUtils::isNotBlank)
                    .map(String::trim)
                    .forEach(set::add);
        }
        return this;
    }

    public PackageSet addBaseClasses(@Nullable Class<?>... classes) {
        if (classes != null) {
            Arrays.stream(classes)
                    .filter(Objects::nonNull)
                    .map(c -> c.getPackage().getName())
                    .forEach(set::add);
        }
        return this;
    }

    public PackageSet addBaseClasses(@Nullable Collection<Class<?>> classes) {
        if (classes != null) {
            classes.stream()
                    .filter(Objects::nonNull)
                    .map(c -> c.getPackage().getName())
                    .forEach(set::add);
        }
        return this;
    }

    public PackageSet clean() {
        set.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
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

    public SortedSet<String> asSet() {
        return set;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PackageSet strings = (PackageSet) o;
        return set.equals(strings.set);
    }

    @Override
    public int hashCode() {
        return Objects.hash(set);
    }

    @Override
    public String toString() {
        return "[" + String.join(",", set) + "]";
    }

}
