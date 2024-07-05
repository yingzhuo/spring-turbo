package spring.turbo.databinding;

import org.springframework.core.convert.converter.Converter;
import spring.turbo.exception.DataBindingException;

/**
 * {@link Converter} 辅助工具 <br>
 * 本类型将尝试转换{@link RuntimeException} 转换成 {@link org.springframework.context.MessageSourceResolvable}。
 *
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
            throw InternalConverterUtils.transform(e);
        }
    }

    /**
     * 转换数据
     *
     * @param source 源数据
     * @return 转换结果
     * @throws DataBindingException 数据转换失败或数据非法
     */
    protected abstract T doConvert(S source) throws DataBindingException;

}
