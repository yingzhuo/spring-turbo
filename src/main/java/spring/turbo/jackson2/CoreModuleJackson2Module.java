/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2;

import com.fasterxml.jackson.core.Version;
import com.fasterxml.jackson.core.util.VersionUtil;
import com.fasterxml.jackson.databind.module.SimpleModule;
import spring.turbo.SpringTurboVersion;
import spring.turbo.bean.*;
import spring.turbo.webmvc.token.BasicToken;
import spring.turbo.webmvc.token.StringToken;

/**
 * @author 应卓
 * @since 1.3.0
 */
public class CoreModuleJackson2Module extends SimpleModule {

    public static final Version MODULE_VERSION = VersionUtil.parseVersion(
            SpringTurboVersion.VERSION,
            "com.github.yingzhuo",
            "spring-turbo"
    );

    public CoreModuleJackson2Module() {
        super(CoreModuleJackson2Module.class.getName(), MODULE_VERSION);
    }

    @Override
    public void setupModule(SetupContext context) {
        super.setupModule(context);

        // ---
        context.setMixInAnnotations(StringToken.class, StringTokenMixin.class);
        context.setMixInAnnotations(BasicToken.class, BasicTokenMixin.class);

        // ---
        context.setMixInAnnotations(DateRange.class, DateRangeMixin.class);
        context.setMixInAnnotations(NumberZones.class, NumberZonesMixin.class);
        context.setMixInAnnotations(DateZones.class, DateZonesMixin.class);

        // ---
        context.setMixInAnnotations(NumberPair.class, NumberPairMixin.class);
        context.setMixInAnnotations(LongPair.class, LongPairMixin.class);
        context.setMixInAnnotations(DoublePair.class, DoublePairMixin.class);
        context.setMixInAnnotations(BigIntegerPair.class, BigIntegerPairMixin.class);
        context.setMixInAnnotations(BigDecimalPair.class, BigDecimalPairMixin.class);
    }

}
