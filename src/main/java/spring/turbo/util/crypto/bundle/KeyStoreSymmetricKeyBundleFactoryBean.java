package spring.turbo.util.crypto.bundle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.io.ApplicationResourceLoader;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.ClassUtils;
import spring.turbo.util.crypto.keystore.KeyStoreFormat;
import spring.turbo.util.crypto.keystore.KeyStoreHelper;

/**
 * @author 应卓
 * @since 3.3.1
 */
public class KeyStoreSymmetricKeyBundleFactoryBean implements FactoryBean<SymmetricKeyBundle>, ResourceLoaderAware, InitializingBean {

    private ResourceLoader resourceLoader = new ApplicationResourceLoader(ClassUtils.getDefaultClassLoader());
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
        var resource = resourceLoader.getResource(location);

        try (var inputStream = resource.getInputStream()) {
            var keyStore = KeyStoreHelper.loadKeyStore(inputStream, format, storepass);
            var key = KeyStoreHelper.getKey(keyStore, alias, keypass);
            this.bundle = new SymmetricKeyBundleImpl(key);
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
