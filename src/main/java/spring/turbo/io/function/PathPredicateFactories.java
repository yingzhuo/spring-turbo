/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io.function;

import spring.turbo.io.PathUtils;
import spring.turbo.util.Asserts;
import spring.turbo.util.FilenameUtils;

import static spring.turbo.util.StringPool.EMPTY;

/**
 * @author 应卓
 * @since 1.0.12
 */
public final class PathPredicateFactories {

    /**
     * 私有构造方法
     */
    private PathPredicateFactories() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static PathPredicate alwaysTrue() {
        return path -> true;
    }

    public static PathPredicate alwaysFalse() {
        return path -> false;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static PathPredicate not(PathPredicate p) {
        Asserts.notNull(p);
        return path -> !p.test(path);
    }

    public static PathPredicate or(PathPredicate p1, PathPredicate p2) {
        Asserts.notNull(p1);
        Asserts.notNull(p2);
        return path -> p1.test(path) || p2.test(path);
    }

    public static PathPredicate and(PathPredicate p1, PathPredicate p2) {
        Asserts.notNull(p1);
        Asserts.notNull(p2);
        return path -> p1.test(path) && p2.test(path);
    }

    public static PathPredicate xor(PathPredicate p1, PathPredicate p2) {
        Asserts.notNull(p1);
        Asserts.notNull(p2);
        return path -> p1.test(path) ^ p2.test(path);
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static PathPredicate isRegularFile() {
        return PathUtils::isRegularFile;
    }

    public static PathPredicate isNotRegularFile() {
        return not(isRegularFile());
    }

    public static PathPredicate isDirectory() {
        return PathUtils::isDirectory;
    }

    public static PathPredicate isNotDirectory() {
        return not(isDirectory());
    }

    public static PathPredicate isSymbolicLink() {
        return PathUtils::isSymbolicLink;
    }

    public static PathPredicate isNotSymbolicLink() {
        return not(isSymbolicLink());
    }

    public static PathPredicate isHidden() {
        return PathUtils::isHidden;
    }

    public static PathPredicate isNotHidden() {
        return not(isHidden());
    }

    public static PathPredicate isFilenameMatchesPattern(String regexPattern) {
        Asserts.notNull(regexPattern);
        return path -> {
            final String filename = path.getFileName().toString();
            return filename.matches(regexPattern);
        };
    }

    public static PathPredicate isFilenameNotMatchesPattern(String regexPattern) {
        return not(isFilenameMatchesPattern(regexPattern));
    }

    public static PathPredicate isExtensionMatches(String ext, boolean ignoreCases) {
        Asserts.notNull(ext);
        return path -> {
            // 扩展名只处理普通文件
            if (!PathUtils.isRegularFile(path)) {
                return false;
            }

            final String filename = path.getFileName().toString();
            final String extension = FilenameUtils.getExtension(filename);

            // 文件没有扩展名
            if (EMPTY.equals(ext)) {
                return false;
            }

            if (ignoreCases) {
                return extension.equalsIgnoreCase(ext);
            } else {
                return extension.equals(ext);
            }
        };
    }

    public static PathPredicate isNotExtensionMatches(String ext, boolean ignoreCases) {
        return not(isExtensionMatches(ext, ignoreCases));
    }

}
