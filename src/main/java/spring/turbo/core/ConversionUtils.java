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
 * {@link ConversionService} 相关工具
 *
 * @author 应卓
 * @see SpringUtils
 * @see ConversionService
 * @since 1.0.0
 */
public final class ConversionUtils {

    /**
     * 私有构造方法
     */
    private ConversionUtils() {
        super();
    }

    /**
     * 判断是否可以完成转换
     *
     * @param sourceType 源类型
     * @param targetType 目标类型
     * @return 判断结果
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    public static boolean canConverter(Class<?> sourceType, Class<?> targetType) {
        Asserts.notNull(sourceType);
        Asserts.notNull(targetType);
        return SpringUtils.getConversionService().canConvert(sourceType, targetType);
    }

    /**
     * 转换
     *
     * @param source     源对象实例
     * @param targetType 目标类型
     * @param <T>        目标类型泛型
     * @return 转换结果或null
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    @Nullable
    public static <T> T convert(Object source, Class<T> targetType) {
        Asserts.notNull(source);
        Asserts.notNull(targetType);
        return SpringUtils.getConversionService().convert(source, targetType);
    }

    /**
     * 转换
     *
     * @param source     源对象实例
     * @param targetType 目标类型
     * @param <T>        目标类型泛型
     * @return 转换结果或null
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @throws IllegalArgumentException      无法转换
     */
    public static <T> T convertOrThrow(Object source, Class<T> targetType) {
        final T target = convert(source, targetType);
        Asserts.notNull(target, "cannot convert");
        return target;
    }

}
