package spring.turbo;

import org.springframework.boot.SpringBootVersion;

/**
 * @author 应卓
 */
public final class SpringTurboVersion {

    /**
     * 私有构造方法
     */
    private SpringTurboVersion() {
    }

    public static final String VERSION = SpringBootVersion.getVersion();

}
