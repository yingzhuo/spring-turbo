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
public final class InstanceCache {

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

    public <T> T findOrCreate(Class<?> type) {
        return findOrCreate(type, new InstantiationExceptionSupplier(type));
    }

    public <T> T findOrCreate(Class<?> type, Supplier<? extends RuntimeException> exceptionIfCannotCreateInstance) {
        Asserts.notNull(exceptionIfCannotCreateInstance);

        Object instance = map.get(type);
        if (instance == null) {
            instance = find(type, exceptionIfCannotCreateInstance);
            this.add(type, instance);
        }
        return (T) instance;
    }

    @NonNull
    private Object find(Class<?> type, Supplier<? extends RuntimeException> exceptionIfCannotCreateInstance) {
        // 没有spring容器
        // 尝试反射创建
        if (applicationContext == null) {
            return InstanceUtils.newInstanceOrThrow(type, exceptionIfCannotCreateInstance);
        }

        // 有spring容器
        // 尝试查找或反射创建
        final ObjectProvider objectProvider = applicationContext.getBeanProvider(type);
        try {
            return objectProvider.getIfAvailable(new ReflectionObjectSupplier(type));
        } catch (BeansException e) {
            throw exceptionIfCannotCreateInstance.get();
        }
    }

}
