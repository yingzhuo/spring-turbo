/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.interfaces.DSAPrivateKey;
import java.security.interfaces.DSAPublicKey;

/**
 * @author 应卓
 * @since 3.3.1
 */
class DSAImpl implements DSA {

    private final DSAPublicKey publicKey;
    private final DSAPrivateKey privateKey;

    public DSAImpl(DSAPublicKey publicKey, DSAPrivateKey privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public byte[] sign(byte[] data, SignerAlgorithm signerAlgorithm) {
        try {
            var signature = Signature.getInstance(signerAlgorithm.getAlgorithmName());
            signature.initSign(privateKey);
            signature.update(data);
            return signature.sign();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean verify(byte[] data, byte[] sign, SignerAlgorithm signerAlgorithm) {
        try {
            var signature = Signature.getInstance(signerAlgorithm.getAlgorithmName());
            signature.initVerify(publicKey);
            signature.update(data);
            return signature.verify(sign);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }

}
