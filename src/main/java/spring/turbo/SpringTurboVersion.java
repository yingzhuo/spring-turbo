package spring.turbo;

import static org.springframework.boot.SpringBootVersion.getVersion;

/**
 * 本软件版本号
 *
 * @author 应卓
 * @since 3.1.1
 */
public final class SpringTurboVersion {

    /**
     * 私有构造方法
     */
    private SpringTurboVersion() {
    }

    /**
     * 本软件版本号
     */
    public static final String VERSION = getVersion();

}
