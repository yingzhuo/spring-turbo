package spring.turbo.io;

import java.util.Objects;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * String型Resource
 *
 * @author 应卓
 * @see org.springframework.core.io.Resource
 * @see StringResource
 * @since 1.1.0
 */
public class StringResource extends InMemoryResource {

    private final String string;

    public StringResource(String source) {
        super(source.getBytes(UTF_8));
        this.string = source;
    }

    @Override
    public boolean equals(Object res) {
        if (!(res instanceof StringResource)) {
            return false;
        }
        return Objects.equals(this.string, ((StringResource) res).string);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(string);
    }

}
