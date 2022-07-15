/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io.function;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import spring.turbo.io.LocalFile;
import spring.turbo.util.StringUtils;

/**
 * @author 应卓
 * @since 1.1.1
 */
public final class LocalFilePredicateFactories {

    /**
     * 私有构造方法
     */
    private LocalFilePredicateFactories() {
        super();
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static LocalFilePredicate alwaysTrue() {
        return localFile -> true;
    }

    public static LocalFilePredicate alwaysFalse() {
        return localFile -> false;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static LocalFilePredicate not(LocalFilePredicate p) {
        return localFile -> !p.test(localFile);
    }

    public static LocalFilePredicate any(LocalFilePredicate... ps) {
        return localFile -> {
            for (LocalFilePredicate p : ps) {
                if (p.test(localFile)) return true;
            }
            return false;
        };
    }

    public static LocalFilePredicate all(LocalFilePredicate... ps) {
        return localFile -> {
            for (LocalFilePredicate p : ps) {
                if (!p.test(localFile)) return false;
            }
            return true;
        };
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static LocalFilePredicate exists() {
        return LocalFile::exists;
    }

    public static LocalFilePredicate notExists() {
        return not(exists());
    }

    public static LocalFilePredicate hidden() {
        return LocalFile::isHidden;
    }

    public static LocalFilePredicate notHidden() {
        return not(hidden());
    }

    public static LocalFilePredicate directory() {
        return LocalFile::isDirectory;
    }

    public static LocalFilePredicate notDirectory() {
        return not(directory());
    }

    public static LocalFilePredicate regularFile() {
        return LocalFile::isRegularFile;
    }

    public static LocalFilePredicate notRegularFile() {
        return not(regularFile());
    }

    public static LocalFilePredicate symbolicLink() {
        return LocalFile::isSymbolicLink;
    }

    public static LocalFilePredicate notSymbolicLink() {
        return not(symbolicLink());
    }

    public static LocalFilePredicate emptyDirectory() {
        return LocalFile::isEmptyDirectory;
    }

    public static LocalFilePredicate notEmptyDirectory() {
        return not(emptyDirectory());
    }

    public static LocalFilePredicate readable() {
        return LocalFile::isReadable;
    }

    public static LocalFilePredicate notReadable() {
        return not(readable());
    }

    public static LocalFilePredicate writable() {
        return LocalFile::isWritable;
    }

    public static LocalFilePredicate notWritable() {
        return not(writable());
    }

    public static LocalFilePredicate executable() {
        return LocalFile::isExecutable;
    }

    public static LocalFilePredicate notExecutable() {
        return not(executable());
    }

    public static LocalFilePredicate zeroSize() {
        return localFile -> localFile.size() == 0L;
    }

    public static LocalFilePredicate notZeroSize() {
        return not(zeroSize());
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static LocalFilePredicate extension(boolean startWithDot, boolean caseSensitive, String... supportExtensions) {
        return localFile -> {
            final String ext = localFile.getExtension(startWithDot);
            for (String supportExtension : supportExtensions) {
                if (caseSensitive) {
                    if (StringUtils.endsWith(ext, supportExtension)) return true;
                } else {
                    if (StringUtils.endsWithIgnoreCase(ext, supportExtension)) return true;
                }
            }
            return false;
        };
    }

    public static LocalFilePredicate antStylePattern(String... patterns) {
        final PathMatcher matcher = new AntPathMatcher();
        return localFile -> {
            final String pathStr = localFile.getPathAsString();
            for (String pattern : patterns) {
                if (matcher.match(pattern, pathStr)) return true;
            }
            return false;
        };
    }

    public static LocalFilePredicate antStylePatternStart(String... patterns) {
        final PathMatcher matcher = new AntPathMatcher();
        return localFile -> {
            final String pathStr = localFile.getPathAsString();
            for (String pattern : patterns) {
                if (matcher.matchStart(pattern, pathStr)) return true;
            }
            return false;
        };
    }

}
