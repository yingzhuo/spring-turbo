/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.format;

import org.springframework.format.Formatter;
import org.springframework.util.StringUtils;
import spring.turbo.bean.NumberPair;
import spring.turbo.util.NumberParseUtils;
import spring.turbo.util.RegexUtils;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Pattern;

/**
 * @author 应卓
 * @since 1.3.1
 */
public class NumberPairFormatter implements Formatter<NumberPair> {

    private static final Pattern REGEX =
            Pattern.compile("^([+\\-]?[#a-fA-F0-9.xX]+)-([+\\-]?[#a-fA-F0-9.xX]+)$");

    @Override
    public NumberPair parse(String text, Locale locale) throws ParseException {
        final String originalString = text;
        final String trimmedString = StringUtils.trimAllWhitespace(originalString);

        String leftString = RegexUtils.replaceFirst(trimmedString, REGEX, "$1");
        String rightString = RegexUtils.replaceFirst(trimmedString, REGEX, "$2");

        // 去除前面的+号
        if (leftString.startsWith("+")) {
            leftString = leftString.substring(1);
        }

        // 去除前面的+号
        if (rightString.startsWith("+")) {
            rightString = rightString.substring(1);
        }

        final BigDecimal left = NumberParseUtils.parse(leftString, BigDecimal.class);
        final BigDecimal right = NumberParseUtils.parse(rightString, BigDecimal.class);

        return new NumberPair(left, right);
    }

    @Override
    public String print(NumberPair object, Locale locale) {
        return object.toString();
    }

}
