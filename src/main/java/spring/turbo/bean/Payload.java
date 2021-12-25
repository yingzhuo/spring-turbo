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
import spring.turbo.util.StringFormatter;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

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
    public <T> T find(String key) {
        return (T) get(key);
    }

    // since 1.0.1
    @Nullable
    public <T> T findOrDefault(String key, @Nullable T defaultIfNull) {
        return Optional.<T>ofNullable(find(key)).orElse(defaultIfNull);
    }

    // since 1.0.1
    @NonNull
    public <T> T findRequiredFirst(String key) {
        T obj = find(key);
        if (obj == null) {
            throw new NoSuchElementException(StringFormatter.format("element not found. key: {}", key));
        }
        return obj;
    }

}
