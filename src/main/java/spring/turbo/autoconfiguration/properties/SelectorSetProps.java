/* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *
 *    ____             _            _____           _
 *   / ___| _ __  _ __(_)_ __   __ |_   _|   _ _ __| |__   ___
 *   \___ \| '_ \| '__| | '_ \ / _` || || | | | '__| '_ \ / _ \
 *    ___) | |_) | |  | | | | | (_| || || |_| | |  | |_) | (_) |
 *   |____/| .__/|_|  |_|_| |_|\__, ||_| \__,_|_|  |_.__/ \___/
 *         |_|                 |___/   https://github.com/yingzhuo/spring-turbo
 * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * */
package spring.turbo.autoconfiguration.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 应卓
 * @since 2.0.1
 */
@ConfigurationProperties(prefix = "springturbo.selector-set-formatter")
public class SelectorSetProps {

    /**
     * 是否启用本插件
     */
    private boolean enabled = true;

    /**
     * 多个 spring.turbo.module.queryselector.Selector 之间的分隔符
     */
    private String separatorBetweenSelectors = "@@";

    /**
     * SelectorFormatter 配置
     */
    private SelectorProperties selectorFormatter = new SelectorProperties();

    /**
     * 解析失败是否忽略错误
     */
    private boolean ignoreErrorIfUnableToParse = false;

    /**
     * 打印失败是否忽略错误
     */
    private boolean ignoreErrorIfUnableToPrint = false;

    /**
     * SQL片段转换工具相关
     */
    private SQL sql = new SQL();

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public String getSeparatorBetweenSelectors() {
        return separatorBetweenSelectors;
    }

    public void setSeparatorBetweenSelectors(String separatorBetweenSelectors) {
        this.separatorBetweenSelectors = separatorBetweenSelectors;
    }

    public SelectorProperties getSelectorFormatter() {
        return selectorFormatter;
    }

    public void setSelectorFormatter(SelectorProperties selectorFormatter) {
        this.selectorFormatter = selectorFormatter;
    }

    public boolean isIgnoreErrorIfUnableToParse() {
        return ignoreErrorIfUnableToParse;
    }

    public void setIgnoreErrorIfUnableToParse(boolean ignoreErrorIfUnableToParse) {
        this.ignoreErrorIfUnableToParse = ignoreErrorIfUnableToParse;
    }

    public boolean isIgnoreErrorIfUnableToPrint() {
        return ignoreErrorIfUnableToPrint;
    }

    public void setIgnoreErrorIfUnableToPrint(boolean ignoreErrorIfUnableToPrint) {
        this.ignoreErrorIfUnableToPrint = ignoreErrorIfUnableToPrint;
    }

    public SQL getSql() {
        return sql;
    }

    public void setSql(SQL sql) {
        this.sql = sql;
    }

    public static class SelectorProperties {

        /**
         * selector内部分隔符
         */
        private String separatorInSelector = "#";

        /**
         * range内部的分隔符
         */
        private String separatorInRange = "<==>";

        /**
         * set内部的分隔符
         */
        private String separatorInSet = "'";

        /**
         * date格式
         */
        private String datePattern = "yyyy-MM-dd";

        /**
         * datetime格式
         */
        private String datetimePattern = "yyyy-MM-dd HH:mm:ss";

        public String getSeparatorInSelector() {
            return separatorInSelector;
        }

        public void setSeparatorInSelector(String separatorInSelector) {
            this.separatorInSelector = separatorInSelector;
        }

        public String getSeparatorInRange() {
            return separatorInRange;
        }

        public void setSeparatorInRange(String separatorInRange) {
            this.separatorInRange = separatorInRange;
        }

        public String getSeparatorInSet() {
            return separatorInSet;
        }

        public void setSeparatorInSet(String separatorInSet) {
            this.separatorInSet = separatorInSet;
        }

        public String getDatePattern() {
            return datePattern;
        }

        public void setDatePattern(String datePattern) {
            this.datePattern = datePattern;
        }

        public String getDatetimePattern() {
            return datetimePattern;
        }

        public void setDatetimePattern(String datetimePattern) {
            this.datetimePattern = datetimePattern;
        }
    }

    public static class SQL {

        /**
         * 是否启用SQL片段转换工具
         */
        private boolean enabled = true;

        /**
         * item 到 数据库column的映射
         */
        private Map<String, String> itemNameToTableColumnMappings = new HashMap<>();

        public boolean isEnabled() {
            return enabled;
        }

        public void setEnabled(boolean enabled) {
            this.enabled = enabled;
        }

        public Map<String, String> getItemNameToTableColumnMappings() {
            return itemNameToTableColumnMappings;
        }

        public void setItemNameToTableColumnMappings(Map<String, String> itemNameToTableColumnMappings) {
            this.itemNameToTableColumnMappings = itemNameToTableColumnMappings;
        }
    }

}
