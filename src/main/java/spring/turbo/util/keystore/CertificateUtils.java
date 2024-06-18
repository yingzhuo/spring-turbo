/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.keystore;

import org.springframework.core.io.Resource;
import spring.turbo.io.IOExceptionUtils;
import spring.turbo.util.Asserts;

import java.io.IOException;
import java.security.PublicKey;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * 电子证书工具
 *
 * @author 应卓
 * @see KeyStoreUtils
 * @since 1.0.15
 */
public final class CertificateUtils {

    /**
     * 私有构造方法
     */
    private CertificateUtils() {
        super();
    }

    /**
     * 获得数字证书
     *
     * @param certificateResource 证书文件
     * @return 证书
     */
    public static Certificate getCertificate(Resource certificateResource) {
        Asserts.notNull(certificateResource);
        try {
            final CertificateFactory factory = CertificateFactory.getInstance("x.509");
            return factory.generateCertificate(certificateResource.getInputStream());
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

    /**
     * 获得公钥
     *
     * @param certificateResource 电子证书文件
     * @return 公钥
     */
    public static PublicKey getPublicKeyFromCertificate(Resource certificateResource) {
        final Certificate cert = getCertificate(certificateResource);
        return cert.getPublicKey();
    }

}
