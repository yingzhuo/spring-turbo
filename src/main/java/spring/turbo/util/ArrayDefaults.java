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

import static spring.turbo.util.ArrayUtils.isNullOrEmpty;

/**
 * 数组默认值相关工具
 *
 * @author 应卓
 * @see ArrayUtils
 * @since 2.1.0
 */
public final class ArrayDefaults {

    /**
     * 私有构造方法
     */
    private ArrayDefaults() {
        super();
    }

    public static <T> T[] nullToDefault(@Nullable T[] array, T[] defaultArray) {
        return array == null ? defaultArray : array;
    }

    public static <T> T[] emptyToDefault(@Nullable T[] array, T[] defaultArray) {
        return isNullOrEmpty(array) ? defaultArray : array;
    }

}
