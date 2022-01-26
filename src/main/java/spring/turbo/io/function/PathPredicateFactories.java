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
import spring.turbo.util.CollectionUtils;
import spring.turbo.util.FilenameUtils;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

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
        return any(p1, p2);
    }

    public static PathPredicate and(PathPredicate p1, PathPredicate p2) {
        Asserts.notNull(p1);
        Asserts.notNull(p2);
        return all(p1, p2);
    }

    public static PathPredicate any(PathPredicate... ps) {
        Asserts.notNull(ps);
        Asserts.noNullElements(ps);
        return new Any(Arrays.asList(ps));
    }

    public static PathPredicate all(PathPredicate... ps) {
        Asserts.notNull(ps);
        Asserts.noNullElements(ps);
        return new All(Arrays.asList(ps));
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

    public static PathPredicate isExtensionMatches(String ext) {
        return isExtensionMatches(ext, true);
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

    public static PathPredicate isNotExtensionMatches(String ext) {
        return isNotExtensionMatches(ext, true);
    }

    public static PathPredicate isNotExtensionMatches(String ext, boolean ignoreCases) {
        return not(isExtensionMatches(ext, ignoreCases));
    }

    // -----------------------------------------------------------------------------------------------------------------

    private static final class All implements PathPredicate {

        private final List<PathPredicate> list = new LinkedList<>();

        public All(List<PathPredicate> list) {
            CollectionUtils.nullSafeAddAll(this.list, list);
            Asserts.isTrue(this.list.size() >= 2);
        }

        @Override
        public boolean test(Path path) {
            for (PathPredicate p : list) {
                if (!p.test(path)) {
                    return false;
                }
            }
            return true;
        }
    }

    private static final class Any implements PathPredicate {

        private final List<PathPredicate> list = new LinkedList<>();

        public Any(List<PathPredicate> list) {
            CollectionUtils.nullSafeAddAll(this.list, list);
            Asserts.isTrue(this.list.size() >= 2);
        }

        @Override
        public boolean test(Path path) {
            for (PathPredicate p : list) {
                if (p.test(path)) {
                    return true;
                }
            }
            return false;
        }
    }

}
