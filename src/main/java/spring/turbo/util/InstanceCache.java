/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.ApplicationContext;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import spring.turbo.lang.Mutable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

/**
 * @author 应卓
 * @see InstanceUtils
 * @since 1.0.0
 */
@Mutable
@SuppressWarnings({"unchecked", "rawtypes"})
public final class InstanceCache implements Serializable {

    private final Map<Class<?>, Object> map = new HashMap<>();
    private final ApplicationContext applicationContext;

    private InstanceCache(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public static InstanceCache newInstance() {
        return new InstanceCache(null);
    }

    public static InstanceCache newInstance(@Nullable ApplicationContext applicationContext) {
        return new InstanceCache(applicationContext);
    }

    public InstanceCache add(Class<?> type, Object instance) {
        Asserts.notNull(type);
        Asserts.notNull(instance);

        map.put(type, instance);
        return this;
    }

    public InstanceCache remove(Class<?> type) {
        map.remove(type);
        return this;
    }

    public InstanceCache clear() {
        map.clear();
        return this;
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public int size() {
        return map.size();
    }

    @NonNull
    public synchronized <T> T findOrCreate(@NonNull Class<?> type) {
        return findOrCreate(type, new InstantiationExceptionSupplier(type));
    }

    @NonNull
    public synchronized <T> T findOrCreate(@NonNull Class<?> type, @NonNull Supplier<? extends RuntimeException> exceptionIfCannotCreateInstance) {
        Asserts.notNull(type);
        Asserts.notNull(exceptionIfCannotCreateInstance);

        Object instance = findFromApplicationContext(type);
        if (instance != null) {
            // spring托管的不放入map
            // 如果该bean是prototype类型，每次都需要创建一个新对象
            return (T) instance;
        }

        instance = map.get(type);
        if (instance == null) {
            instance = InstanceUtils.newInstanceOrThrow(type, exceptionIfCannotCreateInstance);
            map.put(type, instance);
        }
        return (T) instance;
    }

    @Nullable
    private Object findFromApplicationContext(@NonNull Class<?> type) {
        if (applicationContext == null) {
            return null;
        }

        final ObjectProvider provider = applicationContext.getBeanProvider(type);
        try {
            return provider.getIfAvailable();
        } catch (BeansException e) {
            return null;
        }
    }

}
