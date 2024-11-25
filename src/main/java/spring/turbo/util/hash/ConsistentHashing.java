package spring.turbo.util.hash;

import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 一致性哈希算法
 *
 * @author 应卓
 * @since 3.4.0
 */
public class ConsistentHashing {

    private final int countOfReplicas;
    private final SortedMap<Integer, String> hashCircle = new TreeMap<>();
    private final HashFunction hashFunction;

    /**
     * 构造方法
     *
     * @param countOfReplicas 虚拟节点数量
     */
    public ConsistentHashing(int countOfReplicas) {
        this(countOfReplicas, new HashFunction.Simple());
    }

    /**
     * 构造方法
     *
     * @param countOfReplicas 虚拟节点数量
     * @param hashFunction    哈希函数
     */
    public ConsistentHashing(int countOfReplicas, @Nullable HashFunction hashFunction) {
        Assert.isTrue(countOfReplicas >= 1, "countOfReplicas must be greater or equals 1");
        this.countOfReplicas = countOfReplicas;
        this.hashFunction = Objects.requireNonNullElseGet(hashFunction, HashFunction::newDefaultInstance);
    }

    /**
     * 添加节点
     *
     * @param nodeName 节点名称或IP
     * @return this
     */
    public ConsistentHashing addNode(String nodeName) {
        for (int i = 0; i < this.countOfReplicas; i++) {
            var virtualNode = nodeName + "&&VN" + i;
            int hash = hashFunction.apply(virtualNode);
            this.hashCircle.put(hash, nodeName);
        }
        return this;
    }

    /**
     * 获取数据应该分配到哪个节点
     *
     * @param key 数据键
     * @return 节点名称
     */
    public String getNode(String key) {
        if (this.hashCircle.isEmpty()) {
            throw new IllegalArgumentException("you should add node first");
        }

        var hash = hashFunction.apply(key);
        var tailMap = hashCircle.tailMap(hash);
        var targetHash = tailMap.isEmpty() ? hashCircle.firstKey() : tailMap.firstKey();
        return hashCircle.get(targetHash);
    }

}
