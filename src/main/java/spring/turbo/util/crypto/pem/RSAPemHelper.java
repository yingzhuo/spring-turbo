/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto.pem;

import org.springframework.core.io.Resource;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

/**
 * 这是一个小工具。可以从PEM文件中读取RSA公钥和私钥 <br>
 * 此工具依赖 <a href="https://search.maven.org/search?q=bcprov-jdk18oon">Bouncy Castle</a>
 *
 * @author 应卓
 * @see <a href="https://github.com/yingzhuo/spring-turbo/wiki/2024%E2%80%9007%E2%80%9002%E2%80%90openssl%E2%80%90cheatsheet">2024‐07‐02‐openssl‐cheatsheet</a>
 * @see <a href="https://www.openssl.org/">OpenSSL官方文档</a>
 * @see <a href="https://en.wikipedia.org/wiki/X.509">X509 wiki</a>
 * @see <a href="https://en.wikipedia.org/wiki/PKCS_8">PKCS#8 wiki</a>
 * @since 3.3.1
 */
public final class RSAPemHelper {

    private static final String ALGORITHM = "RSA";

    /**
     * 私有构造方法
     */
    private RSAPemHelper() {
    }

    /**
     * 读取公钥和私钥
     *
     * @param publicKeyResource  公钥资源
     * @param privateKeyResource 私钥资源
     * @return {@link KeyPair} 实例
     */
    public static KeyPair readKeyPair(Resource publicKeyResource, Resource privateKeyResource) {
        return new KeyPair(
                readX509PublicKey(publicKeyResource),
                readPKCS8PrivateKey(privateKeyResource)
        );
    }

    /**
     * 读取公钥 <br>
     * <em>注意: 必须是X509格式</em>
     *
     * @param resource 资源
     * @return 公钥
     */
    public static RSAPublicKey readX509PublicKey(Resource resource) {
        try {
            return readX509PublicKey(resource.getInputStream());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 读取公钥 <br>
     * <em>注意: 必须是X509格式</em>
     *
     * @param inputStream 资源
     * @return 公钥
     */
    public static RSAPublicKey readX509PublicKey(InputStream inputStream) {
        try {
            var factory = KeyFactory.getInstance(ALGORITHM);
            var spec = new X509EncodedKeySpec(PemHelper.readPemBytes(inputStream));
            return (RSAPublicKey) factory.generatePublic(spec);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * 读取私钥 <br>
     * <em>注意: 必须是PKCS8格式</em>
     *
     * @param resource 资源
     * @return 私钥
     */
    public static RSAPrivateKey readPKCS8PrivateKey(Resource resource) {
        try {
            return readPKCS8PrivateKey(resource.getInputStream());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 读取私钥 <br>
     * <em>注意: 必须是PKCS8格式</em>
     *
     * @param inputStream 资源
     * @return 私钥
     */
    public static RSAPrivateKey readPKCS8PrivateKey(InputStream inputStream) {
        try {
            var factory = KeyFactory.getInstance(ALGORITHM);
            var spec = new PKCS8EncodedKeySpec(PemHelper.readPemBytes(inputStream));
            return (RSAPrivateKey) factory.generatePrivate(spec);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
