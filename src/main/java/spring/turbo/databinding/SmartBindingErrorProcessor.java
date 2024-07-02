/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.databinding;

import org.springframework.beans.PropertyAccessException;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DefaultBindingErrorProcessor;
import org.springframework.validation.ObjectError;

/**
 * {@link DefaultBindingErrorProcessor} 扩展
 *
 * @author 应卓
 * @see #getInstance()
 * @see org.springframework.validation.DataBinder
 * @see org.springframework.web.bind.WebDataBinder
 * @since 3.3.1
 */
public final class SmartBindingErrorProcessor extends DefaultBindingErrorProcessor {

    /**
     * 私有构造方法
     */
    private SmartBindingErrorProcessor() {
        super();
    }

    /**
     * 获取本类型实例
     *
     * @return 单例实例
     */
    public static SmartBindingErrorProcessor getInstance() {
        return SyncAvoid.INSTANCE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void processPropertyAccessException(PropertyAccessException ex, BindingResult bindingResult) {

        if (ex.getRootCause() instanceof MessageSourceResolvable rootResolvable) {
            bindingResult.addError(
                    new ObjectError(
                            bindingResult.getObjectName(),
                            rootResolvable.getCodes(),
                            rootResolvable.getArguments(),
                            rootResolvable.getDefaultMessage()
                    )
            );
            return;
        }

        super.processPropertyAccessException(ex, bindingResult);
    }

    // 延迟加载
    private static class SyncAvoid {
        private static final SmartBindingErrorProcessor INSTANCE = new SmartBindingErrorProcessor();
    }

}
