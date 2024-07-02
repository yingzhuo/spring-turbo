/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

/**
 * AES加密解密工具
 *
 * @author 应卓
 * @see #builder()
 * @see AESUtils
 * @since 3.2.6
 */
@Deprecated
public interface AES {

    public static AESBuilder builder() {
        return new AESBuilder();
    }

    public String encrypt(String input);

    public String decrypt(String cipherText);

    public enum Mode {

        /**
         * Cipher Block Chaining (推荐)
         */
        CBC("AES/CBC/PKCS5Padding"),

        /**
         * Electronic Code Book
         */
        ECB("AES/ECB/PKCS5Padding"),

        /**
         * Cipher FeedBack
         */
        CFB("AES/CFB/PKCS5Padding"),

        /**
         * Output FeedBack
         */
        OFB("AES/OFB/PKCS5Padding"),

        /**
         * Count
         */
        CTR("AES/CTR/PKCS5Padding");

        private final String algorithm;

        private Mode(String algorithm) {
            this.algorithm = algorithm;
        }

        public String getAlgorithm() {
            return algorithm;
        }
    }

}
