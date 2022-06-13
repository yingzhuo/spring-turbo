/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import java.util.Objects;

/**
 * String型Resource
 *
 * @author 应卓
 * @see org.springframework.core.io.Resource
 * @since 1.1.0
 */
public class StringResource extends InMemoryResource {

    private final String string;

    public StringResource(String source) {
        super(source);
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
        return 1;
    }

}
