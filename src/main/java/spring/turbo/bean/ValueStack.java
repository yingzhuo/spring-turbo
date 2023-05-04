/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * 值栈，建议配合 {@code SpEL} 使用
 *
 * @author 应卓
 *
 * @see org.springframework.beans.factory.annotation.Value
 *
 * @since 2.2.4
 */
public sealed interface ValueStack permits ValueStackImpl {

    @Nullable
    public String findString(@Nullable String environmentName, @Nullable String resourceLocation,
            @Nullable String defaultValue);

    @Nullable
    public default String findString(@Nullable String environmentName, @Nullable String resourceLocation) {
        return findString(environmentName, resourceLocation, null);
    }

    public default String findRequiredString(@Nullable String environmentName, @Nullable String resourceLocation,
            @Nullable String defaultValue) {
        var value = findString(environmentName, resourceLocation, defaultValue);
        return Objects.requireNonNull(value);
    }

    // -----------------------------------------------------------------------------------------------------------------

    @Nullable
    public <T> T findObject(@NonNull Class<T> valueType, @Nullable String environmentName,
            @Nullable String resourceLocation, @Nullable String defaultValue);

    @Nullable
    public default <T> T findObject(@NonNull Class<T> valueType, @Nullable String environmentName,
            @Nullable String resourceLocation) {
        return findObject(valueType, environmentName, resourceLocation, null);
    }

    public default <T> T findRequiredObject(@NonNull Class<T> valueType, @Nullable String environmentName,
            @Nullable String resourceLocation, @Nullable String defaultValue) {
        var value = findObject(valueType, environmentName, resourceLocation, defaultValue);
        return Objects.requireNonNull(value);
    }

}
