package spring.turbo.util.hash;

import org.springframework.lang.Nullable;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Objects;

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
    public DigestHashFunction(@Nullable Algorithm algorithm) {
        this.algorithm = Objects.requireNonNullElse(algorithm, Algorithm.MD5); // default to MD5
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer apply(String key) {
        try {
            var digest = MessageDigest.getInstance(algorithm.name).digest(key.getBytes());
            // @formatter:off
            return ((digest[0] & 0XFF) << 24) |
                    ((digest[1] & 0XFF) << 16) |
                    ((digest[2] & 0XFF) << 8) |
                    (digest[3] & 0XFF);
            // @formatter:on
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(); // 实际方法不可能运行到此处
        }
    }

    /**
     * 信息摘要算法美剧
     */
    public enum Algorithm {
        MD5("MD5"),
        SHA1("SHA-1"),
        SHA256("SHA-256"),
        SHA384("SHA-384"),
        SHA512("SHA-512");

        private final String name;

        Algorithm(String name) {
            this.name = name;
        }
    }

}
