/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.jks;

import org.springframework.core.io.Resource;
import spring.turbo.io.IOExceptionUtils;
import spring.turbo.util.Asserts;
import spring.turbo.util.crypto.CipherUtils;

import java.io.IOException;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.Certificate;
import java.security.cert.X509Certificate;

/**
 * JSK秘钥库相关工具
 *
 * @author 应卓
 *
 * @see CertificateUtils
 *
 * @since 1.0.15
 */
public final class KeyStoreUtils {

    /**
     * 私有构造方法
     */
    private KeyStoreUtils() {
        super();
    }

    /**
     * 加载密钥库
     *
     * @param keyStoreResource
     *            密钥库文件
     * @param keyStoreType
     *            秘钥库种类
     * @param password
     *            密钥库
     *
     * @return 密钥库
     */
    public static KeyStore getKeyStore(Resource keyStoreResource, KeyStoreType keyStoreType, String password) {
        Asserts.notNull(keyStoreResource);
        Asserts.notNull(keyStoreType);
        Asserts.notNull(password);

        try {
            final KeyStore ks = KeyStore.getInstance(KeyStoreType.JKS.getName());
            ks.load(keyStoreResource.getInputStream(), password.toCharArray());
            return ks;
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 获得私钥
     *
     * @param keyStoreResource
     *            密钥库文件
     * @param keyStoreType
     *            秘钥库种类
     * @param alias
     *            别名
     * @param password
     *            密钥库密码
     *
     * @return 私钥
     */
    public static PrivateKey getPrivateKeyFromKeyStore(Resource keyStoreResource, KeyStoreType keyStoreType,
            String alias, String password) {
        try {
            final KeyStore ks = getKeyStore(keyStoreResource, keyStoreType, password);
            return (PrivateKey) ks.getKey(alias, password.toCharArray());
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e.getCause());
        }
    }

    /**
     * 从KeyStore文件获取公钥
     *
     * @param keyStorePath
     *            秘钥库文件
     * @param keyStoreType
     *            秘钥库种类
     * @param alias
     *            别名
     * @param password
     *            秘钥库密码
     *
     * @return 公钥
     */
    public static PublicKey getPublicKeyFromKeyStore(Resource keyStorePath, KeyStoreType keyStoreType, String alias,
            String password) {
        return getCertificateFromKeyStore(keyStorePath, keyStoreType, alias, password).getPublicKey();
    }

    /**
     * 获得数字证书
     *
     * @param keyStorePath
     *            密钥库文件
     * @param keyStoreType
     *            秘钥库种类
     * @param alias
     *            别名
     * @param password
     *            秘钥库密码
     *
     * @return 证书
     */
    public static Certificate getCertificateFromKeyStore(Resource keyStorePath, KeyStoreType keyStoreType, String alias,
            String password) {
        try {
            final KeyStore ks = getKeyStore(keyStorePath, keyStoreType, password);
            return ks.getCertificate(alias);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e.getCause());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    /**
     * 私钥加密
     *
     * @param data
     *            待加密的数据
     * @param keyStorePath
     *            密钥库文件
     * @param keyStoreType
     *            秘钥库种类
     * @param alias
     *            别名
     * @param password
     *            秘钥库密码
     *
     * @return 加密数据
     */
    public static byte[] encryptByPrivateKey(byte[] data, Resource keyStorePath, KeyStoreType keyStoreType,
            String alias, String password) {
        final PrivateKey privateKey = getPrivateKeyFromKeyStore(keyStorePath, keyStoreType, alias, password);
        return CipherUtils.encrypt(data, privateKey);
    }

    /**
     * 私钥解密
     *
     * @param data
     *            待解密数据
     * @param keyStorePath
     *            密钥库路径
     * @param keyStoreType
     *            秘钥库种类
     * @param alias
     *            别名
     * @param password
     *            秘钥库密码
     *
     * @return 解密数据
     */
    public static byte[] decryptByPrivateKey(byte[] data, Resource keyStorePath, KeyStoreType keyStoreType,
            String alias, String password) {
        final PrivateKey privateKey = getPrivateKeyFromKeyStore(keyStorePath, keyStoreType, alias, password);
        return CipherUtils.decrypt(data, privateKey);
    }

    /**
     * 公钥加密
     *
     * @param data
     *            等待加密数据
     * @param keyStorePath
     *            秘钥库文件
     * @param keyStoreType
     *            秘钥库种类
     * @param alias
     *            别名
     * @param password
     *            秘钥库密码
     *
     * @return 加密数据
     */
    public static byte[] encryptByPublicKey(byte[] data, Resource keyStorePath, KeyStoreType keyStoreType, String alias,
            String password) {
        final PublicKey publicKey = getPublicKeyFromKeyStore(keyStorePath, keyStoreType, alias, password);
        return CipherUtils.encrypt(data, publicKey);
    }

    /**
     * 公钥解密
     *
     * @param data
     *            等待解密的数据
     * @param keyStorePath
     *            秘钥库文件
     * @param keyStoreType
     *            秘钥库种类
     * @param alias
     *            别名
     * @param password
     *            秘钥库密码
     *
     * @return 解密数据
     */
    public static byte[] decryptByPublicKey(byte[] data, Resource keyStorePath, KeyStoreType keyStoreType, String alias,
            String password) {
        final PublicKey publicKey = getPublicKeyFromKeyStore(keyStorePath, keyStoreType, alias, password);
        return CipherUtils.decrypt(data, publicKey);
    }

    /**
     * 签名
     *
     * @param sign
     *            签名
     * @param keyStorePath
     *            密钥库文件
     * @param keyStoreType
     *            秘钥库种类
     * @param alias
     *            别名
     * @param password
     *            密码
     *
     * @return 签名
     */
    public static byte[] sign(byte[] sign, Resource keyStorePath, KeyStoreType keyStoreType, String alias,
            String password) {
        try {
            // 获得证书
            final X509Certificate x509Certificate = (X509Certificate) getCertificateFromKeyStore(keyStorePath,
                    keyStoreType, alias, password);
            // 构建签名,由证书指定签名算法
            Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
            // 获取私钥
            PrivateKey privateKey = getPrivateKeyFromKeyStore(keyStorePath, keyStoreType, alias, password);
            // 初始化签名，由私钥构建
            signature.initSign(privateKey);
            signature.update(sign);
            return signature.sign();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * 验证签名
     *
     * @param data
     *            数据
     * @param sign
     *            签名
     * @param keyStorePath
     *            密钥库文件
     * @param keyStoreType
     *            秘钥库种类
     * @param alias
     *            别名
     * @param password
     *            密码
     *
     * @return 验证通过为真
     */
    public static boolean verify(byte[] data, byte[] sign, Resource keyStorePath, KeyStoreType keyStoreType,
            String alias, String password) {
        try {
            // 获得证书
            final X509Certificate x509Certificate = (X509Certificate) getCertificateFromKeyStore(keyStorePath,
                    keyStoreType, alias, password);
            // 由证书构建签名
            Signature signature = Signature.getInstance(x509Certificate.getSigAlgName());
            // 由证书初始化签名，实际上是使用了证书中的公钥
            signature.initVerify(x509Certificate);
            signature.update(data);
            return signature.verify(sign);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
