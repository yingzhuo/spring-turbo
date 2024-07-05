package spring.turbo.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * 信息摘要工具
 *
 * @author 应卓
 * @see org.springframework.util.DigestUtils
 * @since 3.3.0
 */
public final class DigestUtils {

    public static final String ALG_MD2 = "MD2";
    public static final String ALG_MD5 = "MD5";
    public static final String ALG_SHA_1 = "SHA-1";
    public static final String ALG_SHA_256 = "SHA-256";
    public static final String ALG_SHA_384 = "SHA-384";
    public static final String ALG_SHA_512 = "SHA-512";

    /**
     * 私有构造方法
     */
    private DigestUtils() {
    }

    /**
     * md2
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static byte[] md2(final byte[] data) {
        try {
            var digest = MessageDigest.getInstance(ALG_MD2);
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * md2
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static String md2Hex(final String data) {
        Asserts.notNull(data);
        return HexUtils.encodeToString(md2(data.getBytes(UTF_8)));
    }

    /**
     * md5
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static byte[] md5(final byte[] data) {
        try {
            var digest = MessageDigest.getInstance(ALG_MD5);
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * md5
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static String md5Hex(final String data) {
        Asserts.notNull(data);
        return HexUtils.encodeToString(md5(data.getBytes(UTF_8)));
    }

    /**
     * SHA-1
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static byte[] sha1(final byte[] data) {
        try {
            var digest = MessageDigest.getInstance(ALG_SHA_1);
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * SHA-1
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static String sha1Hex(final String data) {
        Asserts.notNull(data);
        return HexUtils.encodeToString(sha1(data.getBytes(UTF_8)));
    }

    /**
     * SHA-256
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static byte[] sha256(final byte[] data) {
        try {
            var digest = MessageDigest.getInstance(ALG_SHA_256);
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * SHA-256
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static String sha256Hex(final String data) {
        Asserts.notNull(data);
        return HexUtils.encodeToString(sha256(data.getBytes(UTF_8)));
    }

    /**
     * SHA-384
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static byte[] sha384(final byte[] data) {
        try {
            var digest = MessageDigest.getInstance(ALG_SHA_384);
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * SHA-384
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static String sha384Hex(final String data) {
        Asserts.notNull(data);
        return HexUtils.encodeToString(sha384(data.getBytes(UTF_8)));
    }

    /**
     * SHA-512
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static byte[] sha512(final byte[] data) {
        try {
            var digest = MessageDigest.getInstance(ALG_SHA_512);
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * SHA-512
     *
     * @param data 数据
     * @return 信息摘要
     */
    public static String sha512Hex(final String data) {
        Asserts.notNull(data);
        return HexUtils.encodeToString(sha512(data.getBytes(UTF_8)));
    }

}
