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
import org.springframework.lang.Nullable;

import java.io.Serializable;

/**
 * {@link Logger} 的包装工具
 *
 * @author 应卓
 * @see org.slf4j.Logger
 * @see LogLevel
 * @since 1.0.0
 */
public final class Logger implements Serializable {

    @Nullable
    private final org.slf4j.Logger log;

    private final boolean enabled;

    private final LogLevel level;

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
        return switch (level) {
            case STDOUT, STDERR -> true;
            case TRACE -> log != null && log.isTraceEnabled();
            case DEBUG -> log != null && log.isDebugEnabled();
            case INFO -> log != null && log.isInfoEnabled();
            case WARN -> log != null && log.isWarnEnabled();
            case ERROR -> log != null && log.isErrorEnabled();
            default -> false;
        };
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
        if (log != null) {
            log.trace(format, args);
        }
    }

    private void debug(String format, Object... args) {
        if (log != null) {
            log.debug(format, args);
        }
    }

    private void info(String format, Object... args) {
        if (log != null) {
            log.info(format, args);
        }
    }

    private void warn(String format, Object... args) {
        if (log != null) {
            log.warn(format, args);
        }
    }

    private void error(String format, Object... args) {
        if (log != null) {
            log.warn(format, args);
        }
    }

}
