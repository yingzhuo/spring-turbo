/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc;

import spring.turbo.bean.Payload;
import spring.turbo.lang.Mutable;

/**
 * @author 应卓
 * @since 1.0.0
 */
@Mutable
public final class Json implements ApiResult<Payload> {

    private String code = "200";
    private String errorMessage;
    private Payload payload = Payload.newInstance(); // mutable

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

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getErrorMessage() {
        return this.errorMessage;
    }

    @Override
    public Payload getPayload() {
        return this.payload;
    }

    @Override
    public int getPayloadSize() {
        return this.payload.size();
    }

}
