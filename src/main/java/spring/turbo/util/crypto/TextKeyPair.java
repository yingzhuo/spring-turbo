/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.util.StringFormatter;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.security.KeyPair;

/**
 * 文本化的密钥对
 *
 * @author 应卓
 * @see KeyPair
 * @see java.security.KeyPairGenerator
 * @see RSATextKeyPair
 * @see DSATextKeyPair
 * @see ECDSATextKeyPair
 * @since 3.3.1
 */
public abstract class TextKeyPair implements Serializable {

    /**
     * 获取公钥
     *
     * @return 公钥
     */
    public abstract String getPublicKeyAsBase64EncodedString();

    /**
     * 获取私钥
     *
     * @return 私钥
     */
    public abstract String getPrivateKeyAsBase64EncodedString();

    /**
     * 获取公钥
     *
     * @return 公钥
     */
    public abstract byte[] getPublicKeyAsBytes();

    /**
     * 获取私钥
     *
     * @return 私钥
     */
    public abstract byte[] getPrivateKeyAsBytes();

    /**
     * 获取公钥
     *
     * @return 公钥
     */
    public InputStream getPublicKeyAsInputStream() {
        return new ByteArrayInputStream(getPublicKeyAsBytes());
    }

    /**
     * 获取私钥
     *
     * @return 私钥
     */
    public InputStream getPrivateKeyAsInputStream() {
        return new ByteArrayInputStream(getPrivateKeyAsBytes());
    }

    /**
     * 获取算法
     *
     * @return 算法
     */
    public abstract String getAlgorithm();

    /**
     * 转换成 {@link KeyPair} 对象
     *
     * @return {@link KeyPair} 实例
     */
    public KeyPair toKeyPair() {
        return KeyPairLoadingUtils.loadQuietly(
                getAlgorithm(),
                getPublicKeyAsInputStream(),
                getPrivateKeyAsInputStream()
        );
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return StringFormatter.format(
                "publicKey:'{}' | privateKey:'{}'",
                getPublicKeyAsBase64EncodedString(),
                getPrivateKeyAsBase64EncodedString()
        );
    }

}
