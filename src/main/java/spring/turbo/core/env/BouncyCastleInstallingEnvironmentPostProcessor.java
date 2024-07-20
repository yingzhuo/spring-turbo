package spring.turbo.core.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.util.ClassUtils;

import java.security.Provider;
import java.security.Security;

/**
 * @author 应卓
 * @since 3.3.2
 */
public class BouncyCastleInstallingEnvironmentPostProcessor implements EnvironmentPostProcessor, Ordered {

    private static final String BOUNCY_CASTLE_PROVIDER_CLASS = "org.bouncycastle.jce.provider.BouncyCastleProvider";

    /**
     * {@inheritDoc}
     */
    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        try {
            var cls = ClassUtils.forName(BOUNCY_CASTLE_PROVIDER_CLASS, ClassUtils.getDefaultClassLoader());
            var ctor = cls.getConstructor();
            var provider = ctor.newInstance();
            Security.addProvider((Provider) provider);
        } catch (Throwable ignored) {
            // noop
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getOrder() {
        return LOWEST_PRECEDENCE;
    }

}
