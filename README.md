[![License](http://img.shields.io/badge/License-Apache_2-red.svg?style=flat)](http://www.apache.org/licenses/LICENSE-2.0)
[![JDK](http://img.shields.io/badge/JDK-v8.0-yellow.svg)](http://www.oracle.com/technetwork/java/javase/downloads/index.html)
[![Build](http://img.shields.io/badge/Build-Maven_2-green.svg)](https://maven.apache.org/)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.yingzhuo/spring-turbo.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.yingzhuo%22%20AND%20a:%22spring-turbo%22)

# spring-turbo

### 关联项目:

[spring-turbo-modules](https://github.com/yingzhuo/spring-turbo-modules)

### 使用案例:

[spring-turbo-examples](https://github.com/yingzhuo/spring-turbo-examples)

### 简要说明

#### (1) 加密与数字签名

##### 1.1 AES签名算法工具

```java
public class AESTestCases {

    @Test
    @DisplayName("使用AES签名算法")
    public void test() {
        AES aes = AES.builder()
                .mode(AES.Mode.CBC)
                .passwordAndSalt("hello", "AES")
                .build();

        String ci = aes.encrypt("hello");
        System.out.println(ci);
        System.out.println(aes.decrypt(ci));
    }

}
```

##### 1.2 ECDSA签名算法工具

```java
public class ECDSATestCases {

    @Test
    @DisplayName("生成ECDSA签名秘钥对")
    public void test1() {
        ECDSAKeys keys = ECDSAKeys.create();
        System.out.println(keys.getBase64PublicKey());
        System.out.println(keys.getBase64PrivateKey());
    }

    @Test
    @DisplayName("使用ECDSA签名算法")
    public void test2() {
        ECDSAKeys keys = ECDSAKeys.fromString(
                "MFkwEwYHKoZIzj0CAQYIKoZIzj0DAQcDQgAEhWWn0NglDsxRYwOx7OGTUoZEpJ9Zyz3Ex-rIUXG1J4CdxjyGXyz3VowDY2tRx62E1qk32Iw6ZwtcHFpqUjskOQ==",
                "MEECAQAwEwYHKoZIzj0CAQYIKoZIzj0DAQcEJzAlAgEBBCCsJWvGUTErvJOYxJZZooeOiEbhbqYeyXTRjqNeczb5Yg=="
        );

        ECDSA ecdsa = ECDSA.builder()
                .keyPair(keys)
                .build();

        String sign = ecdsa.sign("要签名的字符串");
        System.out.println(sign);
        System.out.println(ecdsa.verify("要签名的字符串", sign));
    }

}
```

##### 1.3 DSA签名算法工具

```java
public class DSATestCases {

    @Test
    @DisplayName("生成DSA签名秘钥对")
    public void test1() {
        DSAKeys keys = DSAKeys.create(DSAKeys.KEY_SIZE_512);
        System.out.println(keys.getBase64PublicKey());
        System.out.println(keys.getBase64PrivateKey());
    }

    @Test
    @DisplayName("使用DSA签名算法")
    public void test2() {
        DSAKeys keys = DSAKeys.fromString(
                "MIHxMIGoBgcqhkjOOAQBMIGcAkEA_KaCzo4Syrom78z3EQ5SbbB4sF7ey80etKII864WF64B81uRpH5t9jQTxeEu0ImbzRMqzVDZkVG9xD7nN1kuFwIVAJYu3cw2nLqOuyYO5rahJtk0bjjFAkBnhHGyepz0TukaScUUfbGpqvJE8FpDTWSGkx0tFCcbnjUDC3H9c9oXkGmzLik1Yw4cIGI1TQ2iCmxBblC-eUykA0QAAkEAkVYxw2HgLkF6U6p0tIWqG_m-dnSlCe1buNi1fF6myw-cb9Yh9zeFEYVDyap__O_Ha9nZ9gMSOJWVBIl0ZD0Tlw==",
                "MIHHAgEAMIGoBgcqhkjOOAQBMIGcAkEA_KaCzo4Syrom78z3EQ5SbbB4sF7ey80etKII864WF64B81uRpH5t9jQTxeEu0ImbzRMqzVDZkVG9xD7nN1kuFwIVAJYu3cw2nLqOuyYO5rahJtk0bjjFAkBnhHGyepz0TukaScUUfbGpqvJE8FpDTWSGkx0tFCcbnjUDC3H9c9oXkGmzLik1Yw4cIGI1TQ2iCmxBblC-eUykBBcCFQCIpwIhH8FcPo_0fXGVHn127xB-cw=="
        );

        DSA dsa = DSA.builder()
                .keyPair(keys)
                .build();

        String sign = dsa.sign("要签名的字符串");
        System.out.println(sign);
        System.out.println(dsa.verify("要签名的字符串", sign));
    }
}
```
