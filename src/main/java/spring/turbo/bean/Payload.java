/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;
import spring.turbo.lang.Mutable;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringFormatter;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author 应卓
 * @see Attributes
 * @since 1.0.0
 */
@Mutable
@SuppressWarnings("unchecked")
public class Payload extends HashMap<String, Object> {

    public Payload() {
        super();
    }

    public static Payload newInstance() {
        return new Payload();
    }

    // since 1.0.1
    @Nullable
    public <T> T find(@NonNull String key) {
        Asserts.notNull(key);
        return (T) get(key);
    }

    // since 1.0.1
    @Nullable
    public <T> T findOrDefault(@NonNull String key, @Nullable T defaultIfNull) {
        Asserts.notNull(key);
        return Optional.<T>ofNullable(find(key)).orElse(defaultIfNull);
    }

    // since 1.0.1
    public <T> T findRequiredFirst(@NonNull String key) {
        return findRequiredFirst(key, () -> new NoSuchElementException(StringFormatter.format("element not found. key: {}", key)));
    }

    // since 1.0.5
    @NonNull
    public <T> T findRequiredFirst(@NonNull String key, @NonNull Supplier<? extends RuntimeException> exceptionIfKeyNotFound) {
        Asserts.notNull(key);
        Asserts.notNull(exceptionIfKeyNotFound);

        T obj = find(key);
        if (obj == null) {
            throw exceptionIfKeyNotFound.get();
        }
        return obj;
    }

}
