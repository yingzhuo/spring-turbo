/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.token;

import spring.turbo.lang.Immutable;
import spring.turbo.util.Asserts;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 应卓
 * @see BasicTokenResolver
 * @since 1.2.3
 */
@Immutable
public class BasicToken implements Token, Serializable {

    private final String username;
    private final String password;
    private final String string;

    public BasicToken(String stringValue, String username, String password) {
        Asserts.hasText(stringValue);
        Asserts.hasText(username);
        Asserts.hasText(password);
        this.username = username;
        this.password = password;
        this.string = stringValue;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String asString() {
        return string;
    }

    @Override
    public String toString() {
        return string;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BasicToken that = (BasicToken) o;
        return string.equals(that.string);
    }

    @Override
    public int hashCode() {
        return Objects.hash(string);
    }

}
