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
import org.springframework.util.CollectionUtils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import spring.turbo.lang.Mutable;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringFormatter;

import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author 应卓
 * @see Payload
 * @since 1.0.0
 */
@Mutable
@SuppressWarnings("unchecked")
public class Attributes extends LinkedMultiValueMap<String, Object> {

    public Attributes() {
        super();
    }

    public static Attributes newInstance() {
        return new Attributes();
    }

    // since 1.0.1
    public static Attributes fromMap(@Nullable Map<String, Object> map) {
        Attributes attributes = new Attributes();
        if (!CollectionUtils.isEmpty(map)) {
            for (String key : map.keySet()) {
                Object value = map.get(key);
                attributes.add(key, value);
            }
        }
        return attributes;
    }

    // since 1.0.1
    public static Attributes fromMultiValueMap(@Nullable MultiValueMap<String, Object> map) {
        final Attributes attributes = new Attributes();
        Optional.ofNullable(map).ifPresent(attributes::addAll);
        return attributes;
    }

    // since 1.0.1
    @Nullable
    public <T> T findFirst(@NonNull String key) {
        Asserts.notNull(key);
        return (T) super.getFirst(key);
    }

    // since 1.0.1
    @Nullable
    public <T> T findFirstOrDefault(@NonNull String key, @Nullable T defaultIfNull) {
        Asserts.notNull(key);
        return Optional.<T>ofNullable(findFirst(key)).orElse(defaultIfNull);
    }

    @NonNull
    public <T> T findRequiredFirst(@NonNull String key) {
        return findRequiredFirst(key, () -> new NoSuchElementException(StringFormatter.format("element not found. key: {}", key)));
    }

    // since 1.0.5
    @NonNull
    public <T> T findRequiredFirst(@NonNull String key, @NonNull Supplier<? extends RuntimeException> exceptionIfKeyNotFound) {
        Asserts.notNull(key);
        Asserts.notNull(exceptionIfKeyNotFound);

        T obj = findFirst(key);
        if (obj == null) {
            throw exceptionIfKeyNotFound.get();
        }
        return obj;
    }

}
