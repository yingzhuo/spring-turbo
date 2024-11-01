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
    private final SortedSet<String> innerSet = new TreeSet<>();

    /**
     * 私有构造方法
     */
    private PackageSet() {
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
            // @formatter:off
            Stream.of(packages)
                    .filter(StringUtils::isNotBlank)
                    .map(String::trim)
                    .forEach(innerSet::add);
            // @formatter:on
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
            // @formatter:off
            packages.stream()
                    .filter(StringUtils::isNotBlank)
                    .map(String::trim)
                    .forEach(innerSet::add);
            // @formatter:on
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
            // @formatter:off
            Arrays.stream(baseClasses)
                    .filter(Objects::nonNull)
                    .map(c -> c.getPackage().getName())
                    .forEach(innerSet::add);
            // @formatter:on
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
        // @formatter:off
        if (baseClasses != null) {
            baseClasses.stream()
                    .filter(Objects::nonNull)
                    .map(c -> c.getPackage().getName())
                    .forEach(innerSet::add);
        }
        return this;
        // @formatter:on
    }

    /**
     * 清空所有数据
     *
     * @return this
     */
    public PackageSet clear() {
        innerSet.clear();
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Iterator<String> iterator() {
        return innerSet.iterator();
    }

    public boolean isEmpty() {
        return innerSet.isEmpty();
    }

    public boolean isNotEmpty() {
        return !innerSet.isEmpty();
    }

    public int size() {
        return innerSet.size();
    }

    public SortedSet<String> asSet() {
        return innerSet;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        PackageSet strings = (PackageSet) o;
        return innerSet.equals(strings.innerSet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hash(innerSet);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return innerSet.toString();
    }

}
