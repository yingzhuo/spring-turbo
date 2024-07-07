package spring.turbo.databinding;

import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.CollectionUtils;
import org.springframework.util.MultiValueMap;
import spring.turbo.exception.DataBindingException;
import spring.turbo.util.ThreadSharedObjects;
import spring.turbo.util.collection.ArrayUtils;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * {@link GenericConverter} 辅助工具 <br>
 * 本类型将尝试转换{@link RuntimeException} 转换成 {@link org.springframework.context.MessageSourceResolvable}。
 *
 * @author 应卓
 * @since 3.3.1
 */
public abstract class AbstractGenericConverter implements GenericConverter {

    private final Set<ConvertiblePair> convertibleTypes;

    public AbstractGenericConverter(Class<?> sourceType, Class<?> targetType, Class<?>... moreTargetTypes) {
        Set<ConvertiblePair> set = new HashSet<>();
        set.add(new ConvertiblePair(sourceType, targetType));

        if (!ArrayUtils.isNullOrEmpty(moreTargetTypes)) {
            for (var it : moreTargetTypes) {
                set.add(new ConvertiblePair(sourceType, it));
            }
        }

        this.convertibleTypes = Collections.unmodifiableSet(set);
    }

    public AbstractGenericConverter(MultiValueMap<Class<?>, Class<?>> supported) {
        Set<ConvertiblePair> set = new HashSet<>();
        if (!CollectionUtils.isEmpty(supported)) {
            for (var sourceType : supported.keySet()) {
                for (var targetType : supported.get(sourceType)) {
                    set.add(new ConvertiblePair(sourceType, targetType));
                }
            }
        }
        this.convertibleTypes = Collections.unmodifiableSet(set);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public final Set<ConvertiblePair> getConvertibleTypes() {
        return this.convertibleTypes;
    }

    /**
     * {@inheritDoc}
     */
    @Nullable
    @Override
    public final Object convert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) {
        try {
            return doConvert(source, sourceType, targetType);
        } catch (RuntimeException e) {
            throw InternalConverterUtils.transform(e);
        }
    }

    /**
     * 转换数据
     *
     * @param source     源数据
     * @param sourceType 源数据类型
     * @param targetType 目标类型
     * @return 转换结果
     * @throws DataBindingException 数据转换失败或数据非法
     */
    @Nullable
    protected abstract Object doConvert(@Nullable Object source, TypeDescriptor sourceType, TypeDescriptor targetType) throws DataBindingException;

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
