package spring.turbo.util.crypto.pem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

public class PemStringUtilsTest {

    private final Resource cert = new ClassPathResource("jwt-cert-x509.pem");
    private final Resource key = new ClassPathResource("jwt-privatekey-pkcs8.pem");

    // -----------------------------------------------------------------------------------------------------------------

    @Test
    void testTrimContent1() {
        var certContent = PemStringUtils.trimContent(cert);
        Assertions.assertFalse(certContent.isBlank());
    }

    @Test
    void testTrimContent2() {
        var keyContent = PemStringUtils.trimContent(key);
        Assertions.assertFalse(keyContent.isBlank());
    }

}
