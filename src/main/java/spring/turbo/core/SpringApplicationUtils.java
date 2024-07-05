package spring.turbo.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.system.ApplicationHome;
import spring.turbo.util.Asserts;

import java.io.File;

import static spring.turbo.util.collection.CollectionUtils.size;

/**
 * {@link SpringApplication} 相关工具
 *
 * @author 应卓
 * @since 2.1.3
 */
public final class SpringApplicationUtils {

    /**
     * 私有构造方法
     */
    private SpringApplicationUtils() {
    }

    /**
     * 获取SpringBoot应用程序启动目录
     *
     * @param application {@link SpringApplication} 实例
     * @return 启动目录
     */
    public static File getHomeDir(SpringApplication application) {
        Asserts.notNull(application);

        var sourceClasses = application.getAllSources()
                .stream()
                .filter(o -> o instanceof Class<?>)
                .map(o -> (Class<?>) o).toList();

        if (size(sourceClasses) == 1) {
            return new ApplicationHome(sourceClasses.get(0)).getDir();
        } else {
            return new ApplicationHome().getDir();
        }
    }

    /**
     * 获取SpringBoot应用程序启动目录
     *
     * @param application {@link SpringApplication} 实例
     * @return 启动目录
     */
    public static String getHomeDirAsString(SpringApplication application) {
        return getHomeDir(application).getAbsolutePath();
    }

}
