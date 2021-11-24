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

    public Optional<Resource> toOptional();

    public boolean isAbsent();

    public default boolean isPresent() {
        return !isAbsent();
    }

    public String toString(Charset charset);

    @Override
    public String toString();

    public byte[] toByteArray();

    public File toFile();

    public Path toPath();

    public Properties toProperties(boolean xmlFormat);

    public default Properties toProperties() {
        return toProperties(false);
    }

}
