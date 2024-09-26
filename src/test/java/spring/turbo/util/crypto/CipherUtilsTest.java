package spring.turbo.util.crypto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import spring.turbo.core.ResourceUtils;
import spring.turbo.util.crypto.pem.PemUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.PrivateKey;
import java.security.PublicKey;

public class CipherUtilsTest {

    private PublicKey publicKey;
    private PrivateKey privateKey;

    @BeforeEach
    void init() throws IOException {
        var certResource = ResourceUtils.loadResource("classpath:/jwt-cert-x509.pem");
        var privateResource = ResourceUtils.loadResource("classpath:/jwt-privatekey-pkcs8.pem");

        this.publicKey = PemUtils.readX509Certificate(certResource.getContentAsString(StandardCharsets.UTF_8)).getPublicKey();
        this.privateKey = PemUtils.readPkcs8PrivateKey(privateResource.getContentAsString(StandardCharsets.UTF_8), "123456");
    }

    @Test
    void test1() {
        var rawData = """
                你好，世界。
                这是一个测试用的文本
                """;

        var ed = CipherUtils.encrypt(rawData.getBytes(StandardCharsets.UTF_8), privateKey);

        var ns = new String(CipherUtils.decrypt(ed, publicKey), StandardCharsets.UTF_8);

        Assertions.assertEquals(rawData, ns);
    }

}
