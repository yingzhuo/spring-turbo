package spring.turbo.util.crypto;

import org.springframework.lang.Nullable;

/**
 * {@link java.security.KeyStore}格式类型，本程序库只支持以下两种。<br>
 * 其中 <em>PKCS12</em> 为推荐格式
 *
 * <ul>
 *     <li>PKCS12</li>
 *     <li>JKS</li>
 * </ul>
 *
 * @author 应卓
 * @see KeyStoreFormatEditor
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
     * 尝试转换字符串为本美剧类型
     *
     * @param value 字符串
     * @return 结果，有可能为空值
     */
    @Nullable
    public static KeyStoreFormat fromValue(@Nullable String value) {
        if (value == null) {
            return null;
        }

        if ("pfx".equalsIgnoreCase(value) ||
                "p12".equalsIgnoreCase(value) ||
                "pkcs#12".equalsIgnoreCase(value) ||
                "pkcs12".equalsIgnoreCase(value)) {
            return PKCS12;
        }

        if ("jks".equalsIgnoreCase(value)) {
            return JKS;
        }

        return null;
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
