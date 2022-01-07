/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.api;

import org.springframework.lang.NonNull;
import spring.turbo.lang.Mutable;
import spring.turbo.util.Asserts;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Mutable
public class Json implements MutableApiResult<Map<String, Object>> {

    private String code = "200";
    private String errorMessage;
    private Map<String, Object> payload = new LinkedHashMap<>();
    private boolean deprecated = false;

    public Json() {
        super();
    }

    public static Json newInstance() {
        return new Json();
    }

    public Json code(String code) {
        this.code = code;
        return this;
    }

    public Json errorMessage(String msg) {
        this.errorMessage = msg;
        return this;
    }

    public Json payload(@NonNull String key, Object value) {
        Asserts.hasText(key);
        this.payload.put(key, value);
        return this;
    }

    public Json requiredPayload(@NonNull String key, @NonNull Object value) {
        Asserts.notNull(value);
        return payload(key, value);
    }

    public Json deprecated(boolean deprecated) {
        this.deprecated = deprecated;
        return this;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }

    @Override
    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public Map<String, Object> getPayload() {
        return payload;
    }

    @Override
    public void setPayload(Map<String, Object> payload) {
        this.payload = Optional.of(payload).orElseGet(LinkedHashMap::new);
    }

    @Override
    public boolean isDeprecated() {
        return deprecated;
    }

    @Override
    public void setDeprecated(boolean deprecated) {
        this.deprecated = deprecated;
    }

}
