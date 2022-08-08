/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean;

import org.springframework.lang.Nullable;
import spring.turbo.util.StringPool;

import java.io.Serializable;
import java.util.Optional;

/**
 * 空值占位对象
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class Null implements Serializable {

    public static Null getInstance() {
        return AsyncVoid.INSTANCE;
    }

    private Null() {
        super();
    }

    public static Object replaceIfNull(@Nullable Object obj) {
        return Optional.ofNullable(obj).orElse(getInstance());
    }

    @Override
    public String toString() {
        return StringPool.NULL;
    }

    private static class AsyncVoid {
        private static final Null INSTANCE = new Null();
    }

}
