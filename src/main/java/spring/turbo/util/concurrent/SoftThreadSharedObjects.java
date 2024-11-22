package spring.turbo.util.concurrent;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * 简易工具，在同一线程中存取对象。<br>
 * 数据已软引用方式保存
 *
 * @author 应卓
 * @see SoftReference
 * @see WeakThreadSharedObjects
 * @see ThreadSharedObjects
 * @since 3.3.5
 */
@SuppressWarnings("unchecked")
public final class SoftThreadSharedObjects {

    /**
     * 按类型保存对象
     */
    private static final ThreadLocal<Map<Class<?>, SoftReference<Object>>> TYPE_HOLDER = ThreadLocal.withInitial(HashMap::new);

    /**
     * 按名称保存对象
     */
    private static final ThreadLocal<Map<String, SoftReference<Object>>> NAME_HOLDER = ThreadLocal.withInitial(HashMap::new);

    /**
     * 私有构造方法
     */
    private SoftThreadSharedObjects() {
    }

    /**
     * 保存数据
     *
     * @param name 数据名称
     * @param obj  数据
     */
    public static void put(String name, Object obj) {
        NAME_HOLDER.get().put(name, new SoftReference<>(obj));
    }

    /**
     * 保存数据
     *
     * @param type 数据类型
     * @param obj  数据
     * @param <T>  数据类型泛型
     */
    public static <T> void put(Class<T> type, Object obj) {
        TYPE_HOLDER.get().put(type, new SoftReference<>(obj));
    }

    /**
     * 按名称拿取数据
     *
     * @param name 数据名称
     * @param <T>  数据类型泛型
     * @return 数据或 {@code null}
     */
    public static <T> Optional<T> get(String name) {
        return (Optional<T>) Optional.ofNullable(NAME_HOLDER.get().get(name))
                .map(SoftReference::get);
    }

    /**
     * 按类型拿取数据
     *
     * @param type 数据类型
     * @param <T>  数据类型泛型
     * @return 数据或 {@code null}
     */
    public static <T> Optional<T> get(Class<T> type) {
        return (Optional<T>) Optional.ofNullable(TYPE_HOLDER.get().get(type))
                .map(SoftReference::get);
    }

    /**
     * 清除所有已保存的数据
     *
     * @see ThreadLocal#remove()
     */
    public static void remove() {
        TYPE_HOLDER.remove();
        TYPE_HOLDER.set(new HashMap<>());   // 一个空集合也浪费不了多少内存，算了
        NAME_HOLDER.remove();
        NAME_HOLDER.set(new HashMap<>());
    }

}
