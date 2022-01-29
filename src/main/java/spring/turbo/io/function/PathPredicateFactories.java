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
import spring.turbo.util.StringUtils;

import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

import static spring.turbo.util.StringPool.DOT;
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

    public static PathPredicate isEmptyDirectory() {
        return PathUtils::isEmptyDirectory;
    }

    public static PathPredicate isNotEmptyDirectory() {
        return not(isEmptyDirectory());
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

    public static PathPredicate isExtensionMatches(String... extensions) {
        return isExtensionMatches(true, extensions);
    }

    public static PathPredicate isExtensionMatches(boolean ignoreCases, String... extensions) {
        return new Extension(ignoreCases, new HashSet<>(Arrays.asList(extensions)));
    }

    public static PathPredicate isNotExtensionMatches(String... extensions) {
        return not(isExtensionMatches(extensions));
    }

    public static PathPredicate isNotExtensionMatches(boolean ignoreCases, String... extensions) {
        return not(isExtensionMatches(ignoreCases, extensions));
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

    // -----------------------------------------------------------------------------------------------------------------

    private static final class Extension implements PathPredicate {

        private final boolean ignoreCases;
        private final Set<String> allowedExtensions;

        public Extension(boolean ignoreCases, Set<String> allowedExtensions) {
            this.ignoreCases = ignoreCases;
            this.allowedExtensions = allowedExtensions.stream()
                    .filter(StringUtils::isNotNull)
                    .map(ext -> {

                        if (DOT.equals(ext)) {
                            return EMPTY;
                        }

                        if (ext.startsWith(DOT)) {
                            return ext.substring(1);
                        }

                        return ext;
                    })
                    .collect(Collectors.toSet());
        }

        @Override
        public boolean test(Path path) {
            final String e = FilenameUtils.getExtension(path.toString());

            for (String ext : allowedExtensions) {
                if (ignoreCases) {
                    if (ext.equalsIgnoreCase(e)) {
                        return true;
                    }
                } else {
                    if (ext.equals(e)) {
                        return true;
                    }
                }
            }

            return false;
        }
    }

}
