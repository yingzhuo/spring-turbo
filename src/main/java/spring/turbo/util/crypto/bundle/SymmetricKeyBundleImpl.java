package spring.turbo.util.crypto.bundle;

import org.springframework.core.style.ToStringCreator;

import java.security.Key;

/**
 * @author 应卓
 * @since 3.3.1
 */
@SuppressWarnings("unchecked")
public class SymmetricKeyBundleImpl implements SymmetricKeyBundle {

    private final Key key;

    public SymmetricKeyBundleImpl(Key key) {
        this.key = key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public <T extends Key> T getKey() {
        return (T) key;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        var creator = new ToStringCreator(this);
        creator.append("algorithm", getAlgorithm());
        return creator.toString();
    }

}
