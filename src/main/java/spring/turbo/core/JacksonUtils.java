/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.core;

import com.fasterxml.jackson.databind.ObjectMapper;
import spring.turbo.util.Asserts;
import spring.turbo.util.StringPool;

import java.io.File;
import java.io.Reader;
import java.net.URL;

/**
 * FasterXML-jackson 相关工具
 *
 * @author 应卓
 *
 * @see ObjectMapper
 * @see SpringUtils
 *
 * @since 1.0.12
 */
public final class JacksonUtils {

    /**
     * 私有构造方法
     */
    private JacksonUtils() {
        super();
    }

    public static String writeAsString(Object obj) {
        return writeAsString(obj, false);
    }

    public static String writeAsString(Object obj, boolean flat) {
        Asserts.notNull(obj);

        final ObjectMapper om = SpringUtils.getBean(ObjectMapper.class).orElseGet(ObjectMapper::new);

        try {
            final String jsonString = om.writeValueAsString(obj);
            if (flat) {
                return jsonString.replaceAll("\\n", StringPool.EMPTY);
            } else {
                return jsonString;
            }
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    // -----------------------------------------------------------------------------------------------------------------

    public static <T> T readAsObject(String json, Class<T> objectType) {
        Asserts.notNull(json);
        Asserts.notNull(objectType);

        final ObjectMapper om = SpringUtils.getBean(ObjectMapper.class).orElseGet(ObjectMapper::new);

        try {
            return om.readValue(json, objectType);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static <T> T readAsObject(File json, Class<T> objectType) {
        Asserts.notNull(json);
        Asserts.notNull(objectType);

        final ObjectMapper om = SpringUtils.getBean(ObjectMapper.class).orElseGet(ObjectMapper::new);

        try {
            return om.readValue(json, objectType);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static <T> T readAsObject(URL json, Class<T> objectType) {
        Asserts.notNull(json);
        Asserts.notNull(objectType);

        final ObjectMapper om = SpringUtils.getBean(ObjectMapper.class).orElseGet(ObjectMapper::new);

        try {
            return om.readValue(json, objectType);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

    public static <T> T readAsObject(Reader json, Class<T> objectType) {
        Asserts.notNull(json);
        Asserts.notNull(objectType);

        final ObjectMapper om = SpringUtils.getBean(ObjectMapper.class).orElseGet(ObjectMapper::new);

        try {
            return om.readValue(json, objectType);
        } catch (Exception e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }

}
