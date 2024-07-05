package spring.turbo.util.crypto;

import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * @author 应卓
 * @since 3.2.6
 */
@Deprecated(since = "3.3.1")
public final class AESBuilder {

    private final IvParameterSpec ivParameterSpec = AESUtils.generateIv();
    private AES.Mode mode = AES.Mode.CBC;

    @Nullable
    private SecretKey secretKey;

    /**
     * 构造方法
     */
    AESBuilder() {
    }

    public AESBuilder passwordAndSalt(String password, String salt) {
        Asserts.hasText(password);
        Asserts.hasText(salt);

        try {
            this.secretKey = AESUtils.getKeyFromPassword(password, salt);
            return this;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public AESBuilder mode(AES.Mode mode) {
        Asserts.notNull(mode, "mode is null");
        this.mode = mode;
        return this;
    }

    public AES build() {
        Asserts.notNull(secretKey, "secretKey is null");

        return new AES() {
            @Override
            public String encrypt(String input) {
                try {
                    return AESUtils.encrypt(mode, input, secretKey, ivParameterSpec);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }
            }

            @Override
            public String decrypt(String cipherText) {
                try {
                    return AESUtils.decrypt(mode, cipherText, secretKey, ivParameterSpec);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }
            }
        };
    }

}
