package spring.turbo.util.crypto;

import javax.crypto.Cipher;
import java.security.Key;

/**
 * 加密工具
 *
 * @author 应卓
 * @see SignerUtils
 * @since 3.3.1
 */
public final class CipherUtils {

    /**
     * 私有构造方法
     */
    private CipherUtils() {
    }

    public static byte[] encrypt(byte[] data, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public static byte[] decrypt(byte[] data, Key key) {
        try {
            Cipher cipher = Cipher.getInstance(key.getAlgorithm());
            cipher.init(Cipher.DECRYPT_MODE, key);
            return cipher.doFinal(data);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
