/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.util.Asserts;

import java.security.KeyFactory;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author 应卓
 * @see #build()
 * @since 1.0.0
 */
public final class DSABuilder {

    private byte[] publicKey;
    private byte[] privateKey;

    DSABuilder() {
        super();
    }

    public DSABuilder keyPair(DSAKeys keyPair) {
        Asserts.notNull(keyPair);
        this.privateKey = keyPair.getPrivateKey();
        this.publicKey = keyPair.getPublicKey();
        return this;
    }

    public DSA build() {
        return new DSA() {
            @Override
            public byte[] sign(byte[] data) {
                try {
                    // 构造PKCS8EncodedKeySpec对象
                    PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(privateKey);
                    // 指定的加密算法
                    KeyFactory factory = KeyFactory.getInstance("DSA");
                    // 取私钥对象
                    java.security.PrivateKey key = factory.generatePrivate(pkcs);
                    // 用私钥对信息生成数字签名
                    java.security.Signature dsa = java.security.Signature.getInstance("SHA1withDSA", "SUN");
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
                    KeyFactory factory = KeyFactory.getInstance("DSA");
                    // 取公钥对象
                    java.security.PublicKey key = factory.generatePublic(keySpec);
                    // 用公钥验证数字签名
                    java.security.Signature dsa = java.security.Signature.getInstance("SHA1withDSA", "SUN");
                    dsa.initVerify(key);
                    dsa.update(data);
                    return dsa.verify(sign);
                } catch (Exception e) {
                    return false;
                }
            }
        };
    }

}
