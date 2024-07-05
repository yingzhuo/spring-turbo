package spring.turbo.databinding;

import org.springframework.format.Formatter;
import org.springframework.format.Parser;
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
public abstract class AbstractParser<T> implements Parser<T> {

    /**
     * {@inheritDoc}
     */
    @Override
    public T parse(String text, Locale locale) {
        try {
            return doParse(text, locale);
        } catch (RuntimeException e) {
            throw InternalConverterUtils.transform(e);
        }
    }

    /**
     * 转换数据
     *
     * @param text   源数据
     * @param locale locale
     * @return 转换结果
     * @throws DataBindingException 数据转换失败或数据非法
     */
    protected abstract T doParse(String text, Locale locale) throws DataBindingException;

}
