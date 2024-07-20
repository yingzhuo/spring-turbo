package spring.turbo.core;

import org.springframework.core.env.Profiles;
import org.springframework.util.Assert;

import static spring.turbo.core.SpringUtils.getEnvironment;

/**
 * Profiles相关工具
 *
 * @author 应卓
 * @see SpringUtils
 * @see org.springframework.core.env.Environment
 * @see Profiles
 * @see Profiles#of(String...)
 * @since 1.0.13
 */
public final class ProfilesUtils {

    /**
     * 私有构造方法
     */
    private ProfilesUtils() {
    }

    /**
     * 判断profiles是否被激活
     *
     * @param profiles profiles实例
     * @return 结果
     */
    public static boolean acceptsProfiles(Profiles profiles) {
        Assert.notNull(profiles, "profiles is required");
        return getEnvironment().acceptsProfiles(profiles);
    }

    /**
     * 判断profiles是否被激活
     *
     * @param profiles profiles
     * @return 结果
     */
    public static boolean acceptsProfiles(String... profiles) {
        return acceptsProfiles((Profiles.of(profiles)));
    }

}
