package spring.turbo.util.crypto.bundle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import spring.turbo.core.ResourceUtils;

import java.io.IOException;
import java.security.KeyPair;

import static java.nio.charset.StandardCharsets.UTF_8;
import static spring.turbo.util.crypto.pem.PemReadingUtils.readPkcs8PrivateKey;
import static spring.turbo.util.crypto.pem.PemReadingUtils.readX509Certificate;
import static spring.turbo.util.crypto.pem.PemStringUtils.trimContent;

/**
 * {@link AsymmetricKeyBundle} 配置用 {@link FactoryBean} <br>
 * 用PEM格式文件配置
 *
 * @see AsymmetricKeyBundle
 * @since 应卓
 * @since 3.3.1
 */
public class PemAsymmetricKeyBundleFactoryBean
        implements FactoryBean<AsymmetricKeyBundle>, InitializingBean {

    private String certificateLocation;
    private String certificateContent = "";
    private String keyLocation;
    private String keyContent = "";
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
    public void afterPropertiesSet() throws IOException {
        var cert = readX509Certificate(getCertContent());
        var privateKey = readPkcs8PrivateKey(getKeyContent(), this.keyPassword);
        this.bundle = new AsymmetricKeyBundleImpl(new KeyPair(cert.getPublicKey(), privateKey), cert);
    }

    private String getCertContent() throws IOException {
        if (StringUtils.hasText(this.certificateContent)) {
            return this.certificateContent;
        }

        Assert.notNull(this.certificateLocation, "certificateLocation is required");
        return ResourceUtils.load(certificateLocation).getContentAsString(UTF_8);
    }

    private String getKeyContent() throws IOException {
        if (StringUtils.hasText(this.keyContent)) {
            return this.keyContent;
        }

        Assert.notNull(this.keyLocation, "keyLocation is required");
        return trimContent(ResourceUtils.load(keyLocation).getContentAsString(UTF_8));
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

    public void setCertificateContent(String certificateContent) {
        this.certificateContent = certificateContent;
    }

    public void setKeyContent(String keyContent) {
        this.keyContent = keyContent;
    }

}
