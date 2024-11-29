package spring.turbo.util.hash;

import org.springframework.lang.Nullable;

/**
 * 布隆过滤器
 *
 * @author 应卓
 * @since 3.4.0
 */
public interface BloomFilter {

    /**
     * 添加一个元素
     *
     * @param element 要添加的元素
     */
    public void add(String element);

    /**
     * 判断是否有可能存在某元素
     *
     * @param element 待检测的元素
     * @return 结果
     */
    public boolean mightContain(@Nullable String element);

    /**
     * 判断是否不存在某元素
     *
     * @param element 待检测的元素
     * @return 结果
     */
    public default boolean notContain(@Nullable String element) {
        return !mightContain(element);
    }

}
