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
import org.springframework.util.StreamUtils;
import spring.turbo.util.Asserts;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.file.Path;
import java.util.Optional;
import java.util.Properties;
import java.util.zip.CRC32;
import java.util.zip.CheckedInputStream;

import static spring.turbo.io.IOExceptionUtils.toUnchecked;

/**
 * @author 应卓
 * @since 1.0.0
 */
final class ResourceOptionImpl implements ResourceOption {

    private final Resource resource;

    ResourceOptionImpl(Resource resource) {
        this.resource = resource;
    }

    @Override
    public Optional<Resource> toOptional() {
        return Optional.of(resource);
    }

    @Override
    public boolean isAbsent() {
        return false;
    }

    @Override
    public boolean isPresent() {
        return true;
    }

    @Override
    public String toString(Charset charset) {
        try {
            return StreamUtils.copyToString(resource.getInputStream(), charset);
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    @Override
    public String toString() {
        return resource.toString();
    }

    @Override
    public byte[] toByteArray() {
        try {
            return StreamUtils.copyToByteArray(resource.getInputStream());
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    @Override
    public File toFile() {
        try {
            return resource.getFile();
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    @Override
    public Path toPath() {
        try {
            return resource.getFile().toPath().normalize();
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    @Override
    public Properties toProperties(PropertiesFormat format) {
        Asserts.notNull(format);
        Properties props = new Properties();

        try {
            switch (format) {
                case PROPERTIES:
                    props.load(resource.getInputStream());
                    return props;
                case XML:
                    props.loadFromXML(resource.getInputStream());
                    return props;
                case YAML:
                    return YamlUtils.toProperties(resource);
                default:
                    throw new AssertionError();
            }
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    @Override
    public long getChecksumCRC32(int buffSize) {
        try {
            CheckedInputStream checkedInputStream = new CheckedInputStream(resource.getInputStream(), new CRC32());
            final byte[] buffer = new byte[buffSize];
            while (checkedInputStream.read(buffer, 0, buffer.length) >= 0) {
                // nop
            }
            return checkedInputStream.getChecksum().getValue();
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

    @Override
    public LineIterator getLineIterator(Charset charset) {
        try {
            return new LineIterator(new InputStreamReader(resource.getInputStream(), charset));
        } catch (IOException e) {
            throw toUnchecked(e);
        }
    }

}
