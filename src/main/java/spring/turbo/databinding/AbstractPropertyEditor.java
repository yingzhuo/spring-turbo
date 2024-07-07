package spring.turbo.databinding;

import spring.turbo.exception.DataBindingException;
import spring.turbo.util.ThreadSharedObjects;

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

    /**
     * 加入一个线程安全的共享对象。在线程的其他地方可以取出这个共享对象。
     *
     * @param objectType 共享对象类型
     * @param object     共享对象
     * @param <O>        共享对象类型实例
     * @see ThreadSharedObjects#put(Class, Object)
     */
    protected final <O> void setSharedObject(Class<O> objectType, O object) {
        ThreadSharedObjects.put(objectType, object);
    }

    /**
     * 加入一个线程安全的共享对象。在线程的其他地方可以取出这个共享对象。
     *
     * @param objectName 共享对象名称
     * @param object     共享对象
     * @see ThreadSharedObjects#put(String, Object)
     */
    protected final void setSharedObject(String objectName, Object object) {
        ThreadSharedObjects.put(objectName, object);
    }

}
