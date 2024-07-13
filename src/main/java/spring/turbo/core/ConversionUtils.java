package spring.turbo.core;

import org.springframework.core.convert.ConversionService;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

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
        Assert.notNull(sourceType, "sourceType is required");
        Assert.notNull(targetType, "targetType is required");
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
        Assert.notNull(source, "source is required");
        Assert.notNull(targetType, "targetType is required");
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
        Assert.notNull(target, "cannot convert");
        return target;
    }

}
