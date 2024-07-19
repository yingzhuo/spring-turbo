package spring.turbo.util.crypto.pem;

import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * 精细地处理PEM文本内容，内部工具
 *
 * @author 应卓
 * @since 3.3.1
 */
public final class PemStringUtils {

    /**
     * 私有构造方法
     */
    private PemStringUtils() {
    }

    /**
     * 整理PEM文本内容，去除不必要的白字符
     *
     * @param text PEM文件内容
     * @return 整理后的内容
     */
    public static String trimContent(String text) {
        return Arrays.stream(StringUtils.delimitedListToStringArray(text, "\n"))
                .map(String::trim)
                .filter(StringUtils::hasText)
                .collect(Collectors.joining("\n"))
                .trim();
    }

    /**
     * 整理PEM文本内容，去除不必要的白字符
     *
     * @param resource PEM文件
     * @return 整理后的内容
     */
    public static String trimContent(Resource resource) {
        try {
            return trimContent(resource.getContentAsString(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

}
