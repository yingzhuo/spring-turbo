/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletRequestWrapper;
import jakarta.servlet.http.HttpSession;
import org.springframework.lang.NonNull;
import org.springframework.web.cors.CorsUtils;
import spring.turbo.lang.Immutable;
import spring.turbo.util.StringFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Stream;

import static spring.turbo.util.StringPool.LF;
import static spring.turbo.util.StringPool.QUESTION_MARK_X_3;

/**
 * HTTP(s)请求快照
 *
 * @author 应卓
 * @see jakarta.servlet.http.HttpServletRequest
 * @since 1.0.0
 */
@Immutable
public final class HttpRequestSnapshot extends HttpServletRequestWrapper implements Iterable<String> {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    private final List<String> lines = new LinkedList<>();
    private final String text;

    /**
     * 私有构造方法
     *
     * @param request HTTP(s)请求
     */
    private HttpRequestSnapshot(HttpServletRequest request) {
        super(request);

        lines.add(StringFormatter.format("Request Type: {}", request.getClass().getName()));
        lines.add(StringFormatter.format("Time: {}", DATE_FORMAT.format(new Date())));
        lines.add(StringFormatter.format("Method: {}", request.getMethod()));
        lines.add(StringFormatter.format("Path: {}", request.getRequestURI()));

        lines.add(StringFormatter.format("Parameters:"));
        Enumeration<String> requestParamNames = request.getParameterNames();
        while (requestParamNames.hasMoreElements()) {
            String requestParamName = requestParamNames.nextElement();
            String requestParamValue = request.getParameter(requestParamName);
            lines.add(StringFormatter.format("\t\t{} = {}", requestParamName, requestParamValue));
        }

        lines.add(StringFormatter.format("Headers:"));
        Enumeration<String> requestHeaderNames = request.getHeaderNames();
        while (requestHeaderNames.hasMoreElements()) {
            String requestHeaderName = requestHeaderNames.nextElement();
            String requestHeaderValue = request.getHeader(requestHeaderName);
            lines.add(StringFormatter.format("\t\t{} = {}", requestHeaderName, requestHeaderValue));
        }

        lines.add(StringFormatter.format("Remote Address: {}", getRemoteAddr(request)));
        lines.add(StringFormatter.format("Session ID: {}", getSessionId(request)));
        lines.add(StringFormatter.format("Is Cors PreFlight: {}", CorsUtils.isPreFlightRequest(request)));

        text = String.join(LF, lines).trim();
    }

    public static HttpRequestSnapshot of(HttpServletRequest request) {
        return new HttpRequestSnapshot(request);
    }

    private String getSessionId(HttpServletRequest request) {
        return Optional.ofNullable(request.getSession(false)).map(HttpSession::getId).orElse(QUESTION_MARK_X_3);
    }

    private String getRemoteAddr(HttpServletRequest request) {
        return Optional.ofNullable(RemoteAddressUtils.getIpAddress(request)).orElse(QUESTION_MARK_X_3);
    }

    @Override
    public String toString() {
        return text;
    }

    @NonNull
    @Override
    public Iterator<String> iterator() {
        return Collections.unmodifiableList(lines).iterator();
    }

    public List<String> getLinesList() {
        return lines;
    }

    public Stream<String> getLines() {
        return lines.stream();
    }

}
