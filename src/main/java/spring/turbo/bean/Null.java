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

import java.io.Serializable;
import java.util.Optional;

/**
 * 空值占位对象
 *
 * @author 应卓
 * @since 1.0.0
 */
public final class Null implements Serializable {

    private static final Null INSTANCE = new Null();

    private Null() {
        super();
    }

    public static Object replaceIfNull(@Nullable Object obj) {
        return Optional.ofNullable(obj).orElse(INSTANCE);
    }

    public static Null getInstance() {
        return INSTANCE;
    }

    @Override
    public String toString() {
        return "null";
    }

}
