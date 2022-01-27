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
import spring.turbo.util.CollectionUtils;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author 应卓
 * @see ValueObjectFilter
 * @since 1.0.1
 */
public final class ValueObjectFilterFactories {

    /**
     * 私有构造方法
     */
    private ValueObjectFilterFactories() {
        super();
    }

    public static <T> ValueObjectFilter<T> alwaysTrue() {
        return data -> true;
    }

    public static <T> ValueObjectFilter<T> alwaysFalse() {
        return data -> false;
    }

    public static <T> ValueObjectFilter<T> not(ValueObjectFilter<T> other) {
        Asserts.notNull(other);
        return data -> !other.test(data);
    }

    @SafeVarargs
    public static <T> ValueObjectFilter<T> any(ValueObjectFilter<T>... filters) {
        return new Any<>(Arrays.asList(filters));
    }

    @SafeVarargs
    public static <T> ValueObjectFilter<T> all(ValueObjectFilter<T>... filters) {
        return new All<>(Arrays.asList(filters));
    }

    public static <T> ValueObjectFilter<T> or(ValueObjectFilter<T> f1, ValueObjectFilter<T> f2) {
        Asserts.notNull(f1);
        Asserts.notNull(f2);
        return any(f1, f2);
    }

    public static <T> ValueObjectFilter<T> and(ValueObjectFilter<T> f1, ValueObjectFilter<T> f2) {
        Asserts.notNull(f1);
        Asserts.notNull(f2);
        return all(f1, f2);
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static final class Any<T> implements ValueObjectFilter<T> {

        private final List<ValueObjectFilter<T>> list = new LinkedList<>();

        public Any(List<ValueObjectFilter<T>> list) {
            CollectionUtils.nullSafeAddAll(this.list, list);
            Asserts.isTrue(this.list.size() >= 2);
        }

        @Override
        public boolean test(T data) {
            for (ValueObjectFilter<T> filter : list) {
                if (filter.test(data)) {
                    return true;
                }
            }
            return false;
        }
    }

    private static final class All<T> implements ValueObjectFilter<T> {

        private final List<ValueObjectFilter<T>> list = new LinkedList<>();

        public All(List<ValueObjectFilter<T>> list) {
            CollectionUtils.nullSafeAddAll(this.list, list);
            Asserts.isTrue(this.list.size() >= 2);
        }

        @Override
        public boolean test(T data) {
            for (ValueObjectFilter<T> filter : list) {
                if (!filter.test(data)) {
                    return false;
                }
            }
            return true;
        }
    }

}
