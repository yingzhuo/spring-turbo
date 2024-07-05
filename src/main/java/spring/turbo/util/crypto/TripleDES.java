package spring.turbo.util.crypto;

/**
 * 3DES加密解密工具
 *
 * @author 应卓
 * @see #builder()
 * @since 3.2.6
 */
@Deprecated(since = "3.3.1")
public interface TripleDES {

    public static TripleDESBuilder builder() {
        return new TripleDESBuilder();
    }

    public String encrypt(String input);

    public String decrypt(String cipherText);

}
