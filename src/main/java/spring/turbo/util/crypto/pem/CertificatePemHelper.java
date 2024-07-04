/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto.pem;

import org.springframework.core.io.Resource;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * @author 应卓
 * @see java.security.cert.Certificate
 * @see java.security.cert.X509Certificate
 * @since 3.3.1
 */
public final class CertificatePemHelper {

    /**
     * 私有构造方法
     */
    private CertificatePemHelper() {
        super();
    }

    /**
     * 读取证书 <br>
     * <em>注意: 必须是X509格式</em>
     *
     * @param resource 资源
     * @return 证书
     */
    public static <T extends Certificate> T readX509PemCertificate(Resource resource) {
        try {
            return readX509PemCertificate(resource.getInputStream());
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    /**
     * 读取证书 <br>
     * <em>注意: 必须是X509格式</em>
     *
     * @param inputStream 输入流
     * @return 证书
     */
    @SuppressWarnings("unchecked")
    public static <T extends Certificate> T readX509PemCertificate(InputStream inputStream) {
        try {
            var certBytes = PemHelper.readPemBytes(inputStream);
            var certInput = new ByteArrayInputStream(certBytes);
            var factory = CertificateFactory.getInstance("X.509");
            return (T) factory.generateCertificate(certInput);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
