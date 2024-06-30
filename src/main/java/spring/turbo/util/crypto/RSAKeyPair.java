/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import java.security.KeyPair;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

/**
 * 对{@link KeyPair} 简易封装，方便保存到数据库或文本中。
 *
 * @author 应卓
 * @see RSAKeyPairFactories
 * @since 3.3.2
 */
public interface RSAKeyPair extends TextizedKeyPair<RSAPublicKey, RSAPrivateKey> {

    /**
     * {@inheritDoc}
     */
    @Override
    public default String getAlgorithmName() {
        return RSAKeyPairFactories.ALG_RSA;
    }

}
