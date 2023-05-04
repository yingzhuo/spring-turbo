/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 应卓
 *
 * @since 1.0.13
 */
public final class MD5Crypt {

    private static final String APR1_PREFIX = "$apr1$";
    private static final int BLOCKSIZE = 16;
    private static final String MD5_PREFIX = "$1$";
    private static final int ROUNDS = 1000;

    /**
     * 私有构造方法
     */
    private MD5Crypt() {
        super();
    }

    public static String apr1Crypt(final byte[] keyBytes) {
        return apr1Crypt(keyBytes, APR1_PREFIX + B64.getRandomSalt(8));
    }

    public static String apr1Crypt(final byte[] keyBytes, final Random random) {
        return apr1Crypt(keyBytes, APR1_PREFIX + B64.getRandomSalt(8, random));
    }

    public static String apr1Crypt(final byte[] keyBytes, String salt) {
        // to make the md5Crypt regex happy
        if (salt != null && !salt.startsWith(APR1_PREFIX)) {
            salt = APR1_PREFIX + salt;
        }
        return MD5Crypt.md5Crypt(keyBytes, salt, APR1_PREFIX);
    }

    public static String apr1Crypt(final String keyBytes) {
        return apr1Crypt(keyBytes.getBytes(StandardCharsets.UTF_8));
    }

    public static String apr1Crypt(final String keyBytes, final String salt) {
        return apr1Crypt(keyBytes.getBytes(StandardCharsets.UTF_8), salt);
    }

    public static String md5Crypt(final byte[] keyBytes) {
        return md5Crypt(keyBytes, MD5_PREFIX + B64.getRandomSalt(8));
    }

    public static String md5Crypt(final byte[] keyBytes, final Random random) {
        return md5Crypt(keyBytes, MD5_PREFIX + B64.getRandomSalt(8, random));
    }

    public static String md5Crypt(final byte[] keyBytes, final String salt) {
        return md5Crypt(keyBytes, salt, MD5_PREFIX);
    }

    public static String md5Crypt(final byte[] keyBytes, final String salt, final String prefix) {
        return md5Crypt(keyBytes, salt, prefix, new SecureRandom());
    }

    public static String md5Crypt(final byte[] keyBytes, final String salt, final String prefix, final Random random) {
        final int keyLen = keyBytes.length;

        // Extract the real salt from the given string which can be a complete hash string.
        String saltString;
        if (salt == null) {
            saltString = B64.getRandomSalt(8, random);
        } else {
            final Pattern p = Pattern.compile("^" + prefix.replace("$", "\\$") + "([\\.\\/a-zA-Z0-9]{1,8}).*");
            final Matcher m = p.matcher(salt);
            if (!m.find()) {
                throw new IllegalArgumentException("Invalid salt value: " + salt);
            }
            saltString = m.group(1);
        }
        final byte[] saltBytes = saltString.getBytes(StandardCharsets.UTF_8);

        final MessageDigest ctx = getMd5Digest();

        /*
         * The password first, since that is what is most unknown
         */
        ctx.update(keyBytes);

        /*
         * Then our magic string
         */
        ctx.update(prefix.getBytes(StandardCharsets.UTF_8));

        /*
         * Then the raw salt
         */
        ctx.update(saltBytes);

        /*
         * Then just as many characters of the MD5(pw,salt,pw)
         */
        MessageDigest ctx1 = getMd5Digest();
        ctx1.update(keyBytes);
        ctx1.update(saltBytes);
        ctx1.update(keyBytes);
        byte[] finalb = ctx1.digest();
        int ii = keyLen;
        while (ii > 0) {
            ctx.update(finalb, 0, ii > 16 ? 16 : ii);
            ii -= 16;
        }

        /*
         * Don't leave anything around in vm they could use.
         */
        Arrays.fill(finalb, (byte) 0);

        /*
         * Then something really weird...
         */
        ii = keyLen;
        final int j = 0;
        while (ii > 0) {
            if ((ii & 1) == 1) {
                ctx.update(finalb[j]);
            } else {
                ctx.update(keyBytes[j]);
            }
            ii >>= 1;
        }

        /*
         * Now make the output string
         */
        final StringBuilder passwd = new StringBuilder(prefix + saltString + "$");
        finalb = ctx.digest();

        /*
         * and now, just to make sure things don't run too fast On a 60 Mhz Pentium this takes 34 msec, so you would
         * need 30 seconds to build a 1000 entry dictionary...
         */
        for (int i = 0; i < ROUNDS; i++) {
            ctx1 = getMd5Digest();
            if ((i & 1) != 0) {
                ctx1.update(keyBytes);
            } else {
                ctx1.update(finalb, 0, BLOCKSIZE);
            }

            if (i % 3 != 0) {
                ctx1.update(saltBytes);
            }

            if (i % 7 != 0) {
                ctx1.update(keyBytes);
            }

            if ((i & 1) != 0) {
                ctx1.update(finalb, 0, BLOCKSIZE);
            } else {
                ctx1.update(keyBytes);
            }
            finalb = ctx1.digest();
        }

        // The following was nearly identical to the Sha2Crypt code.
        // Again, the buflen is not really needed.
        // int buflen = MD5_PREFIX.length() - 1 + salt_string.length() + 1 + BLOCKSIZE + 1;
        B64.b64from24bit(finalb[0], finalb[6], finalb[12], 4, passwd);
        B64.b64from24bit(finalb[1], finalb[7], finalb[13], 4, passwd);
        B64.b64from24bit(finalb[2], finalb[8], finalb[14], 4, passwd);
        B64.b64from24bit(finalb[3], finalb[9], finalb[15], 4, passwd);
        B64.b64from24bit(finalb[4], finalb[10], finalb[5], 4, passwd);
        B64.b64from24bit((byte) 0, (byte) 0, finalb[11], 2, passwd);

        /*
         * Don't leave anything around in vm they could use.
         */
        // Is there a better way to do this with the JVM?
        ctx.reset();
        ctx1.reset();
        Arrays.fill(keyBytes, (byte) 0);
        Arrays.fill(saltBytes, (byte) 0);
        Arrays.fill(finalb, (byte) 0);

        return passwd.toString();
    }

    private static MessageDigest getMd5Digest() {
        try {
            return MessageDigest.getInstance("MD5");
        } catch (final NoSuchAlgorithmException e) {
            throw new IllegalArgumentException(e); // 实际不可能发生
        }
    }

}
