package spring.turbo.core;

import java.util.List;
import java.util.Set;

import static spring.turbo.core.SpringUtils.getApplicationArguments;

/**
 * {@link org.springframework.boot.ApplicationArguments} 相关工具
 *
 * @author 应卓
 * @see SpringUtils
 * @see org.springframework.boot.ApplicationArguments
 * @since 3.0.7
 */
public final class ApplicationArgumentsUtils {

    /**
     * 私有构造方法
     */
    private ApplicationArgumentsUtils() {
    }

    public static Set<String> getOptionNames() {
        return getApplicationArguments().getOptionNames();
    }

    public static boolean containsOption(String name) {
        return getApplicationArguments().containsOption(name);
    }

    public static List<String> getOptionValues(String name) {
        return getApplicationArguments().getOptionValues(name);
    }

    public static List<String> getNonOptionArgs() {
        return getApplicationArguments().getNonOptionArgs();
    }

}
