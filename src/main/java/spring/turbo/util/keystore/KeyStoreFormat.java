/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.keystore;

import java.io.Serializable;

/**
 * KeyStore格式类型
 *
 * @author 应卓
 * @since 3.3.0
 */
public enum KeyStoreFormat implements Serializable {

    /**
     * PKCS#12格式, Java9以后此格式为默认。 推荐此格式，文件扩展名为 p12或pfx。
     */
    PKCS12("pkcs12"),

    /**
     * JKS，Java8及以前使用的格式。 文件扩展名为jks。
     */
    JKS("JKS");

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // 其他格式不支持
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * 获取默认的格式
     *
     * @return 默认格式
     */
    public static KeyStoreFormat getDefault() {
        return PKCS12;
    }

    /**
     * 字符串类型值
     */
    private final String value;

    /**
     * 私有构造方法
     *
     * @param value 字符串类型值
     */
    private KeyStoreFormat(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
