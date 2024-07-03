/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.databinding;

import org.springframework.format.Formatter;
import spring.turbo.exception.DataBindingException;

import java.util.Locale;

/**
 * {@link Formatter} 辅助工具 <br>
 * 本类型将尝试转换{@link RuntimeException} 转换成 {@link org.springframework.context.MessageSourceResolvable}。
 *
 * @param <T> 目标类型泛型
 * @author 应卓
 * @since 3.3.1
 */
public abstract class AbstractFormatter<T> implements Formatter<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public final T parse(String text, Locale locale) {
        try {
            return doParse(text, locale);
        } catch (RuntimeException e) {
            throw InternalConverterUtils.transform(e);
        }
    }

    protected abstract T doParse(String text, Locale locale) throws DataBindingException;

}
