/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Deprecated(since = "3.3.1")
final class TripleDESImpl implements TripleDES {

    private final SecretKeySpec secretKeySpec;
    private final IvParameterSpec ivSpec;

    TripleDESImpl(String password, String salt) {
        this.secretKeySpec = new SecretKeySpec(password.getBytes(), "TripleDES");
        this.ivSpec = new IvParameterSpec(salt.getBytes());
    }

    @Override
    public String encrypt(String input) {
        try {
            Cipher cipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKeySpec, ivSpec);
            byte[] bytes = cipher.doFinal(input.getBytes(UTF_8));
            return Base64Utils.encodeToString(bytes, true, true);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    @Override
    public String decrypt(String cipherText) {
        try {
            Cipher cipher = Cipher.getInstance("TripleDES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec);
            byte[] bytes = cipher.doFinal(Base64Utils.decode(cipherText));
            return new String(bytes, UTF_8);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
