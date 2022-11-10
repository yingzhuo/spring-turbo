/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.boot.ApplicationArguments;
import org.springframework.core.env.Environment;
import spring.turbo.util.Asserts;

/**
 * @author 应卓
 * @see DebugLogUtils
 * @since 1.2.3
 */
@Deprecated
public final class TraceLogUtils {

    /**
     * 私有构造方法
     */
    private TraceLogUtils() {
        super();
    }

    public static boolean isTraceLogEnabled() {
        return isTraceLogEnabled(SpringUtils.getEnvironment(), SpringUtils.getApplicationArguments());
    }

    public static boolean isTraceLogEnabled(Environment environment, ApplicationArguments applicationArguments) {
        Asserts.notNull(environment);
        Asserts.notNull(applicationArguments);
        return environment.getProperty("trace", Boolean.class, Boolean.FALSE) || applicationArguments.containsOption("trace");
    }

}
