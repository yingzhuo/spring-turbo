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
import spring.turbo.lang.Singleton;
import spring.turbo.util.StringPool;

import java.io.Serializable;
import java.util.Optional;

/**
 * 空值占位对象
 *
 * @author 应卓
 * @see #getInstance()
 * @since 1.0.0
 */
@Singleton
public final class Null implements Serializable {

    /**
     * 私有构造方法
     */
    private Null() {
        super();
    }

    public static Null getInstance() {
        return AsyncAvoid.INSTANCE;
    }

    public static Object replaceIfNull(@Nullable Object obj) {
        return Optional.ofNullable(obj).orElse(getInstance());
    }

    @Override
    public String toString() {
        return StringPool.NULL;
    }

    // -----------------------------------------------------------------------------------------------------------------

    // 延迟加载
    private static class AsyncAvoid {
        private static final Null INSTANCE = new Null();
    }

}
