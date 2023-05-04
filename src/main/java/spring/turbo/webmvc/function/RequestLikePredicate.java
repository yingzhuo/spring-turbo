/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.webmvc.function;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.lang.Nullable;
import org.springframework.web.context.request.NativeWebRequest;

import java.util.function.Predicate;

/**
 * @author 应卓
 *
 * @since 2.0.9
 */
public interface RequestLikePredicate extends Predicate<HttpServletRequest> {

    @Override
    public boolean test(@Nullable HttpServletRequest request);

    // -----------------------------------------------------------------------------------------------------------------

    public default Predicate<HttpServletRequest> asHttpServletRequestPredicate() {
        return this;
    }

    public default Predicate<NativeWebRequest> asNativeWebRequestPredicate() {
        return nativeWebRequest -> {
            if (nativeWebRequest == null) {
                return false;
            }
            if (nativeWebRequest.getNativeRequest() instanceof HttpServletRequest httpServletRequest) {
                return this.test(httpServletRequest);
            } else {
                return false;
            }
        };
    }

}
