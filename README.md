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
    @DisplayName("AES加密")
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

##### 1.2 AES签名算法工具

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