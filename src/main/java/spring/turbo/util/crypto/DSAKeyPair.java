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
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;
import java.util.Base64;

/**
 * 对{@link KeyPair} 简易封装，方便保存到数据库或文本中。
 *
 * @author 应卓
 * @see DSAKeyPairFactories
 * @since 3.3.2
 */
public interface DSAKeyPair extends Serializable {

    public DSAPublicKey getJdkPublicKey();

    public DSAPrivateKey getJdkPrivateKey();

    public default String getBase64EncodedPublicKey() {
        var encoder = Base64.getEncoder();
        return encoder.encodeToString(getJdkPublicKey().getEncoded());
    }

    public default String getBase64EncodedPrivateKey() {
        var encoder = Base64.getEncoder();
        return encoder.encodeToString(getJdkPrivateKey().getEncoded());
    }

    public default String getHexEncodedPublicKey() {
        return HexUtils.encodeToString(getJdkPublicKey().getEncoded());
    }

    public default String getHexEncodedPrivateKey() {
        return HexUtils.encodeToString(getJdkPrivateKey().getEncoded());
    }

    public default KeyPair getJdkKeyPair() {
        return new KeyPair(getJdkPublicKey(), getJdkPrivateKey());
    }

}
