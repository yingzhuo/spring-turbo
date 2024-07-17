package spring.turbo.util.crypto.pem;

import org.springframework.boot.ssl.pem.PemContent;
import org.springframework.core.io.Resource;
import org.springframework.lang.Nullable;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.cert.X509Certificate;

/**
 * PEM相关工具 <br>
 *
 * @author 应卓
 * @see <a href="https://github.com/yingzhuo/spring-turbo/wiki/2024%E2%80%9007%E2%80%9002%E2%80%90openssl%E2%80%90cheatsheet">2024‐07‐02‐openssl‐cheatsheet</a>
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
     * @param resource 资源
     * @return 证书
     * @throws UncheckedIOException IO错误
     */
    public static <T extends X509Certificate> T readX509PemCertificate(Resource resource) {
        try {
            var text = resource.getContentAsString(StandardCharsets.UTF_8);
            var pem = PemContent.of(text);
            var certs = pem.getCertificates();
            if (certs.size() == 1) {
                return (T) certs.get(0);
            } else {
                throw new IllegalStateException("cannot read a certificate chain");
            }
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 读取秘钥 <br>
     * <em>注意: 必须是PKCS8格式</em>
     *
     * @param resource 资源
     * @param <T>      秘钥类型泛型
     * @return 秘钥
     */
    public static <T extends Key> T readPkcs8Key(Resource resource) {
        return readPkcs8Key(resource, null);
    }

    /**
     * 读取秘钥 <br>
     * <em>注意: 必须是PKCS8格式</em>
     *
     * @param resource 资源
     * @param password 秘钥，没有秘钥可以为{@code null}
     * @param <T>      秘钥类型泛型
     * @return 秘钥
     */
    public static <T extends Key> T readPkcs8Key(Resource resource, @Nullable String password) {
        try {
            var text = resource.getContentAsString(StandardCharsets.UTF_8);
            var pem = PemContent.of(text);

            return (T) (password != null ?
                    pem.getPrivateKey(password) :
                    pem.getPrivateKey());

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
