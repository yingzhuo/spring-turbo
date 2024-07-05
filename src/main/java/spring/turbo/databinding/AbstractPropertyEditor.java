package spring.turbo.databinding;

import spring.turbo.exception.DataBindingException;

import java.beans.PropertyEditor;
import java.beans.PropertyEditorSupport;

/**
 * {@link PropertyEditor} 辅助工具 <br>
 * 本类型将尝试转换{@link RuntimeException} 转换成 {@link org.springframework.context.MessageSourceResolvable}。
 *
 * @author 应卓
 * @since 3.3.1
 */
public abstract class AbstractPropertyEditor<T> extends PropertyEditorSupport implements PropertyEditor {

    /**
     * {@inheritDoc}
     */
    @Override
    public final void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(convert(text));
        } catch (RuntimeException e) {
            throw InternalConverterUtils.transform(e);
        }
    }

    /**
     * 转换数据
     *
     * @param text 要转换的文本
     * @return 转换结果
     * @throws DataBindingException 数据转换失败或数据非法
     */
    protected abstract T convert(String text) throws DataBindingException;

}
