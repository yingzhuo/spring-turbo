/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 应卓
 * @see ListFactories
 * @see SetFactories
 * @see StreamFactories
 * @since 1.1.0
 */
public final class StringObjectMap extends HashMap<String, Object> implements Map<String, Object> {

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

    public StringObjectMap delete(String... keys) {
        if (keys != null) {
            for (String key : keys) {
                if (key != null) {
                    this.remove(key);
                }
            }
        }
        return this;
    }

}
