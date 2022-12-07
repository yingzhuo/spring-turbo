/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.collection;

import org.springframework.lang.Nullable;
import spring.turbo.lang.Mutable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 应卓
 * @see ListFactories
 * @see SetFactories
 * @see StreamFactories
 * @see StringObjectMap
 * @since 1.1.2
 */
@Mutable
public final class StringStringMap extends HashMap<String, String> {

    /**
     * 构造方法
     */
    public StringStringMap() {
        super();
    }

    public static StringStringMap newInstance() {
        return new StringStringMap();
    }

    public StringStringMap add(String k, String v) {
        this.put(k, v);
        return this;
    }

    public StringStringMap add(String k1, String v1, String k2, String v2) {
        this.put(k1, v1);
        this.put(k2, v2);
        return this;
    }

    public StringStringMap add(String k1, String v1, String k2, String v2, String k3, String v3) {
        this.put(k1, v1);
        this.put(k2, v2);
        this.put(k3, v3);
        return this;
    }

    public StringStringMap add(String k1, String v1, String k2, String v2, String k3, String v3, String k4, String v4) {
        this.put(k1, v1);
        this.put(k2, v2);
        this.put(k3, v3);
        this.put(k4, v4);
        return this;
    }

    public StringStringMap removeAll() {
        this.clear();
        return this;
    }

    public StringStringMap delete(@Nullable String... keys) {
        if (keys != null) {
            for (String key : keys) {
                if (key != null) {
                    this.remove(key);
                }
            }
        }
        return this;
    }

    public Map<String, String> toUnmodifiable() {
        return Collections.unmodifiableMap(this);
    }

}
