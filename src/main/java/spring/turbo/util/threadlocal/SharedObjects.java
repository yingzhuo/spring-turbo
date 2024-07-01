/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.threadlocal;

import org.springframework.core.NamedThreadLocal;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;

/**
 * 简易工具，在同一线程中保存一下实例。
 *
 * @author 应卓
 * @since 3.3.2
 */
@SuppressWarnings("unchecked")
public final class SharedObjects {

    /**
     * 私有构造方法
     */
    private SharedObjects() {
        super();
    }

    private static final ThreadLocal<Map<Class<?>, Object>> holderByType =
            NamedThreadLocal.withInitial(SharedObjects.class.getName(), HashMap::new);


    private static final ThreadLocal<Map<String, Object>> holderByName =
            NamedThreadLocal.withInitial(SharedObjects.class.getName(), HashMap::new);


    public static <T> void put(Class<T> type, T obj) {
        holderByType.get().put(type, obj);
    }

    public static void put(String name, Object obj) {
        holderByName.get().put(name, obj);
    }

    @Nullable
    public static <T> T get(Class<T> type) {
        return (T) holderByType.get().get(type);
    }

    @Nullable
    public static <T> T get(String name) {
        return (T) holderByName.get().get(name);
    }

    public static <T> T required(Class<T> type) {
        var o = get(type);
        if (o == null) {
            throw new IllegalStateException("Cannot get object of type: " + type.getName());
        }
        return o;
    }

    public static <T> T required(String name) {
        var o = get(name);
        if (o == null) {
            throw new IllegalStateException("Cannot get object of name: \"" + name + "\"");
        }
        return (T) o;
    }

}
