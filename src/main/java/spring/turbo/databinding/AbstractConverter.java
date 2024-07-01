/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.databinding;

import org.springframework.core.convert.converter.Converter;

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
        } catch (RuntimeException e) {
            throw ConverterInternalUtils.transform(e);
        }
    }

    protected abstract T doConvert(S source);

}
