package spring.turbo.util.hash;

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
     * 判断是否存在某元素
     *
     * @param element 待判断的元素
     * @return 结果
     */
    public boolean exists(String element);

    /**
     * 判断是否不存在某元素
     *
     * @param element 待判断的元素
     * @return 结果
     */
    public default boolean notExists(String element) {
        return !exists(element);
    }

}
