/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.*;

/**
 * 枚举类型相关工具
 *
 * @author 应卓
 * @since 1.0.2
 */
public final class EnumUtils {

    /**
     * 私有构造方法
     */
    private EnumUtils() {
        super();
    }

    /**
     * 获取枚举值
     *
     * @param enumClass 枚举类型
     * @param enumName  枚举字符串
     * @param <E>       枚举类型泛型
     * @return 枚举值
     */
    public static <E extends Enum<E>> E getEnum(final Class<E> enumClass, final String enumName) {
        return getEnum(enumClass, enumName, null);
    }

    /**
     * 获取枚举值
     *
     * @param enumClass   枚举类型
     * @param enumName    枚举字符串
     * @param defaultEnum 默认值
     * @param <E>         枚举类型泛型
     * @return 枚举值
     */
    public static <E extends Enum<E>> E getEnum(final Class<E> enumClass, final String enumName, final E defaultEnum) {
        if (enumName == null) {
            return defaultEnum;
        }
        try {
            return Enum.valueOf(enumClass, enumName);
        } catch (final IllegalArgumentException ex) {
            return defaultEnum;
        }
    }

    /**
     * 获取枚举值 (忽略大小写)
     *
     * @param enumClass 枚举类型
     * @param enumName  枚举字符串
     * @param <E>       枚举类型泛型
     * @return 枚举值
     */
    public static <E extends Enum<E>> E getEnumIgnoreCase(final Class<E> enumClass, final String enumName) {
        return getEnumIgnoreCase(enumClass, enumName, null);
    }

    /**
     * 获取枚举值 (忽略大小写)
     *
     * @param enumClass   枚举类型
     * @param enumName    枚举字符串
     * @param defaultEnum 默认值
     * @param <E>         枚举类型泛型
     * @return 枚举值
     */
    public static <E extends Enum<E>> E getEnumIgnoreCase(final Class<E> enumClass, final String enumName, final E defaultEnum) {
        if (enumName == null || !enumClass.isEnum()) {
            return defaultEnum;
        }
        for (final E each : enumClass.getEnumConstants()) {
            if (each.name().equalsIgnoreCase(enumName)) {
                return each;
            }
        }
        return defaultEnum;
    }

    /**
     * 获取所有的枚举值
     *
     * @param enumClass 枚举类型
     * @param <E>       枚举类型泛型
     * @return 所有的枚举值
     */
    public static <E extends Enum<E>> List<E> getEnumList(final Class<E> enumClass) {
        return new ArrayList<>(Arrays.asList(enumClass.getEnumConstants()));
    }

    /**
     * 获取所有的枚举值
     *
     * @param enumClass 枚举类型
     * @param <E>       枚举类型泛型
     * @return 所有的枚举值
     */
    public static <E extends Enum<E>> Map<String, E> getEnumMap(final Class<E> enumClass) {
        final Map<String, E> map = new LinkedHashMap<>();
        for (final E e : enumClass.getEnumConstants()) {
            map.put(e.name(), e);
        }
        return map;
    }

    /**
     * 判断字符串是否为合法的枚举值
     *
     * @param enumClass 枚举类型
     * @param enumName  枚举字符串
     * @param <E>       枚举类泛型
     * @return 合法时返回{@code true}，否则返回 {@code false}
     */
    public static <E extends Enum<E>> boolean isValidEnum(final Class<E> enumClass, final String enumName) {
        return getEnum(enumClass, enumName) != null;
    }

    /**
     * 判断字符串是否为合法的枚举值 (忽略大小写)
     *
     * @param enumClass 枚举类型
     * @param enumName  枚举字符串
     * @param <E>       枚举类泛型
     * @return 合法时返回{@code true}，否则返回 {@code false}
     */
    public static <E extends Enum<E>> boolean isValidEnumIgnoreCase(final Class<E> enumClass, final String enumName) {
        return getEnumIgnoreCase(enumClass, enumName) != null;
    }

}
