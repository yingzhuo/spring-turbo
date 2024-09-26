package spring.turbo.util.crypto.pem;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class PemUtilsTest {

    private final Resource certResource = new ClassPathResource("jwt-cert-x509.pem");
    private final Resource keyResource = new ClassPathResource("jwt-privatekey-pkcs8.pem");

    @Test
    void test1() throws IOException {
        var cert = PemUtils.readX509Certificate(certResource.getContentAsString(StandardCharsets.UTF_8));
        var key = PemUtils.readPkcs8PrivateKey(
                keyResource.getContentAsString(StandardCharsets.UTF_8),
                "123456"
        );

        Assertions.assertNotNull(cert);
        Assertions.assertNotNull(key);
    }

    @Test
    void test2() throws IOException {
        var content = PemUtils.trimPemContent(certResource.getContentAsString(StandardCharsets.UTF_8));
        Assertions.assertNotNull(content);
    }

}
