/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.jsr380;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringUtils;
import spring.turbo.util.collection.StreamFactories;

import static spring.turbo.util.StringPool.NULL;

/**
 * @author 应卓
 * @since 2.0.3
 */
public final class JoinedStringLengthValidators {

    /**
     * 私有构造方法
     */
    private JoinedStringLengthValidators() {
        super();
    }

    private static sealed abstract class AbstractJoinedStringLengthValidator<T> implements ConstraintValidator<JoinedStringLength, T> permits ForArray, ForIterable {
        @Nullable
        private String separator;
        private boolean ignoreNull;
        private int min;
        private int max;

        @Override
        public final void initialize(JoinedStringLength annotation) {
            this.min = annotation.min();
            this.max = annotation.max();
            this.separator = annotation.separator();
            this.ignoreNull = annotation.ignoreNull();
        }

        @Override
        public final boolean isValid(@Nullable T value, ConstraintValidatorContext context) {
            if (value == null) return true;
            Asserts.notNull(separator);
            var str = this.joinAsString(value, this.separator, this.ignoreNull);
            var len = StringUtils.length(str);
            return this.min <= len && len <= this.max;
        }

        protected abstract String joinAsString(T value, String separator, boolean ignoreNull);
    }

    /**
     * 处理String[]
     */
    public final static class ForArray extends AbstractJoinedStringLengthValidator<String[]> {
        @Override
        protected String joinAsString(String[] value, String separator, boolean ignoreNull) {
            if (ignoreNull) {
                return StringUtils.nullSafeJoin(value, separator);
            } else {
                return String.join(separator, value);
            }
        }
    }

    /**
     * 处理 {@link Iterable}
     */
    public static final class ForIterable extends AbstractJoinedStringLengthValidator<Iterable<?>> {
        @Override
        protected String joinAsString(Iterable<?> value, String separator, boolean ignoreNull) {
            if (ignoreNull) {
                return StringUtils.nullSafeJoin(value, separator);
            } else {
                return String.join(
                        separator,
                        StreamFactories.newStream(value).map(o -> o != null ? o.toString() : NULL).toList()
                );
            }
        }
    }

}
