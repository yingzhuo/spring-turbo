package spring.turbo.util.crypto;

import java.security.SecureRandom;
import java.util.Random;

/**
 * 内部工具
 *
 * @author 应卓
 * @since 3.2.6
 */
final class B64 {

    static final String B64T_STRING = "./0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    static final char[] B64T_ARRAY = B64T_STRING.toCharArray();

    /**
     * 私有构造方法
     */
    private B64() {
    }

    static void b64from24bit(final byte b2, final byte b1, final byte b0, final int outLen,
                             final StringBuilder buffer) {
        // The bit masking is necessary because the JVM byte type is signed!
        int w = ((b2 << 16) & 0x00ffffff) | ((b1 << 8) & 0x00ffff) | (b0 & 0xff);
        // It's effectively a "for" loop but kept to resemble the original C code.
        int n = outLen;
        while (n-- > 0) {
            buffer.append(B64T_ARRAY[w & 0x3f]);
            w >>= 6;
        }
    }

    static String getRandomSalt(final int num) {
        return getRandomSalt(num, new SecureRandom());
    }

    static String getRandomSalt(final int num, final Random random) {
        final StringBuilder saltString = new StringBuilder(num);
        for (int i = 1; i <= num; i++) {
            saltString.append(B64T_STRING.charAt(random.nextInt(B64T_STRING.length())));
        }
        return saltString.toString();
    }
}
