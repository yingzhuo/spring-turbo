/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.valueobject;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.BeansException;
import org.springframework.beans.DirectFieldAccessor;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.util.Objects;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author 应卓
 * @since 1.0.6
 */
public class ValueObjectGetter {

    private final Object vo;
    private final BeanWrapper vow;
    private final DirectFieldAccessor vofa;

    public ValueObjectGetter(@NonNull Object vo) {
        Asserts.notNull(vo);
        this.vo = vo;
        this.vow = new BeanWrapperImpl(vo);
        this.vofa = new DirectFieldAccessor(vo);
    }

    @Nullable
    public Object get(@Nullable String key) {
        if (key == null) {
            return null;
        }

        Optional<Object> op = getFromWrapper(key);
        if (!op.isPresent()) {
            op = getFromFieldAccessor(key);
        }
        return op.orElse(null);
    }

    @Nullable
    public Object getOrDefault(@Nullable String key, Object defaultIfNull) {
        return Optional.ofNullable(get(key)).orElse(defaultIfNull);
    }

    @NonNull
    public Object getOrThrow(@Nullable String key, Supplier<? extends RuntimeException> exceptionIfNotFound) {
        Asserts.notNull(exceptionIfNotFound);
        return Optional.ofNullable(get(key)).orElseThrow(exceptionIfNotFound);
    }

    @NonNull
    public Object getOrThrow(@Nullable String key) {
        return getOrThrow(key, () -> new IllegalArgumentException("key not found: " + key));
    }

    @NonNull
    private Optional<Object> getFromWrapper(String key) {
        try {
            return Optional.ofNullable(vow.getPropertyValue(key));
        } catch (BeansException e) {
            return Optional.empty();
        }
    }

    @NonNull
    private Optional<Object> getFromFieldAccessor(String key) {
        try {
            return Optional.ofNullable(vofa.getPropertyValue(key));
        } catch (BeansException e) {
            return Optional.empty();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ValueObjectGetter that = (ValueObjectGetter) o;
        return vo.equals(that.vo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(vo);
    }

}
