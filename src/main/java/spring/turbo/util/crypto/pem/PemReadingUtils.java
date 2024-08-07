package spring.turbo.util.crypto.pem;

import org.springframework.boot.ssl.pem.PemContent;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.security.Key;
import java.security.PrivateKey;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * PEM相关工具 <br>
 *
 * @author 应卓
 * @see <a href="https://www.openssl.org/">OpenSSL官方文档</a>
 * @see <a href="https://en.wikipedia.org/wiki/X.509">X509 wiki</a>
 * @see <a href="https://en.wikipedia.org/wiki/PKCS_8">PKCS#8 wiki</a>
 * @since 3.3.1
 */
@SuppressWarnings("unchecked")
public final class PemReadingUtils {

    /**
     * 私有构造方法
     */
    private PemReadingUtils() {
    }

    /**
     * 读取证书 <br>
     * <em>注意: 必须是X509格式</em>
     *
     * @param text PEM文件内容
     * @return 证书
     */
    public static X509Certificate readX509Certificate(String text) {
        Assert.hasText(text, "text is null or blank");

        text = trimContent(text);
        var pem = PemContent.of(text);
        var certs = pem.getCertificates();
        if (certs.size() == 1) {
            return certs.get(0);
        }

        throw new IllegalStateException("cannot read a certificate chain");
    }

    /**
     * 读取证书链
     *
     * @param text PEM文件内容
     * @return 证书
     */
    public static List<X509Certificate> readX509Certificates(String text) {
        Assert.hasText(text, "text is null or blank");

        text = trimContent(text);
        var pem = PemContent.of(text);
        return new ArrayList<>(pem.getCertificates());
    }

    /**
     * 读取私钥 <br>
     * <em>注意: 必须是PKCS8格式</em>
     *
     * @param text PEM文件内容
     * @param <T>  私钥类型泛型
     * @return 私钥
     */
    public static <T extends PrivateKey> T readPkcs8PrivateKey(String text) {
        return readPkcs8PrivateKey(text, null);
    }

    /**
     * 读取私钥 <br>
     * <em>注意: 必须是PKCS8格式</em>
     *
     * @param text     PEM文件内容
     * @param password 私钥密码
     * @param <T>      私钥类型泛型
     * @return 私钥
     */
    public static <T extends PrivateKey> T readPkcs8PrivateKey(String text, @Nullable String password) {
        return (T) readPkcs8Key(text, password);
    }

    /**
     * 读取秘钥 <br>
     * <em>注意: 必须是PKCS8格式</em>
     *
     * @param text PEM文件内容
     * @param <T>  秘钥类型泛型
     * @return 秘钥
     */
    public static <T extends Key> T readPkcs8Key(String text) {
        return readPkcs8Key(text, null);
    }

    /**
     * 读取秘钥 <br>
     * <em>注意: 必须是PKCS8格式</em>
     *
     * @param text     PEM文件内容
     * @param password 秘钥，没有秘钥可以为{@code null}
     * @param <T>      秘钥类型泛型
     * @return 秘钥
     */
    public static <T extends Key> T readPkcs8Key(String text, @Nullable String password) {
        Assert.hasText(text, "text is null or blank");

        text = trimContent(text);
        var pem = PemContent.of(text);
        return (T) pem.getPrivateKey(password);
    }

    /**
     * 整理PEM文本内容，去除不必要的白字符
     *
     * @param text PEM文件内容
     * @return 整理后的内容
     */
    public static String trimContent(String text) {
        return Arrays.stream(StringUtils.delimitedListToStringArray(text, "\n"))
                .map(String::trim)
                .filter(StringUtils::hasText)
                .collect(Collectors.joining("\n"))
                .trim();
    }

}
