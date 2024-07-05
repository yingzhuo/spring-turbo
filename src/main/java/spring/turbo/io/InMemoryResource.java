package spring.turbo.io;

import org.springframework.core.io.AbstractResource;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Arrays;

/**
 * 内存型Resource
 *
 * @author 应卓
 * @see Resource
 * @since 1.1.0
 */
public class InMemoryResource extends AbstractResource implements Resource {

    private final byte[] source;

    @Nullable
    private final String description;

    public InMemoryResource(byte[] source) {
        this(source, null);
    }

    public InMemoryResource(byte[] source, @Nullable String description) {
        Assert.notNull(source, "source cannot be null");
        this.source = source;
        this.description = description;
    }

    @Override
    @Nullable
    public String getDescription() {
        return this.description;
    }

    @Override
    public InputStream getInputStream() {
        return new ByteArrayInputStream(this.source);
    }

    @Override
    public boolean equals(Object res) {
        if (!(res instanceof InMemoryResource)) {
            return false;
        }
        return Arrays.equals(this.source, ((InMemoryResource) res).source);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(this.source);
    }

}
