package spring.turbo.util.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
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
     * 获取默认实现对象
     *
     * @return 默认实现对象
     */
    public static HashFunction newDefaultInstance() {
        return new Simple();
    }

    /**
     * 计算哈希值
     *
     * @param s 键
     * @return 哈希值
     */
    @Override
    public abstract Integer apply(String s);

    // --------------------------------------------------------------------------------------------------------

    public static class Simple implements HashFunction {

        /**
         * {@inheritDoc}
         */
        @Override
        public Integer apply(String key) {
            try {
                var md5 = MessageDigest.getInstance("MD5");
                byte[] digest = md5.digest(key.getBytes());
                return ((digest[0] & 0XFF) << 24) |
                        ((digest[1] & 0XFF) << 16) |
                        ((digest[2] & 0XFF) << 8) |
                        (digest[3] & 0XFF);
            } catch (NoSuchAlgorithmException ignored) {
                throw new AssertionError(); // 实际方法不可能运行到此处
            }
        }
    }

}
