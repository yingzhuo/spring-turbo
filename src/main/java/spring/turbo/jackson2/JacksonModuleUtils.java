/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.jackson2;

import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;
import spring.turbo.util.ServiceLoaderUtils;

import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @author 应卓
 *
 * @since 3.2.5
 */
public final class JacksonModuleUtils {

    public static List<Module> loadModules() {
        return loadModules(null);
    }

    public static List<Module> loadModules(@Nullable Predicate<Module> predicate) {
        return ServiceLoaderUtils.loadQuietly(Module.class).stream()
                .filter(Objects.requireNonNullElse(predicate, Objects::nonNull)).toList();
    }

    public static void loadAndRegisterModules(ObjectMapper objectMapper) {
        loadAndRegisterModules(objectMapper, null);
    }

    public static void loadAndRegisterModules(ObjectMapper objectMapper, @Nullable Predicate<Module> predicate) {
        Asserts.notNull(objectMapper);
        objectMapper.registerModules(loadModules(predicate));
    }

    /**
     * 私有构造方法
     */
    private JacksonModuleUtils() {
        super();
    }

}
