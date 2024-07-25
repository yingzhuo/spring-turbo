package spring.turbo.util.crypto.store;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import spring.turbo.util.crypto.keystore.KeyStorePemHelper;

public class KeyStorePemHelperTest {

    @Test
    void test() throws Exception {

        var store = KeyStorePemHelper.loadFromPemFiles(
                "classpath:/jwt-cert-x509.pem",
                "classpath:/jwt-privatekey-pkcs8.pem",
                "123456",
                "hell",
                "changeit",
                "changeit"
        );

        var cert = store.getCertificate("hell");
        var key = store.getKey("hell", "changeit".toCharArray());

        Assertions.assertNotNull(cert);
        Assertions.assertNotNull(key);
    }
}
