/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.util;

/**
 * @author 应卓
 *
 * @see XmlStringUtils
 *
 * @since 3.2.5
 */
public final class JsonStringUtils {

    public static String removeExtraWhitespaces(String jsonString) {
        final var result = new StringBuilder(jsonString.length());
        boolean inQuotes = false;
        boolean escapeMode = false;
        for (char character : jsonString.toCharArray()) {
            if (escapeMode) {
                result.append(character);
                escapeMode = false;
            } else if (character == '"') {
                inQuotes = !inQuotes;
                result.append(character);
            } else if (character == '\\') {
                escapeMode = true;
                result.append(character);
            } else if (!inQuotes && character == ' ') {
                continue;
            } else {
                result.append(character);
            }
        }
        return result.toString();
    }

    /**
     * 私有构造方法
     */
    private JsonStringUtils() {
        super();
    }

}
