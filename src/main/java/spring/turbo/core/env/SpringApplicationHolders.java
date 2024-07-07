package spring.turbo.core.env;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.env.ConfigurableEnvironment;
import spring.turbo.core.SpringUtils;

import java.nio.file.Path;
import java.util.Set;

import static spring.turbo.util.collection.CollectionUtils.size;

/**
 * ApplicationContext实例保存
 *
 * @author 应卓
 * @see SpringUtils
 * @since 1.0.2
 */
public final class SpringApplicationHolders {

    private static ApplicationContext APPLICATION_CONTEXT;
    private static Path APPLICATION_HOME;
    private static Set<Object> APPLICATION_SOURCES;
    private static WebApplicationType APPLICATION_WEB_APPLICATION_TYPE;

    /**
     * 私有构造方法
     */
    private SpringApplicationHolders() {
    }

    public static ApplicationContext getApplicationContext() {
        return APPLICATION_CONTEXT;
    }

    public static Path getApplicationHome() {
        return APPLICATION_HOME;
    }

    public static Set<Object> getApplicationSources() {
        return APPLICATION_SOURCES;
    }

    public static WebApplicationType getApplicationWebApplicationType() {
        return APPLICATION_WEB_APPLICATION_TYPE;
    }

    private static class Hook implements EnvironmentPostProcessor, ApplicationListener<ContextRefreshedEvent>, PriorityOrdered {

        @Override
        public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {
            SpringApplicationHolders.APPLICATION_HOME = getAppHomeDir(application).toAbsolutePath();
            SpringApplicationHolders.APPLICATION_SOURCES = application.getAllSources();
            SpringApplicationHolders.APPLICATION_WEB_APPLICATION_TYPE = application.getWebApplicationType();
        }

        @Override
        public void onApplicationEvent(ContextRefreshedEvent event) {
            SpringApplicationHolders.APPLICATION_CONTEXT = event.getApplicationContext();
        }

        @Override
        public int getOrder() {
            return HIGHEST_PRECEDENCE;
        }

        private Path getAppHomeDir(SpringApplication springApplication) {
            var sourceClasses = springApplication.getAllSources()
                    .stream()
                    .filter(o -> o instanceof Class<?>)
                    .map(o -> (Class<?>) o)
                    .toList();

            var file = size(sourceClasses) == 1 ?
                    new ApplicationHome(sourceClasses.get(0)).getDir() :
                    new ApplicationHome().getDir();

            return file.toPath();
        }
    }

}
