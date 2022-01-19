/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * {@link Path}相关工具
 *
 * @author 应卓
 * @since 1.0.7
 */
public final class PathUtils {

    /**
     * 私有构造方法
     */
    private PathUtils() {
        super();
    }

    /**
     * 创建Path并尝试创建文件
     *
     * @param first 第一级Path
     * @param more  次级Path (多个)
     * @return Path实例
     */
    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static Path create(String first, String... more) {
        Asserts.notNull(first);
        Path path = Paths.get(first, more).normalize();
        if (!path.toFile().exists()) {
            try {
                path.toFile().createNewFile();
            } catch (IOException ignored) {
                // ignored
            }
        }
        return path;
    }

    /**
     * 删除
     *
     * @param path 待删除的Path
     */
    public static void deleteQuietly(@Nullable Path path) {
        if (path != null) {
            try {
                Files.deleteIfExists(path);
            } catch (IOException ignored) {
                // nop
            }
        }
    }

}
