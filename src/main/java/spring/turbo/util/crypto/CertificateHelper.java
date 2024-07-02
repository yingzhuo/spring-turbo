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

import java.io.InputStream;
import java.security.cert.Certificate;
import java.security.cert.CertificateFactory;

/**
 * 电子证书工具
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class CertificateHelper {

    /**
     * 私有构造方法
     */
    private CertificateHelper() {
        super();
    }

    /**
     * 获得数字证书
     *
     * @param inputStream 流
     * @return 证书
     */
    public static Certificate loadCertificate(InputStream inputStream) {
        Asserts.notNull(inputStream, "inputStream is required");

        try {
            var factory = CertificateFactory.getInstance("x.509");
            return factory.generateCertificate(inputStream);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
    }

}
