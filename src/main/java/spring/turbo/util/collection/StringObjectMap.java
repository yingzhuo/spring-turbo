package spring.turbo.util.collection;

import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 应卓
 * @see ListFactories
 * @see SetFactories
 * @see StreamFactories
 * @see StringStringMap
 * @since 1.1.0
 */
public final class StringObjectMap extends HashMap<String, Object> {

    /**
     * 构造方法
     */
    public StringObjectMap() {
        super();
    }

    public static StringObjectMap newInstance() {
        return new StringObjectMap();
    }

    public StringObjectMap add(String k, Object v) {
        this.put(k, v);
        return this;
    }

    public StringObjectMap add(String k1, Object v1, String k2, Object v2) {
        this.put(k1, v1);
        this.put(k2, v2);
        return this;
    }

    public StringObjectMap add(String k1, Object v1, String k2, Object v2, String k3, Object v3) {
        this.put(k1, v1);
        this.put(k2, v2);
        this.put(k3, v3);
        return this;
    }

    public StringObjectMap add(String k1, Object v1, String k2, Object v2, String k3, Object v3, String k4, Object v4) {
        this.put(k1, v1);
        this.put(k2, v2);
        this.put(k3, v3);
        this.put(k4, v4);
        return this;
    }

    public StringObjectMap removeAll() {
        this.clear();
        return this;
    }

    public StringObjectMap delete(@Nullable String... keys) {
        if (keys != null) {
            for (String key : keys) {
                if (key != null) {
                    this.remove(key);
                }
            }
        }
        return this;
    }

    public Map<String, Object> toUnmodifiable() {
        return Collections.unmodifiableMap(this);
    }

}
