package spring.turbo.util.io;

import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URI;
import java.net.URL;
import java.nio.channels.ReadableByteChannel;

/**
 * {@link RichResource}的实现
 *
 * @param delegating 代理的{@link Resource}实例
 * @author 应卓
 * @since 2.0.8
 */
record RichResourceImpl(Resource delegating) implements RichResource, Resource, Serializable {

    /**
     * 默认构造方法
     *
     * @param delegating 代理的对象
     */
    public RichResourceImpl {
        Assert.notNull(delegating, "delegating is null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean exists() {
        return delegating.exists();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URL getURL() throws IOException {
        return delegating.getURL();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public URI getURI() throws IOException {
        return delegating.getURI();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public File getFile() throws IOException {
        return delegating.getFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long contentLength() throws IOException {
        return delegating.contentLength();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public long lastModified() throws IOException {
        return delegating.lastModified();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Resource createRelative(String relativePath) throws IOException {
        return delegating.createRelative(relativePath);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getFilename() {
        return delegating.getFilename();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getDescription() {
        return delegating.getDescription();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public InputStream getInputStream() throws IOException {
        return delegating.getInputStream();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isReadable() {
        return delegating.isReadable();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isOpen() {
        return delegating.isOpen();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isFile() {
        return delegating.isFile();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ReadableByteChannel readableChannel() throws IOException {
        return delegating.readableChannel();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return delegating.toString();
    }

}
