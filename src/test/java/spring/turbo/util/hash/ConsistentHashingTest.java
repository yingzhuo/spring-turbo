package spring.turbo.util.hash;

import org.junit.jupiter.api.Test;
import spring.turbo.util.UUIDUtils;

public class ConsistentHashingTest {

    @Test
    public void test() {
        var hashing = new ConsistentHashing(8, new DigestHashFunction(DigestHashFunction.Algorithm.MD5))
                .addNode("10.211.55.3")
                .addNode("10.211.55.4")
                .addNode("10.211.55.5");

        for (int i = 0; i < 100; i++) {
            System.out.println(hashing.getNode(UUIDUtils.uuid32()));
        }
    }

}
