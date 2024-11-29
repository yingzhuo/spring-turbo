package spring.turbo.util.hash;

import org.junit.jupiter.api.Test;
import spring.turbo.util.UUIDUtils;

import java.util.stream.IntStream;

public class HashFunctionTest {

    @Test
    public void test1() {
        var func = DigestHashFunctions.md5();

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDUtils.uuid36()));
                });
    }

    @Test
    public void test2() {
        var func = DigestHashFunctions.sha1();

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDUtils.uuid36()));
                });
    }

    @Test
    public void test3() {
        var func = DigestHashFunctions.sha256();

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDUtils.uuid36()));
                });
    }

    @Test
    public void test4() {
        var func = DigestHashFunctions.sha384();

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDUtils.uuid36()));
                });
    }

    @Test
    public void test5() {
        var func = DigestHashFunctions.sha512();

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDUtils.uuid36()));
                });
    }

}
