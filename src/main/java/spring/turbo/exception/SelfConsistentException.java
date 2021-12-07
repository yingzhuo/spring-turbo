/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 自洽性错误
 *
 * @author 应卓
 * @since 1.0.0
 */
public class SelfConsistentException extends RuntimeException {

    public SelfConsistentException() {
        super();
    }

    public SelfConsistentException(String message) {
        super(message);
    }

    public Map<String, Object> asMap() {
        return asMap(true);
    }

    public Map<String, Object> asMap(boolean includeType) {
        final Map<String, Object> map = new HashMap<>();
        map.put("message", getMessage());
        if (includeType) {
            map.put("type", SelfConsistentException.class.getName());
        }
        return Collections.unmodifiableMap(map);
    }

}
