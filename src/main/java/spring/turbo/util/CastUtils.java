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

/**
 * 类型转换工具
 *
 * @author 应卓
 * @since 3.2.5
 */
@SuppressWarnings("unchecked")
public final class CastUtils {

    /**
     * 私有构造方法
     */
    private CastUtils() {
        super();
    }

    @Nullable
    public static <T> T castNullable(@Nullable Object object) {
        return (T) object;
    }

    public static <T> T castNonNull(Object object) {
        Asserts.notNull(object);
        return (T) object;
    }

}
