/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import java.io.Serializable;

import static spring.turbo.util.crypto.Base64.decode;

/**
 * @author 应卓
 * @since 1.0.0
 */
public interface Keys extends StringifiedKeyPairProvider, Serializable {

    public String getBase64PublicKey();

    public String getBase64PrivateKey();

    public default byte[] getPublicKey() {
        return decode(getBase64PublicKey());
    }

    public default byte[] getPrivateKey() {
        return decode(getBase64PrivateKey());
    }

    @Override
    public default StringifiedKeyPair getStringifiedKeyPair() {
        return new StringifiedKeyPair(getBase64PublicKey(), getBase64PrivateKey());
    }

}
