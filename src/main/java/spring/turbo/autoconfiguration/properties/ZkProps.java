/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.autoconfiguration.properties;

import lombok.*;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.lang.Nullable;
import spring.turbo.util.Asserts;

import java.io.Serializable;

/**
 * @author 应卓
 * @since 1.0.15
 */
@ConfigurationProperties(prefix = "springturbo.zookeeper")
public class ZkProps implements InitializingBean, Serializable {

    private boolean enabled = false;
    private String connectString;
    private String namespace;
    private BackoffRetryPolicy backoffRetryPolicy = new BackoffRetryPolicy();
    private LeaderElection leaderElection = new LeaderElection();

    @Override
    public void afterPropertiesSet() {
        Asserts.notNull(connectString);
        Asserts.notNull(backoffRetryPolicy);
        Asserts.notNull(leaderElection);

        if (leaderElection.enabled) {
            Asserts.notNull(leaderElection.zkPath);
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getConnectString() {
        return connectString;
    }

    public void setConnectString(String connectString) {
        this.connectString = connectString;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public BackoffRetryPolicy getBackoffRetryPolicy() {
        return backoffRetryPolicy;
    }

    public void setBackoffRetryPolicy(BackoffRetryPolicy backoffRetryPolicy) {
        this.backoffRetryPolicy = backoffRetryPolicy;
    }

    public LeaderElection getLeaderElection() {
        return leaderElection;
    }

    public void setLeaderElection(LeaderElection leaderElection) {
        this.leaderElection = leaderElection;
    }

    @Getter
    @Setter
    @ToString
    @NoArgsConstructor(access = AccessLevel.PUBLIC)
    public static class BackoffRetryPolicy implements Serializable {
        private int baseSleepTime = 1000;
        private int maxRetries = 29;

        public int getBaseSleepTime() {
            return baseSleepTime;
        }

        public void setBaseSleepTime(int baseSleepTime) {
            this.baseSleepTime = baseSleepTime;
        }

        public int getMaxRetries() {
            return maxRetries;
        }

        public void setMaxRetries(int maxRetries) {
            this.maxRetries = maxRetries;
        }
    }

    public static class LeaderElection implements Serializable {

        private boolean enabled = true;

        private String zkPath = "/leader-election";

        @Nullable
        private String nodeId;

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public String getZkPath() {
            return zkPath;
        }

        public void setZkPath(String zkPath) {
            this.zkPath = zkPath;
        }

        @Nullable
        public String getNodeId() {
            return nodeId;
        }

        public void setNodeId(@Nullable String nodeId) {
            this.nodeId = nodeId;
        }
    }

}
