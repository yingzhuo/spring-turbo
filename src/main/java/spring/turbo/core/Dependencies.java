/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import spring.turbo.util.ClassUtils;

/**
 * @author 应卓
 * @since 2.0.7
 */
public final class Dependencies {

    public static final boolean IS_HOCON_PRESENT = ClassUtils.isPresent("com.typesafe.config.Config");

    public static final boolean IS_TOML_PRESENT = ClassUtils.isPresent("com.moandjiezana.toml.Toml");

    /**
     * 私有构造方法
     */
    private Dependencies() {
        super();
    }

}
