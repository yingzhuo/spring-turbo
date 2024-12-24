package spring.turbo.util.hash;

import org.junit.jupiter.api.Test;
import spring.turbo.util.UUIDGenerators;
import spring.turbo.util.UUIDUtils;

import java.util.stream.IntStream;

public class HashFunctionTest {

    @Test
    public void test1() {
        var func = DigestHashFunction.md5();

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDGenerators.randomV4(true)));
                });
    }

    @Test
    public void test2() {
        var func = DigestHashFunction.sha1();

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDGenerators.randomV4(true)));
                });
    }

    @Test
    public void test3() {
        var func = DigestHashFunction.sha256();

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDGenerators.randomV4(true)));
                });
    }

    @Test
    public void test4() {
        var func = DigestHashFunction.sha384();

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDGenerators.randomV4(true)));
                });
    }

    @Test
    public void test5() {
        var func = DigestHashFunction.sha512();

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDGenerators.randomV4(true)));
                });
    }

}
