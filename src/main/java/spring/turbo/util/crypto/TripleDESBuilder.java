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

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class TripleDESBuilder {

    private String password;
    private String salt;

    TripleDESBuilder() {
        super();
    }

    public TripleDESBuilder passwordAndSalt(String password, String salt) {
        Asserts.hasText(password);
        Asserts.isTrue(password.getBytes().length == 24);
        this.password = password;

        Asserts.hasText(salt);
        Asserts.isTrue(salt.getBytes().length == 8);
        this.salt = salt;

        return this;
    }

    public TripleDES build() {
        // double check
        Asserts.hasText(password);
        Asserts.isTrue(password.getBytes().length == 24);
        Asserts.hasText(salt);
        Asserts.isTrue(salt.getBytes().length == 8);
        return new TripleDESImpl(password, salt);
    }

}
