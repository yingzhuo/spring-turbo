package spring.turbo.util.crypto.bundle;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.X509Certificate;

/**
 * 对非对称加密算法秘钥和证书的封装
 *
 * @author 应卓
 * @see spring.turbo.util.crypto.SignatureUtils
 * @see spring.turbo.util.crypto.CipherUtils
 * @see PemAsymmetricKeyBundleFactoryBean
 * @see KeyStoreAsymmetricKeyBundleFactoryBean
 * @since 3.3.1
 */
@SuppressWarnings("unchecked")
public interface AsymmetricKeyBundle extends Serializable {

    /**
     * 获取KeyPair
     *
     * @return KeyPair实例
     */
    public KeyPair getKeyPair();

    /**
     * 获取证书
     *
     * @param <T> 证书泛型类型
     * @return 证书
     */
    public <T extends X509Certificate> T getCertificate();

    /**
     * 获取公钥
     *
     * @return 公钥实例
     */
    public default <T extends PublicKey> T getPublicKey() {
        return (T) getKeyPair().getPublic();
    }

    /**
     * 获取私钥
     *
     * @return 私钥实例
     */
    public default <T extends PrivateKey> T getPrivateKey() {
        return (T) getKeyPair().getPrivate();
    }

    /**
     * 获取算法名称
     *
     * @return 算法名称 如: RSA
     */
    public default String getAlgorithm() {
        return getCertificate().getPublicKey().getAlgorithm();
    }

    /**
     * 获取签名算法名称
     *
     * @return 签名算法名称 如: SHA256WithRSA
     */
    public default String getSigAlgName() {
        return getCertificate().getSigAlgName();
    }

}
