/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.util.ClassUtils;
import spring.turbo.util.Asserts;

import java.lang.reflect.Constructor;
import java.util.function.Supplier;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class ReflectionObjectSupplier<T> implements Supplier<T> {

    public static <T> ReflectionObjectSupplier<T> of(Class<T> type) {
        return new ReflectionObjectSupplier<>(type);
    }

    private final Class<T> type;

    public ReflectionObjectSupplier(Class<T> type) {
        Asserts.notNull(type);
        this.type = type;
    }

    @Override
    public T get() {
        final Constructor<T> constructor = ClassUtils.getConstructorIfAvailable(type);
        if (constructor == null) {
            throw new IllegalArgumentException("cannot found the constructor");
        }

        try {
            constructor.setAccessible(true);
            return constructor.newInstance();
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
