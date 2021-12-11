/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import spring.turbo.util.Asserts;
import spring.turbo.util.InstanceUtils;

import java.util.function.Supplier;

/**
 * @author 应卓
 * @see java.util.function.Supplier
 * @see ValueObjectUtils
 * @since 1.0.0
 */
public final class ReflectionObjectSupplier<T> implements Supplier<T> {

    private final Class<T> type;

    public ReflectionObjectSupplier(Class<T> type) {
        Asserts.notNull(type);
        this.type = type;
    }

    public static <T> ReflectionObjectSupplier<T> of(Class<T> type) {
        return new ReflectionObjectSupplier<>(type);
    }

    @Override
    public T get() {
        return InstanceUtils.newInstanceOrThrow(type);
    }

}
