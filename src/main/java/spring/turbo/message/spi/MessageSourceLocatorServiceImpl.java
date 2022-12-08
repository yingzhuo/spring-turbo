/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.message.spi;

import spring.turbo.lang.Beta;
import spring.turbo.message.MessageSourceLocatorService;

import java.util.List;

/**
 * @author 应卓
 * @since 2.0.3
 */
@Beta
public class MessageSourceLocatorServiceImpl implements MessageSourceLocatorService {

    /**
     * 默认构造方法
     */
    public MessageSourceLocatorServiceImpl() {
        super();
    }

    @Override
    public List<String> getBasenames() {
        return List.of("META-INF/spring_turbo_i18n/common");
    }

}
