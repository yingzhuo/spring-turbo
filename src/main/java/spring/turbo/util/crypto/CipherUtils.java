package spring.turbo.util.crypto;

import javax.crypto.Cipher;
import java.security.Key;

/**
 * 加密工具
 *
 * @author 应卓
 * @see SignatureUtils
 * @since 3.3.1
 */
public final class CipherUtils {

    /**
     * 私有构造方法
     */
    private CipherUtils() {
    }

    /**
     * 加密
     *
     * @param rawData 要加密的数据
     * @param key     加密秘钥
     * @return 加密结果
     */
    public static byte[] encrypt(byte[] rawData, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(rawData);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * 解密
     *
     * @param encryptedData 已加密的数据
     * @param key           解密秘钥
     * @return 解密结果
     */
    public static byte[] decrypt(byte[] encryptedData, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
