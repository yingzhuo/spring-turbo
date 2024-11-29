package spring.turbo.util.hash;

import java.util.function.Function;

/**
 * 哈希函数器
 *
 * @author 应卓
 * @see ConsistentHashing
 * @since 3.4.0
 */
@FunctionalInterface
public interface HashFunction extends Function<String, Integer> {

    /**
     * 获取默认实现
     *
     * @return 默认实现
     */
    public static HashFunction defaultInstance() {
        return DigestHashFunctions.md5();
    }

    /**
     * 计算哈希值
     *
     * @param key 键
     * @return 哈希值
     */
    @Override
    public Integer apply(String key);

}
