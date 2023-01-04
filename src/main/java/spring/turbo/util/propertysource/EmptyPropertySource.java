/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.propertysource;

import org.springframework.core.env.PropertySource;
import org.springframework.lang.Nullable;

import java.util.Objects;

/**
 * @author 应卓
 * @since 2.0.7
 */
public final class EmptyPropertySource extends PropertySource<Object> {

    /**
     * 私有构造方法
     *
     * @param name 名称
     */
    private EmptyPropertySource(String name) {
        super(name);
    }

    public static EmptyPropertySource of() {
        return of(null);
    }

    public static EmptyPropertySource of(@Nullable String name) {
        return new EmptyPropertySource(Objects.requireNonNullElse(name, "empty"));
    }

    @Nullable
    @Override
    public Object getProperty(String name) {
        return null;
    }

}
