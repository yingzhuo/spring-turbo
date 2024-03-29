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
import spring.turbo.bean.LongPair;
import spring.turbo.bean.NumberPair;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Locale;

/**
 * @author 应卓
 *
 * @since 1.3.1
 */
public class LongPairFormatter implements Formatter<LongPair> {

    private final NumberPairFormatter inner = new NumberPairFormatter();

    @Override
    public LongPair parse(String text, Locale locale) throws ParseException {
        NumberPair np = inner.parse(text, locale);
        return new LongPair(np.getLeft(BigDecimal.class), np.getRight(BigDecimal.class));
    }

    @Override
    public String print(LongPair object, Locale locale) {
        return object.toString();
    }

}
