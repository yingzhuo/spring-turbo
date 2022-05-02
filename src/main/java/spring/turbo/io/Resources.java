/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import spring.turbo.util.Asserts;
import spring.turbo.util.CharsetPool;
import spring.turbo.util.ClassUtils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;

/**
 * @author 应卓
 * @since 1.0.15
 */
public final class Resources {

    /**
     * 私有构造方法
     */
    private Resources() {
        super();
    }

    public static InputStream getResourceAsStream(String name) {
        Asserts.notNull(name);
        final InputStream in = ClassUtils.getDefaultClassLoader().getResourceAsStream(name);
        Asserts.notNull(in);
        return in;
    }

    public static Reader getResourceAsReader(String name) {
        return getResourceAsReader(name, CharsetPool.UTF_8);
    }

    public static Reader getResourceAsReader(String name, Charset charset) {
        Asserts.notNull(name);
        Asserts.notNull(charset);
        return new InputStreamReader(getResourceAsStream(name), charset);
    }

}
