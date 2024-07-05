package spring.turbo.util.crypto;

import java.security.KeyPair;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

/**
 * 对{@link KeyPair} 简易封装，方便保存到数据库或文本中。
 *
 * @author 应卓
 * @see DSAKeyPairFactories
 * @since 3.3.1
 */
public interface DSAKeyPair extends TextizedKeyPair<DSAPublicKey, DSAPrivateKey> {

    /**
     * {@inheritDoc}
     */
    @Override
    public default String getAlgorithmName() {
        return DSAKeyPairFactories.ALG_DSA;
    }

}
