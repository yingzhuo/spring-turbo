package spring.turbo.util.crypto.bundle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import spring.turbo.util.crypto.keystore.KeyStoreFormat;
import spring.turbo.util.crypto.keystore.KeyStoreHelper;
import spring.turbo.core.ResourceUtils;

/**
 * @author 应卓
 * @since 3.3.1
 */
public class KeyStoreSymmetricKeyBundleFactoryBean implements FactoryBean<SymmetricKeyBundle>, InitializingBean {

    private String location;
    private KeyStoreFormat format = KeyStoreFormat.PKCS12;
    private String storepass;
    private String alias;
    private String keypass;
    private SymmetricKeyBundle bundle;

    /**
     * {@inheritDoc}
     */
    @Override
    public SymmetricKeyBundle getObject() {
        return this.bundle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getObjectType() {
        return SymmetricKeyBundle.class;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(location, () -> "location is required");
        Assert.notNull(format, () -> "format is required");
        Assert.notNull(storepass, () -> "storepass is required");
        Assert.notNull(alias, () -> "alias is required");
        Assert.notNull(keypass, () -> "keypass is required");

        var resource = ResourceUtils.load(location);

        try (var inputStream = resource.getInputStream()) {
            var keyStore = KeyStoreHelper.loadKeyStore(inputStream, format, storepass);
            var key = KeyStoreHelper.getKey(keyStore, alias, keypass);
            this.bundle = new SymmetricKeyBundleImpl(key);
        }
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFormat(KeyStoreFormat format) {
        this.format = format;
    }

    public void setStorepass(String storepass) {
        this.storepass = storepass;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public void setKeypass(String keypass) {
        this.keypass = keypass;
    }

}
