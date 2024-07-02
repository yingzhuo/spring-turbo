/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * 签名工具
 *
 * @author 应卓
 * @see CipherUtils
 * @since 3.3.1
 */
public final class SignerUtils {

    /**
     * 私有构造方法
     */
    private SignerUtils() {
        super();
    }

    public static byte[] sign(PrivateKey privateKey, byte[] data, String sigAlgName) {
        try {
            var signature = Signature.getInstance(sigAlgName);
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    public static boolean verify(PublicKey publicKey, byte[] data, byte[] sign, String sigAlgName) {
        try {
            var signature = Signature.getInstance(sigAlgName);
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
