package spring.turbo.util.io;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;

/**
 * {@link RichResource}的实现
 *
 * @author 应卓
 * @since 2.0.8
 */
public class RichResourceImpl implements RichResource {

    private Resource delegating;

    public RichResourceImpl(Resource delegating) {
        Assert.notNull(delegating, "delegating is null");
        this.delegating = delegating;
    }

    @Override
    public Resource getDelegating() {
        return this.delegating;
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
