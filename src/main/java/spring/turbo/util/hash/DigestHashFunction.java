package spring.turbo.util.hash;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 信息摘要实现
 *
 * @author 应卓
 * @see ConsistentHashing
 * @see HashFunction
 * @since 3.4.0
 */
public class DigestHashFunction implements HashFunction {

    private final Algorithm algorithm;

    /**
     * 默认构造方法
     */
    public DigestHashFunction() {
        this.algorithm = Algorithm.MD5;
    }

    /**
     * 构造方法
     *
     * @param algorithm 信息摘要算法
     */
    public DigestHashFunction(Algorithm algorithm) {
        this.algorithm = algorithm;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer apply(String key) {
        try {
            var md5 = MessageDigest.getInstance(algorithm.getAlgorithmName());
            var digest = md5.digest(key.getBytes());
            return ((digest[0] & 0XFF) << 24) |
                    ((digest[1] & 0XFF) << 16) |
                    ((digest[2] & 0XFF) << 8) |
                    (digest[3] & 0XFF);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(); // 实际方法不可能运行到此处
        }
    }

    public enum Algorithm {
        MD5("MD5"),
        SHA1("SHA-1"),
        SHA256("SHA-256"),
        SHA384("SHA-384"),
        SHA512("SHA-512");

        private final String algorithmName;

        Algorithm(String algorithmName) {
            this.algorithmName = algorithmName;
        }

        public String getAlgorithmName() {
            return algorithmName;
        }
    }

}
