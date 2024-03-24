/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.banner;

import org.springframework.boot.Banner;
import org.springframework.core.env.Environment;
import org.springframework.lang.Nullable;
import spring.turbo.util.collection.CollectionUtils;
import spring.turbo.util.StringFormatter;

import java.io.PrintStream;
import java.util.SortedSet;

import static spring.turbo.util.collection.SetFactories.nullSafeNewTreeSet;

/**
 * @author 应卓
 *
 * @since 3.2.4
 */
public class EnvironmentPrintingBanner implements Banner {

    private final SortedSet<String> printingEnvironmentKeys;

    public EnvironmentPrintingBanner(@Nullable String... printingEnvironmentKeys) {
        this.printingEnvironmentKeys = nullSafeNewTreeSet(printingEnvironmentKeys);
    }

    @Override
    public void printBanner(Environment environment, Class<?> sourceClass, PrintStream out) {
        if (CollectionUtils.isNotEmpty(printingEnvironmentKeys)) {
            for (var envName : printingEnvironmentKeys) {
                var envVal = environment.getProperty(envName);
                out.println(StringFormatter.format("Environment: {} = {}", envName, envVal));
            }
        }
    }

}
