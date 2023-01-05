/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.core.io.Resource;
import spring.turbo.util.CharsetPool;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Properties;

/**
 * {@link Resource} 的包装工具
 *
 * @author 应卓
 * @see ResourceOptions
 * @see ResourceOptionBuilder
 * @since 1.0.0
 */
public sealed interface ResourceOption extends Serializable
        permits ResourceOptionEmpty, ResourceOptionImpl {

    public Optional<Resource> toOptional();

    public default Resource get() {
        var optional = toOptional();
        if (optional.isPresent()) {
            return optional.get();
        } else {
            throw new NoSuchElementException("No resource present");
        }
    }

    public boolean isAbsent();

    public default boolean isPresent() {
        return !isAbsent();
    }

    @Override
    public String toString();

    public String toString(Charset charset);

    public InputStream toInputStream();

    public byte[] toByteArray();

    public File toFile();

    public Path toPath();

    public long getChecksumCRC32(int buffSize);

    public default long getChecksumCRC32() {
        return getChecksumCRC32(1024);
    }

    public Properties toProperties(PropertiesFormat propertiesFormat);

    public default Properties toProperties() {
        return toProperties(PropertiesFormat.PROPERTIES);
    }

    public default LineIterator getLineIterator() {
        return getLineIterator(CharsetPool.UTF_8);
    }

    public LineIterator getLineIterator(Charset charset);

}
