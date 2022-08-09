/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import spring.turbo.bean.Builder;
import spring.turbo.util.Asserts;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class AESBuilder implements Builder<AES> {

    private final IvParameterSpec ivParameterSpec = AESUtils.generateIv();
    private AES.Mode mode = AES.Mode.CBC;

    @Nullable
    private SecretKey secretKey;

    AESBuilder() {
        super();
    }

    public AESBuilder passwordAndSalt(String password, String salt) {
        Asserts.hasText(password);
        Asserts.hasText(salt);

        try {
            this.secretKey = AESUtils.getKeyFromPassword(password, salt);
            return this;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public AESBuilder mode(AES.Mode mode) {
        Assert.notNull(mode, "mode is null");
        this.mode = mode;
        return this;
    }

    @Override
    public AES build() {
        Assert.notNull(secretKey, "secretKey is null");

        return new AES() {
            @Override
            public String encrypt(String input) {
                try {
                    return AESUtils.encrypt(mode, input, secretKey, ivParameterSpec);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }

            @Override
            public String decrypt(String cipherText) {
                try {
                    return AESUtils.decrypt(mode, cipherText, secretKey, ivParameterSpec);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage());
                }
            }
        };
    }

}
