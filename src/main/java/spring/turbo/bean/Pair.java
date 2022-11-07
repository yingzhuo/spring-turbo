/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.Nullable;
import spring.turbo.lang.Immutable;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringFormatter;

import java.io.Serializable;
import java.util.Objects;

/**
 * 二元组
 *
 * @author 应卓
 * @see Tuple
 * @see #ofNullable(Object, Object)
 * @see #ofNonNull(Object, Object)
 * @since 1.0.0
 */
@Immutable
public final class Pair<A, B> implements Serializable {

    @Nullable
    private final A a;

    @Nullable
    private final B b;

    private Pair(@Nullable A a, @Nullable B b) {
        this.a = a;
        this.b = b;
    }

    public static <A, B> Pair<A, B> ofNullable(@Nullable A a, @Nullable B b) {
        return new Pair<>(a, b);
    }

    public static <A, B> Pair<A, B> ofNonNull(A a, B b) {
        Asserts.notNull(a);
        Asserts.notNull(b);
        return new Pair<>(a, b);
    }

    @Nullable
    public A getA() {
        return a;
    }

    // since 1.0.10
    public A getRequiredA() {
        Asserts.notNull(a);
        return a;
    }

    @Nullable
    public B getB() {
        return b;
    }

    // since 1.0.10
    public B getRequiredB() {
        Asserts.notNull(b);
        return b;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(a, pair.a) && Objects.equals(b, pair.b);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b);
    }

    @Override
    public String toString() {
        return StringFormatter.format("({}, {})", a, b);
    }

}
