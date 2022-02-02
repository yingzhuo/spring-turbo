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
import spring.turbo.util.Asserts;

import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author 应卓
 * @see #build()
 * @since 1.0.0
 */
public final class ECDSABuilder {

    @Nullable
    private byte[] publicKey;

    @Nullable
    private byte[] privateKey;

    ECDSABuilder() {
        super();
    }

    public ECDSABuilder keyPair(ECDSAKeys keyPair) {
        Asserts.notNull(keyPair);
        this.publicKey = keyPair.getPublicKey();
        this.privateKey = keyPair.getPrivateKey();
        return this;
    }

    public ECDSA build() {
        Asserts.notNull(publicKey);
        Asserts.notNull(privateKey);

        return new ECDSA() {
            @Override
            public byte[] sign(byte[] data) {
                try {
                    // 构造PKCS8EncodedKeySpec对象
                    PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(privateKey);
                    // 指定的加密算法
                    KeyFactory factory = KeyFactory.getInstance("EC");
                    // 取私钥对象
                    java.security.PrivateKey key = factory.generatePrivate(pkcs);
                    // 用私钥对信息生成数字签名
                    java.security.Signature dsa = java.security.Signature.getInstance("SHA1withECDSA");
                    dsa.initSign(key);
                    dsa.update(data);
                    return dsa.sign();
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }

            }

            @Override
            public boolean verify(byte[] data, byte[] sign) {
                try {
                    // 构造X509EncodedKeySpec对象
                    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
                    // 指定的加密算法
                    KeyFactory factory = KeyFactory.getInstance("EC");
                    // 取公钥对象
                    java.security.PublicKey key = factory.generatePublic(keySpec);
                    // 用公钥验证数字签名
                    java.security.Signature ecdsa = java.security.Signature.getInstance("SHA1withECDSA");
                    ecdsa.initVerify(key);
                    ecdsa.update(data);
                    return ecdsa.verify(sign);
                } catch (Exception e) {
                    return false;
                }
            }
        };
    }

}
