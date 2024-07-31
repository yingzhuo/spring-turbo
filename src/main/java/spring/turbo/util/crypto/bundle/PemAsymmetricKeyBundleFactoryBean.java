package spring.turbo.util.crypto.bundle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import spring.turbo.core.ResourceUtils;

import java.security.KeyPair;

import static spring.turbo.util.crypto.pem.PemReadingUtils.readPkcs8PrivateKey;
import static spring.turbo.util.crypto.pem.PemReadingUtils.readX509Certificate;

/**
 * {@link AsymmetricKeyBundle} 配置用 {@link FactoryBean} <br>
 * 用PEM格式文件配置
 *
 * @see AsymmetricKeyBundle
 * @since 应卓
 * @since 3.3.1
 */
public class PemAsymmetricKeyBundleFactoryBean implements FactoryBean<AsymmetricKeyBundle>, InitializingBean {

    private String certificateLocation;
    private String certificateContent = "";
    private String privateKeyLocation;
    private String privateKeyContent = "";
    private String privateKeyPassword;
    private AsymmetricKeyBundle bundle;

    /**
     * {@inheritDoc}
     */
    @NonNull
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
        var cert = readX509Certificate(getCertContent());
        var privateKey = readPkcs8PrivateKey(getPrivateKeyContent(), privateKeyPassword);
        this.bundle = new AsymmetricKeyBundleImpl(new KeyPair(cert.getPublicKey(), privateKey), cert);
    }

    private String getCertContent() {
        if (StringUtils.hasText(this.certificateContent)) {
            return this.certificateContent;
        }

        Assert.notNull(this.certificateLocation, "certificateLocation is required");
        return ResourceUtils.readResourceAsString(certificateLocation);
    }

    private String getPrivateKeyContent() {
        if (StringUtils.hasText(this.privateKeyContent)) {
            return this.privateKeyContent;
        }

        Assert.notNull(this.privateKeyLocation, "keyLocation is required");
        return ResourceUtils.readResourceAsString(privateKeyLocation);
    }

    public void setPrivateKeyContent(String privateKeyContent) {
        this.privateKeyContent = privateKeyContent;
    }

    public void setCertificateLocation(String certificateLocation) {
        this.certificateLocation = certificateLocation;
    }

    public void setPrivateKeyLocation(String privateKeyLocation) {
        this.privateKeyLocation = privateKeyLocation;
    }

    public void setCertificateContent(String certificateContent) {
        this.certificateContent = certificateContent;
    }

    public void setPrivateKeyPassword(String privateKeyPassword) {
        this.privateKeyPassword = privateKeyPassword;
    }

}
