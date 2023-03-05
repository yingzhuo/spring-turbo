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

import javax.crypto.Cipher;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class RSABuilder {

    @Nullable
    private byte[] publicKey;

    @Nullable
    private byte[] privateKey;

    RSABuilder() {
        super();
    }

    public RSABuilder keyPair(RSAKeys keyPair) {
        Asserts.notNull(keyPair);
        this.publicKey = keyPair.getPublicKey();
        this.privateKey = keyPair.getPrivateKey();
        return this;
    }

    public RSA build() {
        Asserts.notNull(publicKey);
        Asserts.notNull(privateKey);

        return new RSA() {
            @Override
            public byte[] encryptByPublicKey(byte[] data) {
                try {
                    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    PublicKey publicKey = factory.generatePublic(keySpec);
                    Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
                    cipher.init(Cipher.ENCRYPT_MODE, publicKey);
                    return cipher.doFinal(data);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }
            }

            @Override
            public byte[] decryptByPrivateKey(byte[] encryptedData) {
                try {
                    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    PrivateKey privateKey = factory.generatePrivate(keySpec);
                    Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
                    cipher.init(Cipher.DECRYPT_MODE, privateKey);
                    return cipher.doFinal(encryptedData);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }
            }

            @Override
            public byte[] encryptByPrivateKey(byte[] data) {
                try {
                    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(privateKey);
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    PrivateKey privateKey = factory.generatePrivate(keySpec);
                    Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
                    cipher.init(Cipher.ENCRYPT_MODE, privateKey);
                    return cipher.doFinal(data);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }
            }

            @Override
            public byte[] decryptByPublicKey(byte[] encryptedData) {
                try {
                    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    PublicKey publicKey = factory.generatePublic(keySpec);
                    Cipher cipher = Cipher.getInstance(factory.getAlgorithm());
                    cipher.init(Cipher.DECRYPT_MODE, publicKey);
                    return cipher.doFinal(encryptedData);
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }
            }

            @Override
            public byte[] sign(byte[] data) {
                try {
                    PKCS8EncodedKeySpec pkcs = new PKCS8EncodedKeySpec(privateKey);
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    PrivateKey key = factory.generatePrivate(pkcs);
                    Signature signature = Signature.getInstance("MD5withRSA");
                    signature.initSign(key);
                    signature.update(data);
                    return signature.sign();
                } catch (Exception e) {
                    throw new IllegalArgumentException(e.getMessage(), e);
                }
            }

            @Override
            public boolean verify(byte[] data, byte[] sign) {
                try {
                    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(publicKey);
                    KeyFactory factory = KeyFactory.getInstance("RSA");
                    PublicKey key = factory.generatePublic(keySpec);
                    Signature signature = Signature.getInstance("MD5withRSA");
                    signature.initVerify(key);
                    signature.update(data);
                    return signature.verify(sign);
                } catch (Exception e) {
                    return false;
                }
            }
        };
    }

}
