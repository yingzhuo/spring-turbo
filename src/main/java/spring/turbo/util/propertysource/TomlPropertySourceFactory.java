/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.propertysource;

import org.springframework.core.io.support.PropertySourceFactory;
import spring.turbo.lang.NotRecommended;

/**
 * @author 应卓
 * @see YamlPropertySourceFactory
 * @see HoconPropertySourceFactory
 * @since 2.0.6
 */
@NotRecommended
public class TomlPropertySourceFactory extends AbstractPropertySourceFactory implements PropertySourceFactory {

    /**
     * 默认构造方法
     */
    public TomlPropertySourceFactory() {
        super(new TomlPropertySourceLoader());
    }

}
