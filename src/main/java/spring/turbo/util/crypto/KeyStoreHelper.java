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

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.security.*;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;
import java.util.Objects;

/**
 * {@link KeyStore} 相关工具类
 *
 * @author 应卓
 * @see KeyStoreFormat
 * @see <a href="https://github.com/yingzhuo/spring-turbo/wiki/2024%E2%80%9007%E2%80%9002%E2%80%90keytool%E2%80%90cheatsheet">2024‐07‐02‐keytool‐cheatsheet</a>
 * @since 3.3.1
 */
@SuppressWarnings("unchecked")
public class KeyStoreHelper {

    /**
     * 私有构造方法
     */
    private KeyStoreHelper() {
        super();
    }

    /**
     * 加载密钥库
     *
     * @param inputStream 输入流
     * @param storepass   秘钥库的口令
     * @return 密钥库
     * @throws UncheckedIOException     IO错误
     * @throws IllegalArgumentException 其他错误
     */
    public static KeyStore loadKeyStore(InputStream inputStream, String storepass) {
        return loadKeyStore(inputStream, KeyStoreFormat.PKCS12, storepass);
    }

    /**
     * 加载密钥库
     *
     * @param inputStream    输入流
     * @param keyStoreFormat 密钥库格式
     * @param storepass      秘钥库的口令
     * @return 密钥库
     * @throws UncheckedIOException     IO错误
     * @throws IllegalArgumentException 其他错误
     */
    public static KeyStore loadKeyStore(InputStream inputStream, @Nullable KeyStoreFormat keyStoreFormat, String storepass) {
        Asserts.notNull(inputStream, "inputStream is required");
        Asserts.notNull(storepass, "storepass is required");

        keyStoreFormat = Objects.requireNonNullElseGet(keyStoreFormat, KeyStoreFormat::getDefault);

        try {
            var keyStore = KeyStore.getInstance(keyStoreFormat.getValue());
            keyStore.load(inputStream, storepass.toCharArray());
            return keyStore;
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * 获取秘钥
     *
     * @param keyStore 已加载的密钥库
     * @param alias    条目名称
     * @param keypass  秘钥的密码
     * @param <T>      秘钥类型泛型
     * @return 秘钥
     */
    public static <T extends Key> T getKey(KeyStore keyStore, String alias, String keypass) {
        Asserts.notNull(keyStore, "keyStore is required");
        Asserts.hasText(alias, "alias is required");
        Asserts.notNull(keypass, "privateKeyPass is required");

        try {
            return (T) keyStore.getKey(alias, keypass.toCharArray());
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 从秘钥库中获取私钥
     *
     * @param keyStore 已加载的密钥库
     * @param alias    条目名称
     * @param keypass  私钥的密码
     * @param <T>      私钥类型的泛型
     * @return 私钥
     * @see #loadKeyStore(InputStream, String) 加载密钥库
     * @see #loadKeyStore(InputStream, KeyStoreFormat, String) 加载密钥库
     */
    public static <T extends PrivateKey> T getPrivateKey(KeyStore keyStore, String alias, String keypass) {
        return getKey(keyStore, alias, keypass);
    }

    /**
     * 从密钥库中获取公钥
     *
     * @param keyStore 已加载的密钥库
     * @param alias    条目名称
     * @param <T>      私钥类型的泛型
     * @return 公钥
     * @see #loadKeyStore(InputStream, String) 加载密钥库
     * @see #loadKeyStore(InputStream, KeyStoreFormat, String) 加载密钥库
     */
    public static <T extends PublicKey> T getPublicKey(KeyStore keyStore, String alias) {
        var cert = getCertificate(keyStore, alias);
        return (T) cert.getPublicKey();
    }

    /**
     * 从密钥库中获取证书，公钥保存在证书之中
     *
     * @param keyStore 已加载的密钥库
     * @param alias    条目名称
     * @param <T>      证书类型的泛型
     * @return 证书
     * @see #loadKeyStore(InputStream, String) 加载密钥库
     * @see #loadKeyStore(InputStream, KeyStoreFormat, String) 加载密钥库
     */
    public static <T extends Certificate> T getCertificate(KeyStore keyStore, String alias) {
        Asserts.notNull(keyStore, "keyStore is required");
        Asserts.hasText(alias, "alias is required");

        try {
            return (T) keyStore.getCertificate(alias);
        } catch (KeyStoreException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * 从密钥库中获取密钥对
     *
     * @param keyStore 已加载的密钥库
     * @param alias    条目名称
     * @param keypass  私钥类型的泛型
     * @return 密钥对
     * @see #loadKeyStore(InputStream, String) 加载密钥库
     * @see #loadKeyStore(InputStream, KeyStoreFormat, String) 加载密钥库
     */
    public static KeyPair getKeyPair(KeyStore keyStore, String alias, String keypass) {
        return new KeyPair(
                getPublicKey(keyStore, alias),
                getPrivateKey(keyStore, alias, keypass)
        );
    }

    /**
     * 获取签名算法名称
     *
     * @param keyStore 已加载的密钥库
     * @param alias    条目名称
     * @return 签名算法名称
     * @see #loadKeyStore(InputStream, String) 加载密钥库
     * @see #loadKeyStore(InputStream, KeyStoreFormat, String) 加载密钥库
     */
    public static String getSigAlgName(KeyStore keyStore, String alias) {
        var cert = getCertificate(keyStore, alias);
        if (cert instanceof X509Certificate x509Cert) {
            return x509Cert.getSigAlgName();
        }
        throw new IllegalArgumentException("cannot get SigAlg");
    }

    /**
     * 获取签名算法OID
     *
     * @param keyStore 已加载的密钥库
     * @param alias    条目名称
     * @return 签名算法OID
     * @see #loadKeyStore(InputStream, String) 加载密钥库
     * @see #loadKeyStore(InputStream, KeyStoreFormat, String) 加载密钥库
     */
    public static String getSigAlgOID(KeyStore keyStore, String alias) {
        var cert = getCertificate(keyStore, alias);
        if (cert instanceof X509Certificate x509Cert) {
            return x509Cert.getSigAlgOID();
        }
        throw new IllegalArgumentException("cannot get SigAlgOID");
    }

    /**
     * 测试秘钥库是否包含条目
     *
     * @param keyStore 已加载的密钥库
     * @param alias    条目名称
     * @return 结果
     * @see #loadKeyStore(InputStream, String) 加载密钥库
     * @see #loadKeyStore(InputStream, KeyStoreFormat, String) 加载密钥库
     */
    public static boolean containsAlias(KeyStore keyStore, String alias) {
        try {
            return keyStore.containsAlias(alias);
        } catch (KeyStoreException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
