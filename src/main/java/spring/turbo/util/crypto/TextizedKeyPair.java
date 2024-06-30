/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.util.HexUtils;

import java.io.Serializable;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.Base64;

/**
 * 文本化的密钥对
 *
 * @param <PUB> 公钥类型泛型
 * @param <PRI> 私钥类型泛型
 * @author 应卓
 * @see KeyPair
 * @since 3.2.2
 */
public interface TextizedKeyPair<PUB extends PublicKey, PRI extends PrivateKey> extends Serializable {

    /**
     * 获取算法名称
     *
     * @return 算法名称
     */
    public String getAlgorithmName();

    /**
     * 获取公钥
     *
     * @return 公钥
     */
    public PUB getJdkPublicKey();

    /**
     * 获取私钥
     *
     * @return 私钥
     */
    public PRI getJdkPrivateKey();

    /**
     * 获取密钥对
     *
     * @return 密钥对
     */
    public default KeyPair getJdkKeyPair() {
        return new KeyPair(getJdkPublicKey(), getJdkPrivateKey());
    }

    /**
     * 获取公钥的二进制数据
     *
     * @return 公钥的二进制数据
     */
    public default byte[] getJdkPublicKeyAsBytes() {
        return getJdkPublicKey().getEncoded();
    }

    /**
     * 获取私钥的二进制数据
     *
     * @return 私钥的二进制数据
     */
    public default byte[] getJdkPrivateKeyAsBytes() {
        return getJdkPrivateKey().getEncoded();
    }

    /**
     * 获取Base64文本化的公钥
     *
     * @return 文本化的公钥
     */
    public default String getBase64EncodedPublicKey() {
        var encoder = Base64.getEncoder().withoutPadding();
        return encoder.encodeToString(getJdkPublicKeyAsBytes());
    }

    /**
     * 获取Base64文本化的私钥
     *
     * @return 文本化的私钥
     */
    public default String getBase64EncodedPrivateKey() {
        var encoder = Base64.getEncoder().withoutPadding();
        return encoder.encodeToString(getJdkPrivateKeyAsBytes());
    }

    /**
     * 获取HEX文本化的公钥
     *
     * @return 文本化的公钥
     */
    public default String getHexEncodedPublicKey() {
        return HexUtils.encodeToString(getJdkPublicKeyAsBytes());
    }

    /**
     * 获取HEX文本化的私钥
     *
     * @return 文本化的私钥
     */
    public default String getHexEncodedPrivateKey() {
        return HexUtils.encodeToString(getJdkPrivateKeyAsBytes());
    }

}
