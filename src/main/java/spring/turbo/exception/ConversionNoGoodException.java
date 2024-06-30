/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import org.springframework.context.MessageSourceResolvable;
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
public class ConversionNoGoodException extends AbstractMessageResolvableException implements MessageSourceResolvable {

    /**
     * 构造方法
     *
     * @param code      解析错误信息用code
     * @param arguments 解析错误信息用参数
     * @see MessageSourceResolvable
     */
    public ConversionNoGoodException(@Nullable String code, @Nullable Object... arguments) {
        super(code, arguments);
    }

    /**
     * 构造方法
     *
     * @param defaultMessage 默认错误信息
     * @see MessageSourceResolvable
     */
    public ConversionNoGoodException(@Nullable String defaultMessage) {
        super(defaultMessage);
    }

    /**
     * 构造方法
     *
     * @param code           解析错误信息用code
     * @param arguments      解析错误信息用参数
     * @param defaultMessage 默认错误信息
     * @see MessageSourceResolvable
     */
    public ConversionNoGoodException(@Nullable String code, @Nullable Object[] arguments, @Nullable String defaultMessage) {
        super(code, arguments, defaultMessage);
    }

}
