/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * 简易工具，在同一线程中存取对象
 *
 * @author 应卓
 * @since 3.3.1
 */
@SuppressWarnings("unchecked")
public final class TreadSharedObjects {

    /**
     * 按类型保存对象
     */
    private static final ThreadLocal<Map<Class<?>, Object>> TYPE_HOLDER =
            NamedThreadLocal.withInitial(TreadSharedObjects.class.getName(), HashMap::new);
    /**
     * 按名称保存对象
     */
    private static final ThreadLocal<Map<String, Object>> NAME_HOLDER =
            NamedThreadLocal.withInitial(TreadSharedObjects.class.getName(), HashMap::new);


    /**
     * 私有构造方法
     */
    private TreadSharedObjects() {
        super();
    }

    /**
     * 保存数据
     *
     * @param type 数据类型
     * @param obj  数据
     * @param <T>  数据类型泛型
     */
    public static <T> void put(Class<T> type, T obj) {
        TYPE_HOLDER.get().put(type, obj);
    }

    /**
     * 保存数据
     *
     * @param name 数据名称
     * @param obj  数据
     */
    public static void put(String name, Object obj) {
        NAME_HOLDER.get().put(name, obj);
    }

    /**
     * 按类型拿取数据
     *
     * @param type 数据类型
     * @param <T>  数据类型泛型
     * @return 数据或 {@code null}
     */
    @Nullable
    public static <T> T get(Class<T> type) {
        return (T) TYPE_HOLDER.get().get(type);
    }

    /**
     * 按名称拿取数据
     *
     * @param name 数据名称
     * @param <T>  数据类型泛型
     * @return 数据或 {@code null}
     */
    @Nullable
    public static <T> T get(String name) {
        return (T) NAME_HOLDER.get().get(name);
    }

    /**
     * 按类型拿取数据，并断言数据一定不是控制
     *
     * @param type 数据类型
     * @param <T>  数据类型泛型
     * @return 数据
     */
    public static <T> T required(Class<T> type) {
        var o = get(type);
        if (o == null) {
            var msg = StringFormatter.format("Cannot get object of type: '{}'", type.getName());
            throw new IllegalStateException(msg);
        }
        return o;
    }

    /**
     * 按名称拿取数据，并断言数据一定不是控制
     *
     * @param name 数据名称
     * @param <T>  数据类型泛型
     * @return 数据
     */
    public static <T> T required(String name) {
        var o = get(name);
        if (o == null) {
            var msg = StringFormatter.format("Cannot get object of name: '{}'", name);
            throw new IllegalStateException(msg);
        }
        return (T) o;
    }

    /**
     * 清除所有已保存的数据
     */
    public static void clear() {
        TYPE_HOLDER.get().clear();
        NAME_HOLDER.get().clear();
    }

}
