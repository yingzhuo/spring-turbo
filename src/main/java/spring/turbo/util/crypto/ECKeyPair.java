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
import java.security.interfaces.ECPrivateKey;
import java.security.interfaces.ECPublicKey;

/**
 * 对{@link KeyPair} 简易封装，方便保存到数据库或文本中。
 *
 * @author 应卓
 * @see ECKeyPairFactories
 * @since 3.3.1
 */
public interface ECKeyPair extends TextizedKeyPair<ECPublicKey, ECPrivateKey> {

    /**
     * {@inheritDoc}
     */
    @Override
    public default String getAlgorithmName() {
        return ECKeyPairFactories.ALG_EC;
    }

}
