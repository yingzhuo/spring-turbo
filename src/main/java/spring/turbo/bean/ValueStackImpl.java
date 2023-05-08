/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.core.convert.ConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import spring.turbo.io.ResourceUtils;

import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * @param environment
 *            {@link Environment} 实例
 * @param resourceLoader
 *            {@link ResourceLoader} 实例
 * @param conversionService
 *            {@link ConversionService} 实例
 *
 * @author 应卓
 *
 * @since 2.2.4
 */
public record ValueStackImpl(Environment environment, ResourceLoader resourceLoader,
        ConversionService conversionService) implements ValueStack {

    @Nullable
    @Override
    public String findString(@Nullable String environmentName, @Nullable String resourceLocation,
            @Nullable String defaultValue) {
        var value = (String) null;

        if (environmentName != null) {
            value = this.environment.getProperty(environmentName);
        }

        if (value == null && resourceLocation != null) {
            var resource = resourceLoader.getResource(resourceLocation);
            value = ResourceUtils.readText(resource, UTF_8);
        }

        if (value == null) {
            value = defaultValue;
        }

        return value;
    }

    @Nullable
    @Override
    @SuppressWarnings("unchecked")
    public <T> T findObject(@NonNull Class<T> valueType, @Nullable String environmentName,
            @Nullable String resourceLocation, @Nullable String defaultValue) {

        var stringValue = findString(environmentName, resourceLocation, defaultValue);

        if (valueType == String.class) {
            return (T) stringValue;
        }

        if (stringValue == null) {
            return null;
        }

        if (conversionService.canConvert(String.class, valueType)) {
            return conversionService.convert(stringValue, valueType);
        } else {
            return null;
        }
    }

}
