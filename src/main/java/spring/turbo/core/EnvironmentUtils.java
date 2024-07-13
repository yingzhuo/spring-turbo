package spring.turbo.core;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Objects;

/**
 * {@link org.springframework.core.env.Environment}相关工具
 *
 * @author 应卓
 * @see SpringUtils
 * @since 1.0.0
 */
public final class EnvironmentUtils {

    /**
     * 私有构造方法
     */
    private EnvironmentUtils() {
    }

    /**
     * 解析placeholder
     *
     * @param text 解析的文本
     * @return 解析结果
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    public static String resolvePlaceholders(String text) {
        Assert.hasText(text, "text is required");
        return SpringUtils.getEnvironment().resolvePlaceholders(text);
    }

    /**
     * 解析placeholder
     *
     * @param text 解析的文本
     * @return 解析结果
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @throws IllegalArgumentException      任意一个解析项无法满足
     */
    public static String resolveRequiredPlaceholders(String text) {
        Assert.hasText(text, "text is required");
        return SpringUtils.getEnvironment().resolveRequiredPlaceholders(text);
    }

    /**
     * 获取PropertyValue
     *
     * @param propertyName property名
     * @return 结果或{@code null}
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    @Nullable
    public static String getPropertyValue(String propertyName) {
        return getPropertyValue(propertyName, String.class);
    }

    /**
     * 获取PropertyValue
     *
     * @param propertyName property名
     * @return 结果
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @throws IllegalArgumentException      无法找到property名相对的值
     */
    public static String getRequiredPropertyValue(String propertyName) {
        var result = getPropertyValue(propertyName);
        return Objects.requireNonNull(result);
    }

    /**
     * 获取PropertyValue
     *
     * @param propertyName property名
     * @param targetType   目标类型
     * @param <T>          目标类型泛型
     * @return 结果或{@code null}
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    @Nullable
    public static <T> T getPropertyValue(String propertyName, Class<T> targetType) {
        return getPropertyValue(propertyName, targetType, null);
    }

    /**
     * 获取PropertyValue
     *
     * @param propertyName property名
     * @param targetType   目标类型
     * @param <T>          目标类型泛型
     * @return 结果
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @throws IllegalArgumentException      无法找到property名相对的值
     */
    public static <T> T getRequiredPropertyValue(String propertyName, Class<T> targetType) {
        T result = getPropertyValue(propertyName, targetType);
        return Objects.requireNonNull(result);
    }

    /**
     * 获取PropertyValue
     *
     * @param propertyName  property名
     * @param targetType    目标类型
     * @param defaultIfNull 默认值
     * @param <T>           目标类型泛型
     * @return 结果或默认值 (可能为null)
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    @Nullable
    public static <T> T getPropertyValue(String propertyName, Class<T> targetType, @Nullable T defaultIfNull) {
        Assert.hasText(propertyName, "propertyName is required");
        Assert.notNull(targetType, "targetType is required");

        T result = SpringUtils.getEnvironment().getProperty(propertyName, targetType);
        return result != null ? result : defaultIfNull;
    }

    /**
     * 获取PropertyValue
     *
     * @param propertyName  property名
     * @param targetType    目标类型
     * @param defaultIfNull 默认值
     * @param <T>           目标类型泛型
     * @return 结果或默认值 (可能为null)
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @throws IllegalArgumentException      无法找到property名相对的值
     */
    public static <T> T getRequiredPropertyValue(String propertyName, Class<T> targetType, @Nullable T defaultIfNull) {
        T result = getPropertyValue(propertyName, targetType, defaultIfNull);
        return Objects.requireNonNull(result);
    }

}
