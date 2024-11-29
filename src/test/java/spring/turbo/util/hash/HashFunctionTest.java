package spring.turbo.util.hash;

import org.junit.jupiter.api.Test;
import spring.turbo.util.UUIDUtils;

import java.util.stream.IntStream;

public class HashFunctionTest {

    @Test
    public void test1() {
        var func = new DigestHashFunction(DigestHashFunction.Algorithm.MD5);

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDUtils.uuid36()));
                });
    }

    @Test
    public void test2() {
        var func = new DigestHashFunction(DigestHashFunction.Algorithm.SHA1);

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDUtils.uuid36()));
                });
    }

    @Test
    public void test3() {
        var func = new DigestHashFunction(DigestHashFunction.Algorithm.SHA256);

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDUtils.uuid36()));
                });
    }

    @Test
    public void test4() {
        var func = new DigestHashFunction(DigestHashFunction.Algorithm.SHA384);

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDUtils.uuid36()));
                });
    }

    @Test
    public void test5() {
        var func = new DigestHashFunction(DigestHashFunction.Algorithm.SHA512);

        IntStream.rangeClosed(1, 1000)
                .forEach(__ -> {
                    System.out.println(func.apply(UUIDUtils.uuid36()));
                });
    }

}
