/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.api;

import spring.turbo.bean.Payload;
import spring.turbo.lang.Mutable;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Mutable
public final class Json implements MutableApiResult<Payload> {

    private String code = "200";
    private String errorMessage;
    private Payload payload = Payload.newInstance();
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

    public Json payload(String key, Object value) {
        this.payload.put(key, value);
        return this;
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
    public Payload getPayload() {
        return payload;
    }

    @Override
    public void setPayload(Payload payload) {
        this.payload = payload;
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
