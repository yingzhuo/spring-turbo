/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.Nullable;

import java.util.List;
import java.util.Set;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * {@link StringTokenizer}相关工具
 *
 * @author 应卓
 * @see StringTokenizer
 * @see StringMatcher
 * @since 2.0.2
 */
public final class StringTokenizerUtils {

    /**
     * 私有构造方法
     */
    private StringTokenizerUtils() {
        super();
    }

    public static String[] toArray(@Nullable StringTokenizer tokenizer) {
        return toList(tokenizer).toArray(new String[0]);
    }

    public static List<String> toList(@Nullable StringTokenizer tokenizer) {
        return toStream(tokenizer, false).toList();
    }

    public static Set<String> toSet(@Nullable StringTokenizer tokenizer) {
        return toStream(tokenizer, false).collect(Collectors.toSet());
    }

    public static Stream<String> toStream(@Nullable StringTokenizer tokenizer) {
        return toStream(tokenizer, false);
    }

    public static Stream<String> toStream(@Nullable StringTokenizer tokenizer, boolean parallel) {
        if (tokenizer == null) {
            return Stream.empty();
        }
        return StreamSupport.stream(Spliterators.spliteratorUnknownSize(tokenizer, Spliterator.ORDERED), parallel);
    }

}
