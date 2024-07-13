package spring.turbo.util.crypto.bundle;

import org.springframework.core.style.ToStringCreator;

import java.security.KeyPair;
import java.security.cert.Certificate;

/**
 * @author 应卓
 * @since 3.3.1
 */
final class AsymmetricKeyBundleImpl implements AsymmetricKeyBundle {

    private final KeyPair keyPair;
    private final Certificate certificate;

    public AsymmetricKeyBundleImpl(KeyPair keyPair, Certificate certificate) {
        this.keyPair = keyPair;
        this.certificate = certificate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public KeyPair getKeyPair() {
        return this.keyPair;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public <T extends Certificate> T getCertificate() {
        return (T) this.certificate;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        var creator = new ToStringCreator(this);
        creator.append("algorithm", getAlgorithm());
        creator.append("sigAlgName", getSigAlgName());
        return creator.toString();
    }

}
