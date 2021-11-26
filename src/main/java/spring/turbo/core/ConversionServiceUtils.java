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
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.lang.Nullable;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class ConversionServiceUtils {

    private ConversionServiceUtils() {
        super();
    }

    public static boolean canConverter(@Nullable Class<?> sourceType, Class<?> targetType) {
        return SpringApplicationAware.AC.getBean(ConversionService.class).canConvert(sourceType, targetType);
    }

    public static boolean canConvert(@Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        return SpringApplicationAware.AC.getBean(ConversionService.class).canConvert(sourceType, targetType);
    }

    @Nullable
    public static <T> T convert(@Nullable Object source, Class<T> targetType) {
        return SpringApplicationAware.AC.getBean(ConversionService.class).convert(source, targetType);
    }

    @Nullable
    public static Object convert(@Nullable Object source, @Nullable TypeDescriptor sourceType, TypeDescriptor targetType) {
        return SpringApplicationAware.AC.getBean(ConversionService.class).convert(source, sourceType, targetType);
    }

}
