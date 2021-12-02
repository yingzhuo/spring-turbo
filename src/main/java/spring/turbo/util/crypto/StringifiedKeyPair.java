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
public final class StringifiedKeyPair implements Serializable {

    public static final String DEFAULT_DELIMITER = "<@@>";

    private final String delimiter;
    private final String publicKey;
    private final String privateKey;

    public StringifiedKeyPair(String publicKey, String privateKey) {
        this(DEFAULT_DELIMITER, publicKey, privateKey);
    }

    public StringifiedKeyPair(String delimiter, String publicKey, String privateKey) {
        Asserts.hasText(delimiter);
        Asserts.hasText(publicKey);
        Asserts.hasText(privateKey);
        this.delimiter = delimiter;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StringifiedKeyPair that = (StringifiedKeyPair) o;
        return publicKey.equals(that.publicKey) && privateKey.equals(that.privateKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(publicKey, privateKey);
    }

    @Override
    public String toString() {
        return String.format("%s%s%s", publicKey, delimiter, privateKey);
    }

}
