/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.exception;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceAware;
import org.springframework.context.NoSuchMessageException;
import spring.turbo.util.Asserts;

import java.util.Locale;
import java.util.Optional;

/**
 * @author 应卓
 * @since 1.0.0
 */
public class MessageSourceBusinessExceptionFactory implements BusinessExceptionFactory, MessageSourceAware, InitializingBean {

    private MessageSource messageSource;

    @Override
    public BusinessException create(String code, Locale locale, Object... args) {
        String message = null;
        try {
            message = messageSource.getMessage(code, (Object[]) args, Optional.ofNullable(locale).orElse(Locale.getDefault()));
        } catch (NoSuchMessageException e) {
            throw new IllegalArgumentException(e.getMessage(), e);
        }
        return new BusinessException(code, message);
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void afterPropertiesSet() {
        Asserts.notNull(messageSource);
    }

}
