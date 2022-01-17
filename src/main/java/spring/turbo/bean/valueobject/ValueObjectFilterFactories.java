/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.lang.NonNull;
import spring.turbo.util.Asserts;

/**
 * @author 应卓
 * @since 1.0.1
 */
public final class ValueObjectFilterFactories {

    private ValueObjectFilterFactories() {
        super();
    }

    public static <T> ValueObjectFilter<T> alwaysTrue() {
        return data -> true;
    }

    public static <T> ValueObjectFilter<T> alwaysFalse() {
        return data -> false;
    }

    public static <T> ValueObjectFilter<T> not(@NonNull ValueObjectFilter<T> other) {
        Asserts.notNull(other);
        return data -> !other.filter(data);
    }

    public static <T> ValueObjectFilter<T> or(@NonNull ValueObjectFilter<T> f1, @NonNull ValueObjectFilter<T> f2) {
        Asserts.notNull(f1);
        Asserts.notNull(f2);
        return data -> f1.filter(data) || f2.filter(data);
    }

    public static <T> ValueObjectFilter<T> and(@NonNull ValueObjectFilter<T> f1, @NonNull ValueObjectFilter<T> f2) {
        Asserts.notNull(f1);
        Asserts.notNull(f2);
        return data -> f1.filter(data) && f2.filter(data);
    }

    public static <T> ValueObjectFilter<T> xor(@NonNull ValueObjectFilter<T> f1, @NonNull ValueObjectFilter<T> f2) {
        Asserts.notNull(f1);
        Asserts.notNull(f2);
        return data -> f1.filter(data) ^ f2.filter(data);
    }

}
