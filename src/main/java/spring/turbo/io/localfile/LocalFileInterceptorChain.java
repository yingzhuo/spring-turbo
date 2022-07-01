/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.io.localfile;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.OrderComparator;

import java.util.ArrayList;
import java.util.List;

/**
 * @author 应卓
 * @since 1.1.1
 */
public class LocalFileInterceptorChain implements InitializingBean {

    private LocalFilePredicate predicate = lf -> true;
    private List<LocalFileInterceptor> interceptorList = new ArrayList<>();

    /**
     * 构造方法
     */
    public LocalFileInterceptorChain() {
        super();
    }

    public void execute(LocalFile localFile) {
        if (!predicate.test(localFile)) {
            for (LocalFileInterceptor interceptor : interceptorList) {
                boolean next = interceptor.execute(localFile);
                if (!next) {
                    break;
                }
            }
        }
    }

    @Override
    public void afterPropertiesSet() {
        OrderComparator.sort(this.interceptorList);
    }

    public void setPredicate(LocalFilePredicate predicate) {
        this.predicate = predicate;
    }

    public void setInterceptorList(List<LocalFileInterceptor> interceptorList) {
        this.interceptorList = interceptorList;
    }

}
