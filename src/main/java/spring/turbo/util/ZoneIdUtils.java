/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.time.DateTimeException;
import java.time.ZoneId;
import java.util.Objects;
import java.util.regex.Pattern;

import static spring.turbo.util.StringUtils.isBlank;

/**
 * {@link ZoneId} 相关工具
 *
 * @author 应卓
 *
 * @see ZoneId#getAvailableZoneIds()
 *
 * @since 1.1.3
 */
public final class ZoneIdUtils {

    // SYSTEM
    public static final ZoneId SYSTEM_DEFAULT = ZoneId.systemDefault();
    // UTC
    public static final String EUROPE_LONDON_VALUE = "Europe/London";
    public static final ZoneId EUROPE_LONDON = ZoneId.of(EUROPE_LONDON_VALUE);
    // UTC+8
    public static final String ASIA_SHANGHAI_VALUE = "Asia/Shanghai";
    public static final ZoneId ASIA_SHANGHAI = ZoneId.of(ASIA_SHANGHAI_VALUE);
    // UTC+9
    public static final String ASIA_TOKYO_VALUE = "Asia/Tokyo";
    public static final ZoneId ASIA_TOKYO = ZoneId.of(ASIA_TOKYO_VALUE);

    /**
     * 私有构造方法
     */
    private ZoneIdUtils() {
        super();
    }

    public static ZoneId toZoneIdOrDefault(@Nullable String name) {
        var id = toZoneIdOrDefault(name, SYSTEM_DEFAULT);
        return Objects.requireNonNull(id);
    }

    @Nullable
    public static ZoneId toZoneIdOrDefault(@Nullable String name, @Nullable ZoneId defaultIfNullOrError) {

        if (isBlank(name)) {
            return defaultIfNullOrError;
        }

        try {
            return ZoneId.of(name);
        } catch (DateTimeException e) {
            var matcher = Pattern.compile("^([a-zA-Z]+[+-]\\d+)\\.0+$").matcher(name);
            if (matcher.matches()) {
                name = matcher.replaceAll("$1");
                try {
                    return ZoneId.of(name);
                } catch (Throwable ex) {
                    return defaultIfNullOrError;
                }
            } else {
                return defaultIfNullOrError;
            }
        }
    }

}
