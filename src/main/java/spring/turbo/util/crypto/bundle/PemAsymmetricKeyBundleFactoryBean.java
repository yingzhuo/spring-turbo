package spring.turbo.util.crypto.bundle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import spring.turbo.util.crypto.pem.CertificatePemHelper;
import spring.turbo.util.crypto.pem.PemHelper;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * @since 应卓
 * @since 3.3.1
 */
public class PemAsymmetricKeyBundleFactoryBean
        implements FactoryBean<AsymmetricKeyBundle>, ResourceLoaderAware, InitializingBean {

    private ResourceLoader resourceLoader = new DefaultResourceLoader();
    private String certificateLocation;
    private String keyLocation;
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
        var certResource = resourceLoader.getResource(certificateLocation);
        var keyResource = resourceLoader.getResource(keyLocation);

        try (var certInput = certResource.getInputStream();
             var keyInput = keyResource.getInputStream()) {

            X509Certificate cert = CertificatePemHelper.readX509PemCertificate(certResource);
            var factory = KeyFactory.getInstance(cert.getPublicKey().getAlgorithm());
            var spec = new PKCS8EncodedKeySpec(PemHelper.readPemBytes(keyInput));
            var privateKey = factory.generatePrivate(spec);
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

}
