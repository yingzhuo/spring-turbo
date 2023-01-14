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
import spring.turbo.util.Asserts;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;

/**
 * @author 应卓
 * @since 2.0.8
 */
final class RichResourceImpl implements RichResource {

    private final Resource delegating;

    public RichResourceImpl(Resource delegating) {
        Asserts.notNull(delegating, "delegating is null");
        this.delegating = delegating;
    }

    @Override
    public boolean exists() {
        return delegating.exists();
    }

    @Override
    public URL getURL() throws IOException {
        return delegating.getURL();
    }

    @Override
    public URI getURI() throws IOException {
        return delegating.getURI();
    }

    @Override
    public File getFile() throws IOException {
        return delegating.getFile();
    }

    @Override
    public long contentLength() throws IOException {
        return delegating.contentLength();
    }

    @Override
    public long lastModified() throws IOException {
        return delegating.lastModified();
    }

    @Override
    public Resource createRelative(String relativePath) throws IOException {
        return delegating.createRelative(relativePath);
    }

    @Override
    public String getFilename() {
        return delegating.getFilename();
    }

    @Override
    public String getDescription() {
        return delegating.getDescription();
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return delegating.getInputStream();
    }

    @Override
    public boolean isReadable() {
        return delegating.isReadable();
    }

    @Override
    public boolean isOpen() {
        return delegating.isOpen();
    }

    @Override
    public boolean isFile() {
        return delegating.isFile();
    }

    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        return delegating.readableChannel();
    }

    @Override
    public void close() throws IOException {
        getInputStream().close();
    }

    @Override
    public String toString() {
        return delegating.toString();
    }

}
