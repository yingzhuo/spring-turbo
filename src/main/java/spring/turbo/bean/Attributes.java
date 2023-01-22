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

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Attributes
 *
 * @author 应卓
 * @see Payload
 * @see MultiValueMap
 * @since 1.0.0
 */
@Mutable
@SuppressWarnings("unchecked")
public class Attributes extends LinkedMultiValueMap<String, Object> {

    /**
     * 构造方法
     */
    public Attributes() {
        super();
    }

    /**
     * 构造方法
     *
     * @param map 其他数据来源
     */
    public Attributes(@Nullable Map<String, List<Object>> map) {
        if (map != null) {
            super.putAll(map);
        }
    }

    /**
     * 构造方法
     *
     * @param anotherAttributes 其他数据来源
     */
    public Attributes(@Nullable Attributes anotherAttributes) {
        this((Map<String, List<Object>>) anotherAttributes);
    }

    /**
     * 创建Attributes实例
     *
     * @return Attributes实例
     */
    public static Attributes newInstance() {
        return new Attributes();
    }

    /**
     * 通过 {@link Map} 创建Attributes实例
     *
     * @param map map
     * @return Attributes实例
     * @since 1.0.1
     */
    public static Attributes fromMap(@Nullable Map<String, Object> map) {
        final Attributes attributes = new Attributes();
        if (!CollectionUtils.isEmpty(map)) {
            for (String key : map.keySet()) {
                Object value = map.get(key);
                attributes.add(key, value);
            }
        }
        return attributes;
    }

    /**
     * 从字典构建对象
     *
     * @param map 其他数据来源
     * @return Attributes实例
     * @since 1.1.2
     */
    public static Attributes fromListMap(@Nullable Map<String, List<Object>> map) {
        return new Attributes(map);
    }

    /**
     * 从其他attributes构建本对象
     *
     * @param attributes 其他数据来源
     * @return Attributes实例
     * @since 1.1.2
     */
    public static Attributes fromAttributes(@Nullable Attributes attributes) {
        return new Attributes(attributes);
    }

    /**
     * 通过 {@link MultiValueMap} 的实例创建Attributes实例
     *
     * @param map map
     * @return Attributes实例
     * @since 1.0.1
     */
    public static Attributes fromMultiValueMap(@Nullable MultiValueMap<String, Object> map) {
        final Attributes attributes = new Attributes();
        Optional.ofNullable(map).ifPresent(attributes::addAll);
        return attributes;
    }

    /**
     * 获取第一个值
     *
     * @param key key
     * @param <T> 返回值类型泛型
     * @return 值或者  {@code null}
     * @since 1.0.1
     */
    @Nullable
    public <T> T findFirst(@NonNull String key) {
        Asserts.notNull(key);
        return (T) super.getFirst(key);
    }

    /**
     * 获取第一个值
     *
     * @param key           key
     * @param defaultIfNull 找不到时的默认值
     * @param <T>           返回值类型泛型
     * @return 值或者默认值
     * @see #findFirst(String)
     * @see #findRequiredFirst(String)
     * @see #findRequiredFirst(String, Supplier)
     * @since 1.0.1
     */
    @Nullable
    public <T> T findFirstOrDefault(@NonNull String key, @Nullable T defaultIfNull) {
        Asserts.notNull(key);
        return Optional.<T>ofNullable(findFirst(key)).orElse(defaultIfNull);
    }

    /**
     * 获取第一个值
     *
     * @param key           key
     * @param defaultIfNull 找不到时的默认值生成器
     * @param <T>           返回值类型泛型
     * @return 值或者默认值
     * @see #findFirst(String)
     * @see #findRequiredFirst(String)
     * @see #findRequiredFirst(String, Supplier)
     * @since 1.1.2
     */
    @Nullable
    public <T> T findFirstOrDefault(@NonNull String key, @NonNull Supplier<T> defaultIfNull) {
        Asserts.notNull(key);
        return Optional.<T>ofNullable(findFirst(key)).orElseGet(defaultIfNull);
    }

    /**
     * 获取第一个值，如果找不到key值将抛出异常
     *
     * @param key key
     * @param <T> 返回值类型泛型
     * @return 值
     * @throws NoSuchElementException 找不到key值时抛出异常
     * @see #findRequiredFirst(String)
     * @since 1.0.5
     */
    @NonNull
    public <T> T findRequiredFirst(@NonNull String key) {
        return findRequiredFirst(key, () -> new NoSuchElementException(StringFormatter.format("element not found. key: {}", key)));
    }

    /**
     * 获取第一个值，如果找不到key值将抛出异常
     *
     * @param key                    key
     * @param exceptionIfKeyNotFound 找不到key对应的值时的异常提供器
     * @param <T>                    返回值类型泛型
     * @return 值
     * @see #findRequiredFirst(String)
     * @since 1.0.5
     */
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
