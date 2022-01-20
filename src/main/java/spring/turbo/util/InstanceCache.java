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
 * 实例缓存
 *
 * @author 应卓
 * @see InstanceUtils
 * @see spring.turbo.core.InstanceCacheAware
 * @see spring.turbo.core.InstanceCacheAwareBeanPostProcessor
 * @since 1.0.0
 */
@Mutable
@SuppressWarnings({"unchecked", "rawtypes"})
public final class InstanceCache {

    /**
     * 本地缓存
     */
    private final Map<Class<?>, Object> map = new HashMap<>();

    /**
     * Spring上下文
     */
    @Nullable
    private final ApplicationContext applicationContext;

    /**
     * 私有构造方法
     *
     * @param applicationContext Spring上下文，可为 {@code null}
     */
    private InstanceCache(@Nullable ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * 创建实例缓存实例
     *
     * @return InstanceCache
     */
    public static InstanceCache newInstance() {
        return new InstanceCache(null);
    }

    /**
     * 创建实例缓存实例
     *
     * @param applicationContext Spring上下文，可为 {@code null}
     * @return InstanceCache
     */
    public static InstanceCache newInstance(@Nullable ApplicationContext applicationContext) {
        return new InstanceCache(applicationContext);
    }

    /**
     * 用户初始化缓存
     *
     * @param type     要缓存的类型 (不可为null)
     * @param instance 实例 (不可为null)
     * @return InstanceCache本身
     */
    public InstanceCache add(Class<?> type, Object instance) {
        Asserts.notNull(type);
        Asserts.notNull(instance);

        map.put(type, instance);
        return this;
    }

    /**
     * 查找或尝试创建对象
     *
     * @param type 实例的类型
     * @param <T>  实例泛型
     * @return 实例值
     */
    @NonNull
    public synchronized <T> T findOrCreate(@NonNull Class<?> type) {
        return findOrCreate(type, new InstantiationExceptionSupplier(type));
    }

    /**
     * 查找或尝试创建对象
     *
     * @param type                            实例的类型
     * @param exceptionIfCannotCreateInstance 在尝试创建对象失败时异常提供器
     * @param <T>                             实例泛型
     * @return 实例值
     */
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
