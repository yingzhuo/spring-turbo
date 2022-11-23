/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2;

import com.fasterxml.jackson.annotation.JsonValue;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import spring.turbo.bean.LongPair;
import spring.turbo.bean.NumberPair;
import spring.turbo.jackson2.support.AbstractNumberPairJsonDeserializer;

/**
 * @author 应卓
 * @see NumberPair
 * @see LongPair
 * @since 1.3.0
 */
@JsonDeserialize(using = LongPairMixin.LongPairJsonDeserializer.class)
public abstract class LongPairMixin {

    @JsonValue
    public abstract String toString();

    public static class LongPairJsonDeserializer extends AbstractNumberPairJsonDeserializer<LongPair> {
        public LongPairJsonDeserializer() {
            super(LongPair.class);
        }
    }

}
