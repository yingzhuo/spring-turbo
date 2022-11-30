/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import org.springframework.lang.Nullable;

/**
 * 未实现的功能
 *
 * @author 应卓
 * @since 1.0.11
 */
public class NotImplementedException extends UnsupportedOperationException {

    /**
     * 联系人 (workmate)
     */
    @Nullable
    private final String contact;

    public NotImplementedException() {
        this.contact = null;
    }

    public NotImplementedException(@Nullable String contact) {
        this.contact = contact;
    }

    public NotImplementedException(String message, @Nullable String contact) {
        super(message);
        this.contact = contact;
    }

    @Nullable
    public String getContact() {
        return contact;
    }

}
