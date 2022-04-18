/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.jks;

import spring.turbo.io.ResourceOption;
import spring.turbo.util.Asserts;

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
    public static Certificate getCertificate(ResourceOption certificateResource) {
        Asserts.notNull(certificateResource);
        try {
            final CertificateFactory factory = CertificateFactory.getInstance("x.509");
            return factory.generateCertificate(certificateResource.toInputStream());
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
    public static PublicKey getPublicKeyFromCertificate(ResourceOption certificateResource) {
        final Certificate cert = getCertificate(certificateResource);
        return cert.getPublicKey();
    }

}
