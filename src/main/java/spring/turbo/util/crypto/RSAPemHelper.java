/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import org.bouncycastle.util.io.pem.PemReader;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 这是一个小工具。可以从PEM文件中读取RSA公钥和私钥
 *
 * <pre>
 *     openssl genrsa -out keypair.pem 2048
 *     openssl rsa -in keypair.pem -pubout -inform PEM -outform PEM -out public-key.pem
 *     openssl pkcs8 -topk8 -inform PEM -outform PEM -nocrypt -in keypair.pem -out private-key.pem
 * </pre>
 *
 * @author 应卓
 * @since 3.3.2
 */
public final class RSAPemHelper {

    private static final String ALGORITHM = "RSA";

    /**
     * 私有构造方法
     */
    private RSAPemHelper() {
        super();
    }

    public static RSAPublicKey readX509PublicKey(InputStream inputStream) {
        KeyFactory factory;

        try {
            factory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(); // 实际不会发生此异常
        }

        try (var keyReader = new InputStreamReader(inputStream); var pemReader = new PemReader(keyReader)) {
            var pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            var spec = new X509EncodedKeySpec(content);
            return (RSAPublicKey) factory.generatePublic(spec);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static RSAPrivateKey readPKCS8PrivateKey(InputStream inputStream) {
        KeyFactory factory;

        try {
            factory = KeyFactory.getInstance(ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            throw new AssertionError(); // 实际不会发生此异常
        }

        try (var keyReader = new InputStreamReader(inputStream); var pemReader = new PemReader(keyReader)) {
            var pemObject = pemReader.readPemObject();
            byte[] content = pemObject.getContent();
            var spec = new PKCS8EncodedKeySpec(content);
            return (RSAPrivateKey) factory.generatePrivate(spec);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

}
