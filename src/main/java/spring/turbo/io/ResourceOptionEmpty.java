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
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;

/**
 * @author 应卓
 * @since 1.0.0
 */
final class ResourceOptionEmpty implements ResourceOption {

    static final ResourceOptionEmpty INSTANCE = new ResourceOptionEmpty();

    private ResourceOptionEmpty() {
    }

    @Override
    public Optional<Resource> toOptional() {
        return Optional.empty();
    }

    @Override
    public boolean isAbsent() {
        return true;
    }

    @Override
    public boolean isPresent() {
        return false;
    }

    @Override
    public String toString(Charset charset) {
        return null;
    }

    @Override
    public String toString() {
        return "Empty ResourceOption";
    }

    @Override
    public byte[] toByteArray() {
        return null;
    }

    @Override
    public File toFile() {
        return null;
    }

    @Override
    public Path toPath() {
        return null;
    }

    @Override
    public Properties toProperties(PropertiesFormat propertiesFormat) {
        return null;
    }

    @Override
    public Properties toProperties() {
        return null;
    }

    @Override
    public long getChecksumCRC32(int buffSize) {
        return -1;
    }

    @Override
    public long getChecksumCRC32() {
        return -1;
    }

}
