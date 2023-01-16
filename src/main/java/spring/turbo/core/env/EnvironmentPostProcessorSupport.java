/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.logging.DeferredLog;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.core.Ordered;
import org.springframework.core.env.ConfigurableEnvironment;

import java.io.File;

import static spring.turbo.util.CollectionUtils.size;
import static spring.turbo.util.StringFormatter.format;

/**
 * @author 应卓
 * @since 2.0.8
 */
public abstract class EnvironmentPostProcessorSupport implements EnvironmentPostProcessor, Ordered {

    private final DeferredLog log = new DeferredLog();
    private int order = HIGHEST_PRECEDENCE;

    @Override
    public final void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

        // 初始化日志
        application.addInitializers(ctx -> log.replayTo(getClass()));

        execute(environment, application);
    }

    protected abstract void execute(ConfigurableEnvironment environment, SpringApplication application);

    protected final File getHomeDir(SpringApplication application) {
        var sourceClasses = application.getAllSources()
                .stream()
                .filter(o -> o instanceof Class<?>)
                .map(o -> (Class<?>) o)
                .toList();

        if (size(sourceClasses) == 1) {
            return new ApplicationHome(sourceClasses.get(0)).getDir();
        } else {
            return new ApplicationHome().getDir();
        }
    }

    protected final void trace(String format, Object... args) {
        if (log.isTraceEnabled()) {
            var msg = format(format, args);
            log.trace(msg);
        }
    }

    protected final void debug(String format, Object... args) {
        if (log.isDebugEnabled()) {
            var msg = format(format, args);
            log.debug(msg);
        }
    }

    protected final void info(String format, Object... args) {
        if (log.isInfoEnabled()) {
            var msg = format(format, args);
            log.info(msg);
        }
    }

    protected final void warn(String format, Object... args) {
        if (log.isWarnEnabled()) {
            var msg = format(format, args);
            log.warn(msg);
        }
    }

    protected final void error(String format, Object... args) {
        if (log.isErrorEnabled()) {
            var msg = format(format, args);
            log.error(msg);
        }
    }

    protected final void fatal(String format, Object... args) {
        if (log.isFatalEnabled()) {
            var msg = format(format, args);
            log.fatal(msg);
        }
    }

    @Override
    public final int getOrder() {
        return this.order;
    }

    public final void setOrder(int order) {
        this.order = order;
    }

}
