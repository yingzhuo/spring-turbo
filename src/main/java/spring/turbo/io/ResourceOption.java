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
import org.springframework.lang.NonNull;
import spring.turbo.util.CharsetPool;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

/**
 * @author 应卓
 * @since 1.0.0
 */
public interface ResourceOption extends Serializable {

    @NonNull
    public Optional<Resource> toOptional();

    public boolean isAbsent();

    public default boolean isPresent() {
        return !isAbsent();
    }

    @NonNull
    @Override
    public String toString();

    @NonNull
    public String toString(Charset charset);

    @NonNull
    public byte[] toByteArray();

    @NonNull
    public File toFile();

    @NonNull
    public Path toPath();

    @NonNull
    public Properties toProperties(PropertiesFormat propertiesFormat);

    public long getChecksumCRC32(int buffSize);

    public default long getChecksumCRC32() {
        return getChecksumCRC32(1024);
    }

    @NonNull
    public default Properties toProperties() {
        return toProperties(PropertiesFormat.PROPERTIES);
    }

    // since 1.0.8
    @NonNull
    public default LineIterator getLineIterator() {
        return getLineIterator(CharsetPool.UTF_8);
    }

    // since 1.0.8
    @NonNull
    public LineIterator getLineIterator(Charset charset);

}
