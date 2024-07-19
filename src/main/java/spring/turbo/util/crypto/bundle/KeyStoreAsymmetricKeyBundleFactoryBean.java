package spring.turbo.util.crypto.bundle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.io.ApplicationResourceLoader;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import spring.turbo.util.crypto.keystore.KeyStoreFormat;

import java.security.KeyPair;

import static spring.turbo.util.crypto.keystore.KeyStoreHelper.*;

/**
 * @author 应卓
 * @since 3.3.1
 */
public class KeyStoreAsymmetricKeyBundleFactoryBean
        implements FactoryBean<AsymmetricKeyBundle>, ResourceLoaderAware, InitializingBean {

    private ResourceLoader resourceLoader = new ApplicationResourceLoader(ClassUtils.getDefaultClassLoader());
    private String location;
    private KeyStoreFormat format = KeyStoreFormat.PKCS12;
    private String storepass;
    private String alias;
    private String keypass;
    private AsymmetricKeyBundle bundle;

    /**
     * {@inheritDoc}
     */
    @Override
    public AsymmetricKeyBundle getObject() {
        return this.bundle;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getObjectType() {
        return AsymmetricKeyBundle.class;
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

        var keyStoreResource = resourceLoader.getResource(location);

        try (var inputStream = keyStoreResource.getInputStream()) {
            var store = loadKeyStore(inputStream, format, storepass);
            var cert = getCertificate(store, alias);
            var privateKey = getPrivateKey(store, alias, keypass);
            this.bundle = new AsymmetricKeyBundleImpl(new KeyPair(cert.getPublicKey(), privateKey), cert);
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
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
