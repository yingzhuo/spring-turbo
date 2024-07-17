package spring.turbo.util.crypto.bundle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import spring.turbo.util.crypto.pem.PemReadingUtils;

import java.security.KeyPair;
import java.security.PrivateKey;

/**
 * @since 应卓
 * @since 3.3.1
 */
public class PemAsymmetricKeyBundleFactoryBean implements FactoryBean<AsymmetricKeyBundle>, ResourceLoaderAware, InitializingBean {

    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    private String certificateLocation;
    private String keyLocation;
    private String keyPassword;
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
    public void afterPropertiesSet() {
        var certResource = resourceLoader.getResource(certificateLocation);
        var keyResource = resourceLoader.getResource(keyLocation);

        var cert = PemReadingUtils.readX509PemCertificate(certResource);
        var privateKey = (PrivateKey) PemReadingUtils.readPkcs8Key(keyResource, keyPassword);
        this.bundle = new AsymmetricKeyBundleImpl(new KeyPair(cert.getPublicKey(), privateKey), cert);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setCertificateLocation(String certificateLocation) {
        this.certificateLocation = certificateLocation;
    }

    public void setKeyLocation(String keyLocation) {
        this.keyLocation = keyLocation;
    }

    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

}
