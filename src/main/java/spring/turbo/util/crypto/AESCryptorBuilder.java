/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import org.springframework.util.Assert;

import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class AESCryptorBuilder {

    private final IvParameterSpec ivParameterSpec = AESUtils.generateIv();
    private AESMode mode = AESMode.CBC;
    private SecretKey secretKey;

    AESCryptorBuilder() {
    }

    public AESCryptorBuilder passwordAndSalt(String password, String salt) {
        Assert.hasText(password, "password is blank");
        Assert.hasText(salt, "salt is blank");

        try {
            this.secretKey = AESUtils.getKeyFromPassword(password, salt);
            return this;
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public AESCryptorBuilder mode(AESMode mode) {
        Assert.notNull(mode, "mode is null");
        this.mode = mode;
        return this;
    }

    public AESCryptor build() {
        Assert.notNull(secretKey, "secretKey is null");

        return new AESCryptor() {
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
