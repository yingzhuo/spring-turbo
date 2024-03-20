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

import java.time.ZoneId;

import static spring.turbo.util.StringUtils.isBlank;

/**
 * @author 应卓
 *
 * @see ZoneId#getAvailableZoneIds()
 *
 * @since 1.1.3
 */
public final class ZoneIdPool {

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
    private ZoneIdPool() {
        super();
    }

    public static ZoneId toZoneIdOrDefault(@Nullable String name) {
        return toZoneIdOrDefault(name, SYSTEM_DEFAULT);
    }

    public static ZoneId toZoneIdOrDefault(@Nullable String name, ZoneId defaultIfNullOrError) {
        Asserts.notNull(defaultIfNullOrError);

        if (isBlank(name)) {
            return defaultIfNullOrError;
        }

        return ZoneId.of(name);
    }

}
