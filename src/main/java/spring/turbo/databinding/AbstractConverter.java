/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.databinding;

import org.springframework.context.MessageSourceResolvable;
import org.springframework.core.NestedExceptionUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.util.StringUtils;
import spring.turbo.exception.ConversionFailedException;

/**
 * @param <S> 源类型泛型
 * @param <T> 目标类型
 * @author 应卓
 * @since 3.2.2
 */
public abstract class AbstractConverter<S, T> implements Converter<S, T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public final T convert(S source) {
        try {
            return doConvert(source);
        } catch (Exception e) {
            if (e instanceof MessageSourceResolvable) {
                throw e;
            }
            var msg = e.getMessage();
            if (!StringUtils.hasText(msg)) {
                var rootEx = NestedExceptionUtils.getRootCause(e);
                if (rootEx != null) {
                    msg = rootEx.getMessage();
                }
            }
            if (StringUtils.hasText(msg)) {
                throw new ConversionFailedException(msg);
            }
            throw e;
        }
    }

    protected abstract T doConvert(S source);

}
