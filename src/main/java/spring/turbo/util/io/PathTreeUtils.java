package spring.turbo.util.io;

import spring.turbo.util.Asserts;
import spring.turbo.util.StringFormatter;

import java.io.IOException;
import java.nio.file.FileVisitOption;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

/**
 * {@link Path}相关工具
 *
 * @author 应卓
 * @see PathUtils
 * @see Path
 * @since 1.0.12
 */
public final class PathTreeUtils {

    /**
     * 私有构造方法
     */
    private PathTreeUtils() {
    }

    /**
     * 列出目录下所有子目录或文件
     *
     * @param path 指定目录或文件
     * @return 所有子目录和文件
     */
    public static Stream<Path> list(Path path) {
        return list(path, Integer.MAX_VALUE);
    }

    /**
     * 列出目录下所有子目录或文件
     *
     * @param path     指定目录或文件
     * @param maxDepth 下钻目录层数 (从0开始)
     * @return 所有子目录和文件
     */
    public static Stream<Path> list(Path path, int maxDepth) {
        Asserts.notNull(path);
        Asserts.isTrue(maxDepth >= 0);

        if (!PathUtils.isExists(path)) {
            final String msg = StringFormatter.format("'{}' not exists", path);
            throw IOExceptionUtils.toUnchecked(msg);
        }

        try {
            return Files.walk(path, maxDepth, FileVisitOption.FOLLOW_LINKS);
        } catch (IOException e) {
            throw IOExceptionUtils.toUnchecked(e);
        }
    }

}
