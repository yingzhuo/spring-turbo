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
 * 依赖工具
 *
 * @author 应卓
 * @see spring.turbo.bean.injection.ApplicationName
 * @since 2.0.7
 */
public final class Dependencies {

    public static final boolean YAML_PRESENT = ClassUtils.isPresent("org.yaml.snakeyaml.Yaml");
    public static final boolean HOCON_PRESENT = ClassUtils.isPresent("com.typesafe.config.Config");
    public static final boolean TOML_PRESENT = ClassUtils.isPresent("com.moandjiezana.toml.Toml");

    /**
     * 私有构造方法
     */
    private Dependencies() {
        super();
    }

}
