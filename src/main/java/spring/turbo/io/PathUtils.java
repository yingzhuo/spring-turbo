/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io;

import org.springframework.util.FileSystemUtils;
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

    public static Path createFile(String first, String... more) {
        Asserts.notNull(first);

        Path path = Paths.get(first, more).normalize();
        try {
            boolean success = path.toFile().createNewFile();
            if (!success) {
                throw IOExceptionUtils.toUnchecked("not able to create file");
            }
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
        return path;
    }

    public static Path createDirectory(String first, String... more) {
        Asserts.notNull(first);

        Path path = Paths.get(first, more).normalize();
        try {
            return Files.createDirectory(path);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    public static boolean isExists(Path path) {
        Asserts.notNull(path);
        return Files.exists(path);
    }

    public static boolean isDirectory(Path path) {
        Asserts.notNull(path);
        return Files.isDirectory(path);
    }

    public static boolean isFile(Path path) {
        Asserts.notNull(path);
        return Files.isRegularFile(path);
    }

    public static boolean isSymbolicLink(Path path) {
        Asserts.notNull(path);
        return Files.isSymbolicLink(path);
    }

    public static boolean isHidden(Path path) {
        Asserts.notNull(path);
        try {
            return Files.isHidden(path);
        } catch (IOException e) {
            return false;
        }
    }

    public static void delete(Path path) {
        Asserts.notNull(path);

        try {
            if (isFile(path)) {
                Files.deleteIfExists(path);
            } else {
                FileSystemUtils.deleteRecursively(path);
            }
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

    public static void deleteQuietly(Path path) {
        Asserts.notNull(path);

        try {
            delete(path);
        } catch (Throwable e) {
            // nop
        }
    }

}
