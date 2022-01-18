/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author 应卓
 * @see ListFactories
 * @since 1.0.9
 */
public final class SetFactories {

    /**
     * 私有构造方法
     */
    private SetFactories() {
        super();
    }

    @NonNull
    @SafeVarargs
    public static <T> Set<T> newUnmodifiableSet(@Nullable T... elements) {
        if (elements == null) {
            return Collections.emptySet();
        }
        return Collections.unmodifiableSet(newHashSet(elements));
    }

    @NonNull
    @SafeVarargs
    public static <T> HashSet<T> newHashSet(@Nullable T... elements) {
        final HashSet<T> set = new HashSet<>();
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    @NonNull
    @SafeVarargs
    public static <T> LinkedHashSet<T> newLinkedHashSet(@Nullable T... elements) {
        final LinkedHashSet<T> set = new LinkedHashSet<>();
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    @NonNull
    @SafeVarargs
    public static <T> HashSet<T> nullSafeNewHashSet(@Nullable T... elements) {
        final HashSet<T> set = new HashSet<>();
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

    @NonNull
    @SafeVarargs
    public static <T> LinkedHashSet<T> nullSafeNewLinkedHashSet(@Nullable T... elements) {
        final LinkedHashSet<T> set = new LinkedHashSet<>();
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

}
