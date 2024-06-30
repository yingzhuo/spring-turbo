/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import org.springframework.lang.Nullable;

/**
 * 数据转换异常
 *
 * @author 应卓
 * @see org.springframework.core.convert.converter.Converter
 * @see org.springframework.core.convert.converter.GenericConverter
 * @see org.springframework.core.convert.ConversionService
 * @since 3.3.2
 */
public class BadConversionException extends AbstractMessageResolvableException {

    public static BadConversionException of(String defaultMsg) {
        throw new BadConversionException(defaultMsg);
    }

    public static BadConversionException of(String code, @Nullable Object[] arguments) {
        throw new BadConversionException(code, arguments);
    }

    public static BadConversionException of(@Nullable String code, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        throw new BadConversionException(code, arguments, defaultMessage);
    }

    public BadConversionException(String code, @Nullable Object[] arguments) {
        super(code, arguments);
    }

    public BadConversionException(String defaultMessage) {
        super(defaultMessage);
    }

    public BadConversionException(@Nullable String code, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        super(code, arguments, defaultMessage);
    }

}
