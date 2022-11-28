/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import spring.turbo.io.ResourceOption;
import spring.turbo.io.ResourceOptions;
import spring.turbo.util.Asserts;

import static spring.turbo.util.CharsetPool.UTF_8;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class KeysReaders {

    /**
     * 私有构造方法
     */
    private KeysReaders() {
        super();
    }

    public static RSAKeys readRSAKeys(String publicKeyLocation, String privateKeyLocation) {
        return RSAKeys.fromString(
                getText(publicKeyLocation),
                getText(privateKeyLocation)
        );
    }

    public static ECDSAKeys readECDSAKeys(String publicKeyLocation, String privateKeyLocation) {
        return ECDSAKeys.fromString(
                getText(publicKeyLocation),
                getText(privateKeyLocation)
        );
    }

    public static DSAKeys readDSAKeys(String publicKeyLocation, String privateKeyLocation) {
        return DSAKeys.fromString(
                getText(publicKeyLocation),
                getText(privateKeyLocation)
        );
    }

    private static String getText(String location) {
        Asserts.hasText(location);
        ResourceOption option = ResourceOptions.builder()
                .add(location)
                .build();

        Asserts.isTrue(option.isPresent());
        return option.toString(UTF_8);
    }

}
