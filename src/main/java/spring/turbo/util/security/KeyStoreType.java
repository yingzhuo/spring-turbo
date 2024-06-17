/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.security;

import java.io.Serializable;

/**
 * KeyStore格式类型
 *
 * @author 应卓
 * @since 3.3.0
 */
public enum KeyStoreType implements Serializable {

    /**
     * PKCS#12 format, this is default format since Java9. also known as pkcs12 or pfx.
     */
    PKCS12("pkcs12"),

    /**
     * Java-specific file format that was the default format for KeyStores until Java 8.
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
    private KeyStoreType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
