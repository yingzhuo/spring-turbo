/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

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
@SuppressWarnings("unchecked")
public final class InstanceCache {

    private final Map<Class<?>, Object> map = new HashMap<>();

    private InstanceCache() {
        super();
    }

    public static InstanceCache newInstance() {
        return new InstanceCache();
    }

    public InstanceCache add(Class<?> type, Object instance) {
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
        Object instance = map.get(type);
        if (instance == null) {
            instance = InstanceUtils.newInstanceOrThrow(type, exceptionIfCannotCreateInstance);
            this.add(type, instance);
        }
        return (T) instance;
    }

}
