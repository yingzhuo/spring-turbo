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
import spring.turbo.util.Asserts;
import spring.turbo.util.StringFormatter;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * @author 应卓
 * @see Attributes
 * @see Map
 * @since 1.0.0
 */
@SuppressWarnings("unchecked")
public class Payload extends LinkedHashMap<String, Object> {

    /**
     * 构造方法
     */
    public Payload() {
        super();
    }

    /**
     * 创建Payload的实例
     *
     * @return Payload的实例
     */
    public static Payload newInstance() {
        return new Payload();
    }

    /**
     * 获取值
     *
     * @param key key
     * @param <T> 返回值类型泛型
     * @return 值或  {@code null}
     * @see #findOrDefault(String, Object)
     * @since 1.0.1
     */
    @Nullable
    public <T> T find(String key) {
        Asserts.notNull(key);
        return (T) get(key);
    }

    /**
     * 获取key对应值或默认值
     *
     * @param key           key
     * @param defaultIfNull 找不到时的默认值
     * @param <T>           返回值类型泛型
     * @return 值或者默认值
     * @see #find(String)
     * @since 1.0.1
     */
    @Nullable
    public <T> T findOrDefault(String key, @Nullable T defaultIfNull) {
        Asserts.notNull(key);
        return Optional.<T>ofNullable(find(key)).orElse(defaultIfNull);
    }

    /**
     * 获取key对应值或默认值
     *
     * @param key           key
     * @param defaultIfNull 找不到时的默认值
     * @param <T>           返回值类型泛型
     * @return 值或者默认值
     * @see #find(String)
     * @since 1.1.2
     */
    public <T> T findOrDefault(String key, @NonNull Supplier<T> defaultIfNull) {
        Asserts.notNull(key);
        return Optional.<T>ofNullable(find(key)).orElseGet(defaultIfNull);
    }

    /**
     * 获取key对应值或抛出异常
     *
     * @param key key
     * @param <T> 返回值类型泛型
     * @return 值
     * @throws NoSuchElementException 找不到key值时抛出异常
     * @see #findRequired(String, Supplier)
     * @since 1.0.1
     */
    public <T> T findRequired(String key) {
        return findRequired(key, () -> new NoSuchElementException(StringFormatter.format("element not found. key: {}", key)));
    }

    /**
     * 获取key对应值或抛出异常
     *
     * @param key                    key
     * @param exceptionIfKeyNotFound 找不到key对应的值时的异常提供器
     * @param <T>                    返回值类型泛型
     * @return 值
     * @see #findRequired(String)
     * @since 1.0.5
     */
    public <T> T findRequired(String key, Supplier<? extends RuntimeException> exceptionIfKeyNotFound) {
        Asserts.notNull(key);
        Asserts.notNull(exceptionIfKeyNotFound);

        T obj = find(key);
        if (obj == null) {
            throw exceptionIfKeyNotFound.get();
        }
        return obj;
    }

}
