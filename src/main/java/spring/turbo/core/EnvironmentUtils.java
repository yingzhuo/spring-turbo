/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

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
        super();
    }

    /**
     * 解析placeholder
     *
     * @param text 解析的文本
     * @return 解析结果
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    public static String resolvePlaceholders(String text) {
        Asserts.notNull(text);
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
        Asserts.notNull(text);
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
        final String result = getPropertyValue(propertyName);
        Asserts.notNull(result);
        return result;
    }

    /**
     * 获取PropertyValue
     *
     * @param propertyName property名
     * @param targetType   目标类型
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
     * @return 结果
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @throws IllegalArgumentException      无法找到property名相对的值
     */
    public static <T> T getRequiredPropertyValue(String propertyName, Class<T> targetType) {
        final T result = getPropertyValue(propertyName, targetType);
        Asserts.notNull(result);
        return result;
    }

    /**
     * 获取PropertyValue
     *
     * @param propertyName  property名
     * @param targetType    目标类型
     * @param defaultIfNull 默认值
     * @return 结果或默认值 (可能为null)
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     */
    @Nullable
    public static <T> T getPropertyValue(String propertyName, Class<T> targetType, @Nullable T defaultIfNull) {
        Asserts.notNull(propertyName);
        Asserts.notNull(targetType);

        T result = SpringUtils.getEnvironment().getProperty(propertyName, targetType);
        return result != null ? result : defaultIfNull;
    }

    /**
     * 获取PropertyValue
     *
     * @param propertyName  property名
     * @param targetType    目标类型
     * @param defaultIfNull 默认值
     * @return 结果或默认值 (可能为null)
     * @throws UnsupportedOperationException 无法定位{@code ApplicationContext}实例
     * @throws IllegalArgumentException      无法找到property名相对的值
     */
    public static <T> T getRequiredPropertyValue(String propertyName, Class<T> targetType, @Nullable T defaultIfNull) {
        final T result = getPropertyValue(propertyName, targetType, defaultIfNull);
        Asserts.notNull(result);
        return result;
    }

}
