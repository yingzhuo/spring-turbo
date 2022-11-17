/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2;

import com.fasterxml.jackson.core.json.PackageVersion;
import com.fasterxml.jackson.databind.module.SimpleModule;
import spring.turbo.bean.*;
import spring.turbo.webmvc.token.BasicToken;
import spring.turbo.webmvc.token.StringToken;

/**
 * @author 应卓
 * @since 1.3.0
 */
public class CoreModuleJackson2Module extends SimpleModule {

    public CoreModuleJackson2Module() {
        super(CoreModuleJackson2Module.class.getName(), PackageVersion.VERSION);
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);

        // http(s) token
        context.setMixInAnnotations(StringToken.class, StringTokenMixin.class);
        context.setMixInAnnotations(BasicToken.class, BasicTokenMixin.class);

        //
        context.setMixInAnnotations(DateRange.class, DateRangeMixin.class);

        //
        context.setMixInAnnotations(NumberPair.class, NumberPairMixin.class);
        context.setMixInAnnotations(IntegerPair.class, IntegerPairMixin.class);
        context.setMixInAnnotations(LongPair.class, LongPairMixin.class);
        context.setMixInAnnotations(BytePair.class, BytePairMixin.class);
        context.setMixInAnnotations(ShortPair.class, ShortPairMixin.class);
        context.setMixInAnnotations(FloatPair.class, FloatPairMixin.class);
        context.setMixInAnnotations(DoublePair.class, DoublePairMixin.class);
        context.setMixInAnnotations(BigIntegerPair.class, BigIntegerPairMixin.class);
        context.setMixInAnnotations(BigDecimalPair.class, BigDecimalPairMixin.class);

        //
        context.setMixInAnnotations(NumberZones.class, NumberZonesMixin.class);
    }

}
