/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.format;

import org.springframework.format.Printer;
import org.springframework.lang.NonNull;

import java.util.Locale;

/**
 * @author 应卓
 * @since 1.0.8
 */
public final class ToStringPrint implements Printer<Object> {

    public static final ToStringPrint INSTANCE = new ToStringPrint();

    private ToStringPrint() {
        super();
    }

    public static ToStringPrint getInstance() {
        return INSTANCE;
    }

    @NonNull
    @Override
    public String print(@NonNull Object object, @NonNull Locale locale) {
        return object.toString();
    }

}
