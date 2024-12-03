package spring.turbo.util.concurrent;

import org.junit.jupiter.api.Test;

public class CurrentThreadUtilsTest {

    @Test
    public void test1() {
        for (int i = 0; i < 10; i++) {
            System.out.println(CurrentThreadUtils.getTrait());
        }
    }

}
