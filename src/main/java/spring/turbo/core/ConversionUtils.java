/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.core.convert.ConversionService;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

/**
 * @author 应卓
 * @see ConversionService
 * @see SpringUtils
 * @since 1.0.0
 */
public final class ConversionUtils {

    /**
     * 私有构造方法
     */
    private ConversionUtils() {
        super();
    }

    public static boolean canConverter(Class<?> sourceType, Class<?> targetType) {
        Asserts.notNull(sourceType);
        Asserts.notNull(targetType);
        return SpringUtils.getConversionService().canConvert(sourceType, targetType);
    }

    @Nullable
    public static <T> T convert(Object source, Class<T> targetType) {
        Asserts.notNull(source);
        Asserts.notNull(targetType);
        return SpringUtils.getConversionService()
                .convert(source, targetType);
    }

    public static <T> T convertOrThrow(Object source, Class<T> targetType) {
        final T target = convert(source, targetType);
        if (target == null) {
            throw new IllegalArgumentException("cannot convert");
        }
        return target;
    }

}
