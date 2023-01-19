/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.bean.classpath;

import spring.turbo.lang.Singleton;

import java.util.List;

/**
 * @author 应卓
 * @since 2.0.9
 */
@Singleton
final class NullClassPathScanner implements ClassPathScanner {

    /**
     * 获取单例实例
     *
     * @return 实例
     */
    public static NullClassPathScanner getInstance() {
        return SyncAvoid.INSTANCE;
    }

    /**
     * 私有构造方法
     */
    private NullClassPathScanner() {
        super();
    }

    @Override
    public List<ClassDef> scan(Iterable<String> basePackages) {
        return List.of();
    }

    @Override
    public List<ClassDef> scan(String... basePackages) {
        return List.of();
    }

    // 延迟加载
    private static final class SyncAvoid {
        private static final NullClassPathScanner INSTANCE = new NullClassPathScanner();
    }

}
