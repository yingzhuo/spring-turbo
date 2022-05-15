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
import java.util.Optional;
import java.util.Properties;

/**
 * @author 应卓
 * @see ResourceOptions#builder()
 * @see ResourceOptions#fromSeparatedLocations(String)
 * @see ResourceOptions#of(String)
 * @see ResourceOptions#of(Resource)
 * @see ResourceOptions#contact(ResourceOption...)
 * @see ResourceOptions#empty()
 * @since 1.0.0
 */
public interface ResourceOption extends Serializable {

    public Optional<Resource> toOptional();

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

    // since 1.0.8
    public default LineIterator getLineIterator() {
        return getLineIterator(CharsetPool.UTF_8);
    }

    // since 1.0.8
    public LineIterator getLineIterator(Charset charset);

}
