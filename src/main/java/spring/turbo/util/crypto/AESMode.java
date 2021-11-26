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
 * AES模式
 *
 * @author 应卓
 */
public enum AESMode {

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

    /**
     * 构造方法
     *
     * @param algorithm 算法
     */
    AESMode(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getAlgorithm() {
        return algorithm;
    }

}
