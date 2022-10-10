/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.reflection;

import spring.turbo.util.Asserts;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.function.Predicate;

/**
 * @author 应卓
 * @see FieldUtils
 * @since 1.2.1
 */
public final class FieldPredicateFactories {

    /**
     * 私有构造方法
     */
    private FieldPredicateFactories() {
        super();
    }

    public static Predicate<Field> alwaysTrue() {
        return f -> true;
    }

    public static Predicate<Field> alwaysFalse() {
        return f -> false;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static Predicate<Field> not(Predicate<Field> predicate) {
        Asserts.notNull(predicate);
        return f -> !predicate.test(f);
    }

    @SafeVarargs
    public static Predicate<Field> any(Predicate<Field>... predicates) {
        Asserts.notNull(predicates);
        Asserts.isTrue(predicates.length >= 1);
        return m -> {
            for (final Predicate<Field> predicate : predicates) {
                if (predicate.test(m)) {
                    return true;
                }
            }
            return false;
        };
    }

    @SafeVarargs
    public static Predicate<Field> all(Predicate<Field>... predicates) {
        Asserts.notNull(predicates);
        Asserts.isTrue(predicates.length >= 1);
        return m -> {
            for (final Predicate<Field> predicate : predicates) {
                if (!predicate.test(m)) {
                    return false;
                }
            }
            return true;
        };
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static Predicate<Field> isCopyableField() {
        return (f -> !(Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers())));
    }

    public static Predicate<Field> isNotCopyableField() {
        return not(isCopyableField());
    }


}
