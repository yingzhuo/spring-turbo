package spring.turbo.util.hash;

import org.springframework.util.Assert;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 基于信息摘要算法的哈希函数器
 *
 * @author 应卓
 * @since 3.4.0
 */
public class DigestHashFunction implements HashFunction {

    public static HashFunction md5() {
        return SyncAvoid.MD5;
    }

    public static HashFunction sha1() {
        return SyncAvoid.SHA1;
    }

    public static HashFunction sha256() {
        return SyncAvoid.SHA256;
    }

    public static HashFunction sha384() {
        return SyncAvoid.SHA384;
    }

    public static HashFunction sha512() {
        return SyncAvoid.SHA512;
    }

    /**
     * 算法名称
     */
    private final String algName;

    /**
     * 构造方法
     *
     * @param algName 算法名称
     */
    public DigestHashFunction(String algName) {
        Assert.hasText(algName, "algName is null or null");
        this.algName = algName;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Integer apply(String key) {
        try {
            var digest = MessageDigest.getInstance(algName).digest(key.getBytes());
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


    // 延迟加载
    private static class SyncAvoid {
        private static final HashFunction MD5 = new DigestHashFunction("MD5");
        private static final HashFunction SHA1 = new DigestHashFunction("SHA-1");
        private static final HashFunction SHA256 = new DigestHashFunction("SHA-256");
        private static final HashFunction SHA384 = new DigestHashFunction("SHA-384");
        private static final HashFunction SHA512 = new DigestHashFunction("SHA-512");
    }

}
