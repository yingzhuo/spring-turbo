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

import java.util.Set;

/**
 * @author 应卓
 *
 * @since 1.0.0
 */
public class PasswordValidator implements ConstraintValidator<Password, CharSequence> {

    @Nullable
    private Password.Complexity complexity;

    @Nullable
    private Set<Character> specialChars;

    private int minLength;

    private int maxLength;

    /**
     * 构造方法
     */
    public PasswordValidator() {
        super();
    }

    @Override
    public void initialize(Password annotation) {
        this.complexity = annotation.complexity();
        this.minLength = annotation.min();
        this.maxLength = annotation.max();
        this.specialChars = StringUtils.toCharSet(annotation.specialChars());
    }

    @Override
    public boolean isValid(@Nullable CharSequence password, ConstraintValidatorContext context) {

        if (password == null) {
            return true;
        }

        Asserts.notNull(complexity);
        Asserts.notNull(specialChars);

        final int len = password.length();
        if (len < minLength || len > maxLength) {
            return false;
        }

        if (complexity == Password.Complexity.ANY) {
            return true;
        }

        final Set<Character> chars = StringUtils.toCharSet(password.toString());

        boolean hasNumeric = false;
        boolean hasAlphabetic = false;
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasSpecial = false;

        int point = 0;

        for (int ch : chars) {

            if ('a' <= ch && ch <= 'z') {
                hasAlphabetic = true;
                hasLower = true;
                point += 1;
                continue;
            }

            if ('A' <= ch && ch <= 'Z') {
                hasAlphabetic = true;
                hasUpper = true;
                point += 1;
                continue;
            }

            if ('0' <= ch && ch <= '9') {
                hasNumeric = true;
                point += 1;
                continue;
            }

            if (specialChars.stream().anyMatch(i -> i == ch)) {
                hasSpecial = true;
                point += 1;
                // continue;
            }
        }

        return switch (complexity) {
        case ALPHABETIC_AND_NUMERIC -> hasAlphabetic && hasNumeric;
        case ALPHABETIC_AND_NUMERIC_AND_SPECIAL_CHARS -> hasAlphabetic && hasNumeric && hasSpecial;
        case LOWER_AND_UPPER_AND_NUMERIC -> hasLower && hasUpper && hasNumeric;
        case LOWER_AND_UPPER_AND_NUMERIC_AND_SPECIAL_CHARS -> hasLower && hasUpper && hasNumeric && hasSpecial;
        case AT_LEAST_TWO_KIND_OF_ALPHABETIC_AND_NUMERIC_AND_SPECIAL_CHARS -> point >= 2;
        case HAS_NUMERIC -> hasNumeric;
        case ONLY_NUMERIC -> hasNumeric && !hasAlphabetic && !hasSpecial;
        case HAS_ALPHABETIC -> hasAlphabetic;
        case ONLY_ALPHABETIC -> hasAlphabetic && !hasNumeric && !hasSpecial;
        default -> true;
        };
    }

}
