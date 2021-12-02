/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.slf4j.LoggerFactory;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import spring.turbo.lang.Immutable;

import java.io.Serializable;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Immutable
public final class Logger implements Serializable {

    private final org.slf4j.Logger log;
    private final LogLevel level;
    private final boolean enabled;

    public Logger(String loggerName, LogLevel level) {
        Asserts.notNull(level);
        Asserts.notNull(loggerName);

        this.level = level;

        if (level == LogLevel.STDOUT || level == LogLevel.STDERR) {
            this.log = null;
        } else {
            this.log = LoggerFactory.getLogger(loggerName);
        }

        this.enabled = this.checkEnabled();
    }

    public Logger(Class<?> loggerName, LogLevel level) {
        Asserts.notNull(level);
        Asserts.notNull(loggerName);

        this.level = level;

        if (level == LogLevel.STDOUT || level == LogLevel.STDERR) {
            this.log = null;
        } else {
            this.log = LoggerFactory.getLogger(loggerName);
        }

        this.enabled = this.checkEnabled();
    }

    public boolean isEnabled() {
        return enabled;
    }

    private boolean checkEnabled() {
        switch (level) {
            case STDOUT:
            case STDERR:
                return true;
            case TRACE:
                return log.isTraceEnabled();
            case DEBUG:
                return log.isDebugEnabled();
            case INFO:
                return log.isInfoEnabled();
            case WARN:
                return log.isWarnEnabled();
            case ERROR:
                return log.isErrorEnabled();
            case OFF:
            default:
                return false;
        }
    }

    public void log(String format, Object... args) {
        if (isEnabled()) {
            switch (level) {
                case TRACE:
                    trace(format, args);
                    break;
                case DEBUG:
                    debug(format, args);
                    break;
                case INFO:
                    info(format, args);
                    break;
                case WARN:
                    warn(format, args);
                    break;
                case ERROR:
                    error(format, args);
                    break;
                case STDOUT:
                    stdout(format, args);
                case STDERR:
                    stderr(format, args);
                default:
                    // nop
            }
        }
    }

    private void stdout(String format, Object... args) {
        System.out.println(StringFormatter.format(format, args));
    }

    private void stderr(String format, Object... args) {
        System.err.println(StringFormatter.format(format, args));
    }

    private void trace(String format, Object... args) {
        log.trace(format, args);
    }

    private void debug(String format, Object... args) {
        log.debug(format, args);
    }

    private void info(String format, Object... args) {
        log.info(format, args);
    }

    private void warn(String format, Object... args) {
        log.warn(format, args);
    }

    private void error(String format, Object... args) {
        log.warn(format, args);
    }

    @Nullable
    public org.slf4j.Logger getLog() {
        return log;
    }

    @NonNull
    public LogLevel getLevel() {
        return level;
    }

}
