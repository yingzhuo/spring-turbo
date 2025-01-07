package spring.turbo.util.io;

import org.junit.jupiter.api.Test;

import java.net.URL;
import java.util.function.Predicate;

public class ManifestUtilsTestCase {

    @Test
    public void test1() {
        Predicate<URL> p = url -> url.toString().matches("^jar:file:.*json-path.*$");
        var man = ManifestUtils.getManifest(p);

        System.out.println(man.getAttributes("Bundle-Name"));
    }

}
