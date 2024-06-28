/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto.pem;

import org.bouncycastle.util.io.pem.PemReader;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.security.*;
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import static spring.turbo.io.IOExceptionUtils.toUnchecked;

/**
 * 这是一个小工具。可以从PEM文件中读取ECDSA公钥和私钥 <br>
 * 此工具依赖 <a href="https://search.maven.org/search?q=bcprov-jdk15to18">BouncyCastle</a>
 *
 * <pre>
 *     openssl ecparam -list_curves
 *     openssl ecparam -name prime256v1 -genkey -noout -out private.ec.key
 *     openssl pkcs8 -topk8 -in private.ec.key -out ecdsa-private-key.pem -nocrypt
 *     openssl ec -in ecdsa-private-key.pem -pubout -out ecdsa-public-key.pem
 * </pre>
 *
 * @author 应卓
 * @since 3.3.2
 */
public final class ECDSAPemHelper {

    private static final String ALGORITHM = "EC";

    /**
     * 私有构造方法
     */
    private ECDSAPemHelper() {
        super();
    }

    public static KeyPair readKeyPair(Resource publicKeyResource, Resource privateKeyResource) {
        return new KeyPair(
                (PublicKey) readX509PublicKey(publicKeyResource),
                (PrivateKey) readPKCS8PrivateKey(privateKeyResource)
        );
    }

    public static ECPublicKey readX509PublicKey(Resource resource) {
        try {
            return readX509PublicKey(resource.getInputStream());
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    public static ECPublicKey readX509PublicKey(InputStream inputStream) {
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
            return (ECPublicKey) factory.generatePublic(spec);
        } catch (IOException e) {
            throw toUnchecked(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    public static ECPrivateKey readPKCS8PrivateKey(Resource resource) {
        try {
            return readPKCS8PrivateKey(resource.getInputStream());
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    public static ECPrivateKey readPKCS8PrivateKey(InputStream inputStream) {
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
            return (ECPrivateKey) factory.generatePrivate(spec);
        } catch (IOException e) {
            throw toUnchecked(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

}
