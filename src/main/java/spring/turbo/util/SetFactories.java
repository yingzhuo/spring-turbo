/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;

/**
 * @author 应卓
 * @since 1.0.9
 */
public final class SetFactories {

    /**
     * 私有构造方法
     */
    private SetFactories() {
        super();
    }

    @SafeVarargs
    public static <T> HashSet<T> newHashSet(T... elements) {
        final HashSet<T> set = new HashSet<>();
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    @SafeVarargs
    public static <T> LinkedHashSet<T> newLinkedHashSet(T... elements) {
        final LinkedHashSet<T> set = new LinkedHashSet<>();
        if (elements != null) {
            Collections.addAll(set, elements);
        }
        return set;
    }

    @SafeVarargs
    public static <T> HashSet<T> nullSafeNewHashSet(T... elements) {
        final HashSet<T> set = new HashSet<>();
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

    @SafeVarargs
    public static <T> LinkedHashSet<T> nullSafeNewLinkedHashSet(T... elements) {
        final LinkedHashSet<T> set = new LinkedHashSet<>();
        CollectionUtils.nullSafeAddAll(set, elements);
        return set;
    }

}
