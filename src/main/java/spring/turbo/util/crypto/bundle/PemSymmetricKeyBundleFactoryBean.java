package spring.turbo.util.crypto.bundle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.lang.NonNull;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import spring.turbo.core.ResourceUtils;
import spring.turbo.util.crypto.pem.PemUtils;

/**
 * {@link SymmetricKeyBundle} 配置用 {@link FactoryBean} <br>
 * 用PEM格式文件配置
 *
 * @author 应卓
 * @see SymmetricKeyBundle
 * @see PemUtils
 * @since 3.3.1
 */
public class PemSymmetricKeyBundleFactoryBean implements FactoryBean<SymmetricKeyBundle>, InitializingBean {

    private String keyLocation;
    private String keyContent;
    private String keyPassword;
    private SymmetricKeyBundle bundle;

    /**
     * {@inheritDoc}
     */
    @NonNull
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
    public void afterPropertiesSet() {
        var key = PemUtils.readPkcs8Key(getKeyContent(), this.keyPassword);
        this.bundle = new SymmetricKeyBundleImpl(key);
    }

    private String getKeyContent() {
        if (StringUtils.hasText(this.keyContent)) {
            return this.keyContent;
        }

        Assert.notNull(this.keyLocation, "keyLocation is required");
        return ResourceUtils.readResourceAsString(keyLocation);
    }

    public void setKeyContent(String keyContent) {
        this.keyContent = keyContent;
    }

    public void setKeyLocation(String keyLocation) {
        this.keyLocation = keyLocation;
    }

    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

}
