package spring.turbo.util.crypto.bundle;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ResourceLoaderAware;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.Assert;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import spring.turbo.util.crypto.pem.PemReadingUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * {@link SymmetricKeyBundle} 配置用 {@link FactoryBean} <br>
 * 用PEM格式文件配置
 *
 * @author 应卓
 * @see SymmetricKeyBundle
 * @since 3.3.1
 */
public class PemSymmetricKeyBundleFactoryBean
        implements FactoryBean<SymmetricKeyBundle>, ResourceLoaderAware, InitializingBean {

    private ResourceLoader resourceLoader = new DefaultResourceLoader(ClassUtils.getDefaultClassLoader());
    private String keyLocation;
    private String keyContent;
    private String keyPassword;
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
        var key = PemReadingUtils.readPkcs8Key(getKeyContent(), this.keyPassword);
        this.bundle = new SymmetricKeyBundleImpl(key);
    }

    private String getKeyContent() throws IOException {
        if (StringUtils.hasText(this.keyContent)) {
            return this.keyContent;
        }

        Assert.notNull(this.keyLocation, "keyLocation is required");
        return resourceLoader.getResource(this.keyLocation).getContentAsString(StandardCharsets.UTF_8);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    public void setKeyLocation(String keyLocation) {
        this.keyLocation = keyLocation;
    }

    public void setKeyContent(String keyContent) {
        this.keyContent = keyContent;
    }

    public void setKeyPassword(String keyPassword) {
        this.keyPassword = keyPassword;
    }

}
