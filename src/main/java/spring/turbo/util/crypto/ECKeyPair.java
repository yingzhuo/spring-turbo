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
