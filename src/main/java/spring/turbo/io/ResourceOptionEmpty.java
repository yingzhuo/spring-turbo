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

    private static final ResourceOptionEmpty INSTANCE = new ResourceOptionEmpty();
    private static final IllegalStateException EX = new IllegalStateException("empty resource");

    public static ResourceOption getInstance() {
        return INSTANCE;
    }

    private ResourceOptionEmpty() {
        super();
    }

    @NonNull
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

    @NonNull
    @Override
    public String toString(Charset charset) {
        throw EX;
    }

    @NonNull
    @Override
    public String toString() {
        throw EX;
    }

    @NonNull
    @Override
    public byte[] toByteArray() {
        throw EX;
    }

    @NonNull
    @Override
    public File toFile() {
        throw EX;
    }

    @NonNull
    @Override
    public Path toPath() {
        throw EX;
    }

    @NonNull
    @Override
    public Properties toProperties(PropertiesFormat propertiesFormat) {
        throw EX;
    }

    @NonNull
    @Override
    public Properties toProperties() {
        throw EX;
    }

    @Override
    public long getChecksumCRC32(int buffSize) {
        throw EX;
    }

    @Override
    public long getChecksumCRC32() {
        throw EX;
    }

    @Override
    public LineIterator getLineIterator(Charset charset) {
        throw EX;
    }

    @NonNull
    @Override
    public LineIterator getLineIterator() {
        throw EX;
    }

}
