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
import spring.turbo.util.HexUtils;

import java.io.Serializable;
import java.security.Key;
import java.util.Base64;

/**
 * 文本化的密钥，简单封装 {@link Key}，使秘钥能够方便保存到数据库或者文本文件。
 *
 * @param <KEY> 秘钥类型泛型
 * @author 应卓
 * @see Key
 * @see #of(Key)
 * @since 3.3.1
 */
public interface TextizedKey<KEY extends Key> extends Serializable {

    /**
     * 转换秘钥实例为 {@link TextizedKey} 实例
     *
     * @param key 秘钥
     * @return {@link TextizedKey} 实例
     */
    public static TextizedKey<Key> of(Key key) {
        return new SimpleTextizedKey(key);
    }

    /**
     * 获取算法名称
     *
     * @return 算法名称
     */
    public String getAlgorithmName();

    /**
     * 获取秘钥
     *
     * @return 秘钥
     */
    public Key getJdkKey();

    /**
     * 获取秘钥的二进制数据
     *
     * @return 秘钥的二进制数据
     */
    public default byte[] getJdkKeyAsBytes() {
        return getJdkKey().getEncoded();
    }

    /**
     * 获取Base64文本化的秘钥
     *
     * @return 文本化的秘钥
     */
    public default String getBase64EncodedKey() {
        var encoder = Base64.getEncoder().withoutPadding();
        return encoder.encodeToString(getJdkKeyAsBytes());
    }

    /**
     * 获取HEX文本化的秘钥
     *
     * @return 文本化的秘钥
     */
    public default String getHexEncodedKey() {
        return HexUtils.encodeToString(getJdkKeyAsBytes());
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static record SimpleTextizedKey(Key key) implements TextizedKey<Key> {

        public SimpleTextizedKey {
            Asserts.notNull(key, "key is required");
        }

        @Override
        public String getAlgorithmName() {
            return key.getAlgorithm();
        }

        @Override
        public Key getJdkKey() {
            return key;
        }
    }

}
