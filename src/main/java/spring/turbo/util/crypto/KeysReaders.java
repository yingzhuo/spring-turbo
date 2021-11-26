/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util.crypto;

import org.springframework.util.Assert;
import spring.turbo.io.ResourceOption;
import spring.turbo.io.ResourceOptions;

import java.nio.charset.StandardCharsets;

/**
 * @author 应卓
 * @since 1.0.0
 */
public final class KeysReaders {

    private KeysReaders() {
        super();
    }

    public static RSAKeys readRSAKeys(String publicKey, String privateKey) {
        return RSAKeys.fromString(
                getText(publicKey),
                getText(privateKey)
        );
    }

    public static ECDSAKeys readECDSAKeys(String publicKey, String privateKey) {
        return ECDSAKeys.fromString(
                getText(publicKey),
                getText(privateKey)
        );
    }

    public static DSAKeys readDSAKeys(String publicKey, String privateKey) {
        return DSAKeys.fromString(
                getText(publicKey),
                getText(privateKey)
        );
    }

    private static String getText(String location) {
        Assert.hasText(location, "location is blank");
        ResourceOption option = ResourceOptions.builder()
                .add(location)
                .build();

        Assert.state(option.isPresent(), "location is not able to open");
        return option.toString(StandardCharsets.UTF_8);
    }

}
