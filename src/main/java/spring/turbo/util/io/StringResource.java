package spring.turbo.util.io;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.lang.Nullable;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

/**
 * 文本类型 {@link org.springframework.core.io.Resource}
 *
 * @author 应卓
 * @see org.springframework.core.io.Resource
 * @see StringResource
 * @since 1.1.0
 */
public class StringResource extends ByteArrayResource {

    private final String string;

    public StringResource(String source) {
        this(source, null);
    }

    public StringResource(String source, @Nullable String description) {
        super(source.getBytes(StandardCharsets.UTF_8), description);
        this.string = source;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object res) {
        if (!(res instanceof StringResource)) {
            return false;
        }
        return Objects.equals(this.string, ((StringResource) res).string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(string);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return string;
    }

    public final String getString() {
        return string;
    }

}
