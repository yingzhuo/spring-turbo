package spring.turbo.util;

import org.junit.jupiter.api.Test;

public class UUIDGeneratorsTestCase {

    @Test
    public void test1() {
        for (int i = 0; i < 1000; i++) {
            System.out.println(
                    UUIDGenerators.timeBased()
            );
        }
    }

}
