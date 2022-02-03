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
    @DisplayName("使用AESj加密算法")
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

##### 1.4 RSA签名算法工具

```java
public class RSATestCases {

    @Test
    @DisplayName("生成RSA加密秘钥")
    public void test1() {
        RSAKeys keys = RSAKeys.create(RSAKeys.KEY_SIZE_2048);
        System.out.println(keys.getBase64PublicKey());
        System.out.println(keys.getBase64PrivateKey());
    }

    @Test
    @DisplayName("使用RSA签名算法")
    public void test2() {
        RSAKeys keys = RSAKeys.fromString(
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAr8wgjhMPTXZNx07DrCl2KbF5QEdbwcBOC2wwGNi98FKYUw3HzsvoJVZhY041-rrcBM50mnSyv59NVqsJeX4D9dcVr9f3yDv6vWMNINWqy_g4oyx36KwQvQZHuuTbxBXIvF0etBAgmmRh8lqQ_glFKnYY5C0hH-oE1kQYDFexaEizLwRK4-lGqQxz2PfW9MieMS7Y1O903izmbi4-amVW-0jtY6rH3C4gfDmwlQlc-Y2sc6WGQ5xD5qzGRal4cwqEuwnUFqo-jBFrUIifqKztBMSkDfnwMA2bBKdnTrcO40HFLwxnT0Ab9hHMEWDWjQ8WACJ3gv-28_wiUp6IwoCboQIDAQAB",
                "MIIEvwIBADANBgkqhkiG9w0BAQEFAASCBKkwggSlAgEAAoIBAQCvzCCOEw9Ndk3HTsOsKXYpsXlAR1vBwE4LbDAY2L3wUphTDcfOy-glVmFjTjX6utwEznSadLK_n01Wqwl5fgP11xWv1_fIO_q9Yw0g1arL-DijLHforBC9Bke65NvEFci8XR60ECCaZGHyWpD-CUUqdhjkLSEf6gTWRBgMV7FoSLMvBErj6UapDHPY99b0yJ4xLtjU73TeLOZuLj5qZVb7SO1jqsfcLiB8ObCVCVz5jaxzpYZDnEPmrMZFqXhzCoS7CdQWqj6MEWtQiJ-orO0ExKQN-fAwDZsEp2dOtw7jQcUvDGdPQBv2EcwRYNaNDxYAIneC_7bz_CJSnojCgJuhAgMBAAECggEAUvITYjWoALqTFDkbpf2iHSX5udRxfMeJi2Yug2qwo9W0vWtBHVekXwmUxdA_M-A6s6Hd7oqlVDiLuxrUS5ijsbkcKG3UIYv7UKSNqzJldfvWbVcr9IJLG4T9DzewYaKNpiKhu__oAjDe7gTBaLtjARfbwr1wZah7Q8W5OwmdUUBAkLalXalX1PFOKNPv98xERDVMGMl52Hr6bBsMVtBKpSMgNsByUI2bfX_PFeZq51IeKBH6RNd1OmelQX1k5pMCtSPE2w3pPlzkQ63A8RyCmad0-8pth7DaSrIHwLDMKIfXKTWPgDe4_ZOyuXpDJSkR_3HytcOtG87HRV6zv2CAKQKBgQD-qjzVD60wMiLQsw3l8xUrh8qGEop3Opdg4GQO8jjc4d8wZddhgoz3CJ4cXQUFpQBN-UqaP_yuxVW3CD6b8LrGwxNRNt1Dz6jKueAc1NTuZHaK7NDuI4WzSLQKp9yKx-g0c6EGBsQv5SA5WZbjN1h73DLTS2TzTbzr1if3M4O5ZwKBgQCwuAxwenPXjyCVXvRh_8t9QTbA82R9ImSzrMYXi2iejDXJB7BSUs7xoElU4z7EVRcVo4AKrCJfOB3J-CJePVUc3CzN5Ah8VN8oycWhjTZ1jzeKikHiKFyB5BWEVbdB6hLqczi1EUDzKKHUC5RlwQGIeAzU4gfbNyUkVm4dZ611twKBgQDJZO--vysDVmLaEQAIvfgb-MNczATczWUxo8L9XulBJQ9QQvnVj1zTb-k6HQX_tMFM6Lzqx3eY704d9VMuF25eCZ-ZNn49RlgCGoT1yAqsjJ6Ct01zRnxRkElxs9Zhmj40XM0W6vrztYuKTyNYhUhgi39WQeWP1AZ3q2DnycF-OQKBgQCbHsNUjDhZM_qfAz2zNCyz4uWkE0HXEbShfvxXOHj6Cws_Ti9449VhhZg5GkxDXImp5d4EADsfqAuF-Ph6Do5CigYOKKdGM927WPnQqDrwkGlpg5j3YwRoxAQbKH53LneyMrfJQL7pcnYywoNdmkC1QOPKjinlRA-nSPLZfuNlGwKBgQCRWabMxB6ZmA5WTDxKMxqyqAzcXaPLHS6pEpCCEsI6sJs7mNMurhDndNqNhK602WYdxmrUDKYo0eRprJtYph6Jt1V4LOAkhj_5Bg5wq5ZSUpjv1Oc__XtFESLiwPBAeClBl-VC9hxzt1WL5JhcRVoZzXyBkpoWdUUrs316ga5eQg=="
        );

        RSA rsa = RSA.builder()
                .keyPair(keys)
                .build();

        String sign = rsa.sign("要签名的字符串");
        System.out.println(sign);
        System.out.println(rsa.verify("要签名的字符串", sign));
    }

}
```

##### 1.5 DES加密算法

```java
public class DESTestCases {

    @Test
    @DisplayName("DES签名算法")
    public void test() {
        TripleDES des = TripleDES.builder()
                .passwordAndSalt("9mng65v8jf4lxn93nabf981m", "a76nb5h9")
                .build();

        String s = des.encrypt("要加密的文本");
        System.out.println(s);
        System.out.println(des.decrypt(s));
    }

}
```

##### 1.6 3DES加密算法

```java
public class TripleDESTestCases {

    @Test
    @DisplayName("使用3DES加密算法")
    public void test() {
        TripleDES _3des = TripleDES.builder()
                .passwordAndSalt("123456789012345678901224", "12345678")
                .build();

        String result = _3des.encrypt("hello");
        System.out.println(result);
        System.out.println(_3des.decrypt(result));
    }

}
```
