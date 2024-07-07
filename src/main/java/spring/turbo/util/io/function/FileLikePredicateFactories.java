package spring.turbo.util.io.function;

import org.springframework.lang.Nullable;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import spring.turbo.util.Asserts;
import spring.turbo.util.collection.ArrayUtils;
import spring.turbo.util.io.FileUtils;
import spring.turbo.util.io.FilenameUtils;

import java.io.File;
import java.util.Arrays;

/**
 * @author 应卓
 * @see FileLikePredicate
 * @since 2.0.8
 */
public final class FileLikePredicateFactories {

    /**
     * 私有构造方法
     */
    private FileLikePredicateFactories() {
    }

    public static FileLikePredicate alwaysTrue() {
        return file -> true;
    }

    public static FileLikePredicate alwaysFalse() {
        return file -> false;
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static FileLikePredicate not(FileLikePredicate predicate) {
        Asserts.notNull(predicate);
        return file -> !predicate.test(file);
    }

    public static FileLikePredicate or(FileLikePredicate... predicates) {
        Asserts.notNull(predicates);
        Asserts.notEmpty(predicates);
        Asserts.noNullElements(predicates);

        if (ArrayUtils.length(predicates) == 1) {
            return predicates[0];
        }

        return f -> {
            for (var predicate : predicates) {
                if (predicate.test(f)) {
                    return true;
                }
            }
            return false;
        };
    }

    public static FileLikePredicate and(FileLikePredicate... predicates) {
        Asserts.notNull(predicates);
        Asserts.notEmpty(predicates);
        Asserts.noNullElements(predicates);

        if (ArrayUtils.length(predicates) == 1) {
            return predicates[0];
        }

        return file -> {
            for (var predicate : predicates) {
                if (!predicate.test(file)) {
                    return false;
                }
            }
            return true;
        };
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static FileLikePredicate isHidden() {
        return file -> file != null && FileUtils.isHidden(file);
    }

    public static FileLikePredicate isDirectory() {
        return file -> file != null && FileUtils.isDirectory(file);
    }

    public static FileLikePredicate isEmptyDirectory() {
        return file -> file != null && FileUtils.isEmptyDirectory(file);
    }

    public static FileLikePredicate isRegularFile() {
        return file -> file != null && FileUtils.isRegularFile(file);
    }

    public static FileLikePredicate isExists() {
        return file -> file != null && FileUtils.isExists(file);
    }

    public static FileLikePredicate isReadable() {
        return file -> file != null && FileUtils.isReadable(file);
    }

    public static FileLikePredicate isWritable() {
        return file -> file != null && FileUtils.isWritable(file);
    }

    public static FileLikePredicate isReadableAndWritable() {
        return file -> file != null && FileUtils.isReadableAndWritable(file);
    }

    public static FileLikePredicate regexMatch(String... acceptRegexes) {
        Asserts.notNull(acceptRegexes);
        Asserts.notEmpty(acceptRegexes);
        Asserts.noNullElements(acceptRegexes);

        if (ArrayUtils.length(acceptRegexes) == 1) {
            return new Regex(acceptRegexes[0]);
        } else {
            return or(Arrays.stream(acceptRegexes).map(Regex::new).toList().toArray(new Regex[0]));
        }
    }

    public static FileLikePredicate antStyleMatch(String... acceptPatterns) {
        Asserts.notNull(acceptPatterns);
        Asserts.notEmpty(acceptPatterns);
        Asserts.noNullElements(acceptPatterns);

        if (ArrayUtils.length(acceptPatterns) == 1) {
            return new AntPath(acceptPatterns[0]);
        } else {
            return or(Arrays.stream(acceptPatterns).map(AntPath::new).toList().toArray(new AntPath[0]));
        }
    }

    public static FileLikePredicate filename(String... acceptFilenames) {
        return filename(true, acceptFilenames);
    }

    public static FileLikePredicate filename(boolean ignoreCase, String... acceptFilenames) {
        Asserts.notNull(acceptFilenames);
        Asserts.notEmpty(acceptFilenames);
        Asserts.noNullElements(acceptFilenames);

        if (ArrayUtils.length(acceptFilenames) == 1) {
            return new Filename(ignoreCase, acceptFilenames[1]);
        } else {
            return or(Arrays.stream(acceptFilenames).map(fn -> new Filename(ignoreCase, fn)).toList()
                    .toArray(new Filename[0]));
        }
    }

    public static FileLikePredicate extension(String... acceptExtensions) {
        return extension(true, acceptExtensions);
    }

    public static FileLikePredicate extension(boolean ignoreCase, String... acceptExtensions) {
        Asserts.notNull(acceptExtensions);
        Asserts.notEmpty(acceptExtensions);
        Asserts.noNullElements(acceptExtensions);

        if (ArrayUtils.length(acceptExtensions) == 1) {
            return new Filename(ignoreCase, acceptExtensions[1]);
        } else {
            return or(Arrays.stream(acceptExtensions).map(fn -> new Extension(ignoreCase, fn)).toList()
                    .toArray(new Extension[0]));
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    private record Regex(String regex) implements FileLikePredicate {
        @Override
        public boolean test(@Nullable File file) {
            if (file == null)
                return false;
            return file.getAbsolutePath().matches(regex);
        }
    }

    private record AntPath(String pattern) implements FileLikePredicate {
        private static final PathMatcher MATCHER = new AntPathMatcher();

        @Override
        public boolean test(@Nullable File file) {
            if (file == null) {
                return false;
            }
            return MATCHER.match(pattern, file.getAbsolutePath());
        }
    }

    private record Filename(boolean ignoreCase, String filename) implements FileLikePredicate {
        @Override
        public boolean test(@Nullable File file) {
            if (file == null) {
                return false;
            }

            if (ignoreCase) {
                return file.getName().equalsIgnoreCase(this.filename);
            } else {
                return file.getName().equals(this.filename);
            }
        }
    }

    private record Extension(boolean ignoreCase, String extension) implements FileLikePredicate {
        @Override
        public boolean test(@Nullable File file) {
            if (file == null) {
                return false;
            }

            var filename = file.getName();
            var ext = FilenameUtils.getExtension(filename);

            if (ignoreCase) {
                return filename.equalsIgnoreCase(this.extension);
            } else {
                return filename.equals(this.extension);
            }
        }
    }
}
