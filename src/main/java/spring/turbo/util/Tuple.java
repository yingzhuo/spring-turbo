package spring.turbo.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.Objects;
import java.util.Optional;

/**
 * 三元组
 *
 * @author 应卓
 * @see Pair
 * @see #ofNullable(Object, Object, Object)
 * @see #ofNonNull(Object, Object, Object)
 * @since 1.0.0
 */
public final class Tuple<A, B, C> implements Serializable {

    @Nullable
    private final A a;

    @Nullable
    private final B b;

    @Nullable
    private final C c;

    /**
     * 私有构造方法
     *
     * @param a a
     * @param b b
     * @param c c
     */
    private Tuple(@Nullable A a, @Nullable B b, @Nullable C c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public static <A, B, C> Tuple<A, B, C> ofNullable(@Nullable A a, @Nullable B b, @Nullable C c) {
        return new Tuple<>(a, b, c);
    }

    public static <A, B, C> Tuple<A, B, C> ofNonNull(@NonNull A a, @NonNull B b, @NonNull C c) {
        Assert.notNull(a, "a is required");
        Assert.notNull(b, "b is required");
        Assert.notNull(b, "c is required");
        return new Tuple<>(a, b, c);
    }

    public static <A, B, C> Tuple<A, B, C> of(@Nullable Pair<A, B> pair, @Nullable C c) {
        return new Tuple<>(Optional.ofNullable(pair).map(Pair::getA).orElse(null),
                Optional.ofNullable(pair).map(Pair::getB).orElse(null), c);
    }

    public static <A, B, C> Tuple<A, B, C> of(@Nullable A a, @Nullable Pair<B, C> pair) {
        return new Tuple<>(a, Optional.ofNullable(pair).map(Pair::getA).orElse(null),
                Optional.ofNullable(pair).map(Pair::getB).orElse(null));
    }

    @Nullable
    public A getA() {
        return a;
    }

    public A getRequiredA() {
        return Objects.requireNonNull(a);
    }

    @Nullable
    public B getB() {
        return b;
    }

    public B getRequiredB() {
        return Objects.requireNonNull(b);
    }

    @Nullable
    public C getC() {
        return c;
    }

    public C getRequiredC() {
        return Objects.requireNonNull(c);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Tuple<?, ?, ?> tuple = (Tuple<?, ?, ?>) o;
        return Objects.equals(a, tuple.a) && Objects.equals(b, tuple.b) && Objects.equals(c, tuple.c);
    }

    @Override
    public int hashCode() {
        return Objects.hash(a, b, c);
    }

    @Override
    public String toString() {
        return StringFormatter.format("({}, {}, {})", a, b, c);
    }

}
