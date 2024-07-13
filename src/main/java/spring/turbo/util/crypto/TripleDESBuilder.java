package spring.turbo.util.crypto;

import org.springframework.lang.Nullable;

/**
 * @author 应卓
 * @since 3.2.6
 */
@Deprecated(since = "3.3.1")
public final class TripleDESBuilder {

    @Nullable
    private String password;

    @Nullable
    private String salt;

    TripleDESBuilder() {
    }

    public TripleDESBuilder passwordAndSalt(String password, String salt) {
//        Asserts.hasText(password);
//        Asserts.isTrue(password.getBytes().length == 24);
        this.password = password;

//        Asserts.hasText(salt);
//        Asserts.isTrue(salt.getBytes().length == 8);
        this.salt = salt;

        return this;
    }

    public TripleDES build() {
        // double check
//        Asserts.notNull(password);
//        Asserts.hasText(password);
//        Asserts.isTrue(password.getBytes().length == 24);
//        Asserts.notNull(salt);
//        Asserts.hasText(salt);
//        Asserts.isTrue(salt.getBytes().length == 8);
        return new TripleDESImpl(password, salt);
    }

}
