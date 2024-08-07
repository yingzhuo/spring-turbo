package spring.turbo.util.crypto.pem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PemReadingUtilsTest {

    private final Resource certResource = new ClassPathResource("jwt-cert-x509.pem");
    private final Resource keyResource = new ClassPathResource("jwt-privatekey-pkcs8.pem");

    @Test
    void test1() throws IOException {
        var cert = PemReadingUtils.readX509Certificate(certResource.getContentAsString(StandardCharsets.UTF_8));
        var key = PemReadingUtils.readPkcs8PrivateKey(
                keyResource.getContentAsString(StandardCharsets.UTF_8),
                "123456"
        );

        Assertions.assertNotNull(cert);
        Assertions.assertNotNull(key);
    }

}
