/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import spring.turbo.lang.Mutable;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Mutable
public final class Payload extends HashMap<String, Object> implements Map<String, Object> {

    private Payload() {
        super();
    }

    public static Payload newInstance() {
        return new Payload();
    }

    public Payload add(String name, Object value) {
        this.put(name, value);
        return this;
    }

}
