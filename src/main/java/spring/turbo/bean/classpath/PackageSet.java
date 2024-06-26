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

import java.io.Serializable;
import java.util.*;
import java.util.stream.Stream;

/**
 * 辅助工具封装多个搜索起点
 *
 * @author 应卓
 * @see #newInstance()
 * @since 2.0.10
 */
public final class PackageSet implements Iterable<String>, Serializable {

    // 已排序
    private final SortedSet<String> set = new TreeSet<>();

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

    /**
     * 添加要扫描的包
     *
     * @param packages 包
     * @return this
     */
    public PackageSet acceptPackages(@Nullable String... packages) {
        if (packages != null) {
            Stream.of(packages).filter(StringUtils::isNotBlank).map(String::trim).forEach(set::add);
        }
        return this;
    }

    /**
     * 添加要扫描的包
     *
     * @param packages 包
     * @return this
     */
    public PackageSet acceptPackages(@Nullable Collection<String> packages) {
        if (packages != null) {
            packages.stream().filter(StringUtils::isNotBlank).map(String::trim).forEach(set::add);
        }
        return this;
    }

    /**
     * 添加要扫描的基础类所在的包
     *
     * @param baseClasses 基础类
     * @return this
     */
    public PackageSet acceptBaseClasses(@Nullable Class<?>... baseClasses) {
        if (baseClasses != null) {
            Arrays.stream(baseClasses).filter(Objects::nonNull).map(c -> c.getPackage().getName()).forEach(set::add);
        }
        return this;
    }

    /**
     * 添加要扫描的基础类所在的包
     *
     * @param baseClasses 基础类
     * @return this
     */
    public PackageSet acceptBaseClasses(@Nullable Collection<Class<?>> baseClasses) {
        if (baseClasses != null) {
            baseClasses.stream().filter(Objects::nonNull).map(c -> c.getPackage().getName()).forEach(set::add);
        }
        return this;
    }

    /**
     * 清空所有数据
     *
     * @return this
     */
    public PackageSet clear() {
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
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
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
