package spring.turbo.core.env;

import org.apache.commons.logging.Log;
import org.springframework.boot.ConfigurableBootstrapContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLogFactory;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;
import spring.turbo.core.SpringApplicationUtils;

import java.io.File;

import static spring.turbo.util.StringFormatter.format;

/**
 * @author 应卓
 * @since 2.0.8
 */
public abstract class EnvironmentPostProcessorSupport implements EnvironmentPostProcessor, Ordered {

    private final Log log;
    private final ConfigurableBootstrapContext bootstrapContext;
    private int order = 0;

    public EnvironmentPostProcessorSupport(
            DeferredLogFactory logFactory,
            ConfigurableBootstrapContext bootstrapContext) {
        this.log = logFactory.getLog(getClass());
        this.bootstrapContext = bootstrapContext;
    }

    @Override
    public final void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
        execute(environment, application);
    }

    protected abstract void execute(ConfigurableEnvironment environment, SpringApplication application);

    protected final File getHomeDir(SpringApplication application) {
        return SpringApplicationUtils.getHomeDir(application);
    }

    protected final String getHomePath(SpringApplication application) {
        return SpringApplicationUtils.getHomeDirAsString(application);
    }

    protected final void trace(String format, Object... args) {
        if (log.isTraceEnabled()) {
            log.trace(format(format, args));
        }
    }

    protected final void debug(String format, Object... args) {
        if (log.isDebugEnabled()) {
            log.debug(format(format, args));
        }
    }

    protected final void info(String format, Object... args) {
        if (log.isInfoEnabled()) {
            log.info(format(format, args));
        }
    }

    protected final void warn(String format, Object... args) {
        if (log.isWarnEnabled()) {
            log.warn(format(format, args));
        }
    }

    protected final void error(String format, Object... args) {
        if (log.isErrorEnabled()) {
            log.error(format(format, args));
        }
    }

    protected final void fatal(String format, Object... args) {
        if (log.isFatalEnabled()) {
            log.fatal(format(format, args));
        }
    }

    @Override
    public final int getOrder() {
        return this.order;
    }

    public final void setOrder(int order) {
        this.order = order;
    }

    public final Log getLog() {
        return this.log;
    }

    public final ConfigurableBootstrapContext getBootstrapContext() {
        return this.bootstrapContext;
    }

}
