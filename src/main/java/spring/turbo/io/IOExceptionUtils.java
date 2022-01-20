/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.io.IOException;
import java.io.UncheckedIOException;

/**
 * @author 应卓
 * @since 1.0.10
 */
public final class IOExceptionUtils {

    private IOExceptionUtils() {
        super();
    }

    public static UncheckedIOException toUnchecked(IOException e) {
        Asserts.notNull(e);
        return new UncheckedIOException(e.getMessage(), e);
    }

    public static UncheckedIOException toUnchecked(@Nullable String message) {
        return toUnchecked(new IOException(message));
    }

}
