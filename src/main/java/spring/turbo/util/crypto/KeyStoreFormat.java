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
 * {@link java.security.KeyStore}格式类型，本程序库只支持以下两种。
 *
 * <ul>
 *     <li>PKCS12 (推荐)</li>
 *     <li>JKS</li>
 * </ul>
 *
 * @author 应卓
 * @since 3.3.1
 */
public enum KeyStoreFormat {

    /**
     * PKCS#12格式, Java9以后此格式为默认。 推荐此格式，文件扩展名为 p12或pfx。
     */
    PKCS12("pkcs12"),

    /**
     * JKS，Java8及以前使用的格式。 文件扩展名为jks。
     */
    JKS("JKS");

    /**
     * 字符串类型值
     */
    private final String value;

    /**
     * 私有构造方法
     *
     * @param value 字符串类型值
     */
    KeyStoreFormat(String value) {
        this.value = value;
    }

    /**
     * 获取默认的格式
     *
     * @return 默认格式
     */
    public static KeyStoreFormat getDefault() {
        return PKCS12;
    }

    public String getValue() {
        return value;
    }

}
