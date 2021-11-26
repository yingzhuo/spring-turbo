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

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 应卓
 * @since 1.0.0
 */
public class Logger implements Serializable {

    private final org.slf4j.Logger log;
    private final LogLevel level;

    private Logger(String loggerName, LogLevel level) {
        this.level = Objects.requireNonNull(level);
        this.log = LoggerFactory.getLogger(Objects.requireNonNull(loggerName));
    }

    public static Builder builder() {
        return new Builder();
    }

    public boolean isEnabled() {
        switch (level) {
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
            case STDOUT:
                return true;
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
                    stdout(format, (Objects[]) args);
                default:
                    // nop
            }
        }
    }

    private void stdout(String format, Objects... args) {
        System.out.println(StringFormatter.format(format, args));
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

    // ----------------------------------------------------------------------------------------------------------------

    public final static class Builder {

        private LogLevel level = LogLevel.DEBUG;
        private String loggerName = Logger.class.getName();

        private Builder() {
        }

        public Builder level(LogLevel level) {
            this.level = level;
            return this;
        }

        public Builder name(String loggerName) {
            this.loggerName = loggerName;
            return this;
        }

        public Builder name(Class<?> loggerName) {
            this.loggerName = loggerName.getName();
            return this;
        }

        public Logger build() {
            return new Logger(loggerName, level);
        }
    }
}
