/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.util.Asserts;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class PasswordAndSalt implements Serializable {

    public static final String DEFAULT_DELIMITER = "<@@>";

    private final String delimiter;
    private final String password;
    private final String salt;

    public PasswordAndSalt(String password, String salt) {
        this(DEFAULT_DELIMITER, password, salt);
    }

    public PasswordAndSalt(String delimiter, String password, String salt) {
        Asserts.hasText(delimiter);
        Asserts.hasText(password);
        Asserts.hasText(salt);
        this.delimiter = delimiter;
        this.password = password;
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public String getSalt() {
        return salt;
    }

    public String getDelimiter() {
        return delimiter;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PasswordAndSalt that = (PasswordAndSalt) o;
        return password.equals(that.password) && salt.equals(that.salt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(password, salt);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", password, delimiter, salt);
    }

}
