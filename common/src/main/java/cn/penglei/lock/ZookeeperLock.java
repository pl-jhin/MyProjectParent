package cn.penglei.lock;

import lombok.Data;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 主要采用zk的临时节点
 * 临时节点具有释放的特征
 *
 */

public class ZookeeperLock implements Lock {
    public ZookeeperLock() {
    }
    // 锁的工作区间 用来隔离 即临时节点的目录
    private final static String WORKSPACE="/lock-zookeeper";
    // 锁的名称
    private String lockName;
    public void setLockName(String lockName) {
        this.lockName = lockName;
        init();
    }
    // 第三方客户端
    private CuratorFramework client;
    // zookeeper服务器或集群地址
    private static final String ZOOKEEPERSERVER = "localhost:2181";
    private void init(){
        // 初始化 zk客户端对象
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000,10);
        client = CuratorFrameworkFactory.newClient(ZOOKEEPERSERVER,5000,1000,retryPolicy);
        client.start();
        // 创建工作区间  此时肯定是持久节点
        try {
            // 首先判断工作区间节点是否存在，存在会抛一异常
            if (client.checkExists().forPath(WORKSPACE)==null){
                client.create().creatingParentContainersIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(WORKSPACE);
            }
        } catch (Exception e) {
        }
    }
    @Override
    public void lock(){
        // 初始化子节点的监听器
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client,WORKSPACE,true);
        try {
            pathChildrenCache.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 死循环，类自旋
        while (true) {
            // 锁，临时节点的创建
            String lockPath = WORKSPACE + "/" + lockName;
            try {
                if (client.checkExists().forPath(lockPath) == null) {
                    // 创建临时节点
                    client.create().creatingParentContainersIfNeeded()
                            .withMode(CreateMode.EPHEMERAL)
                            .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                            .forPath(lockPath);
                    // 临时节点具有排他，但是不具有阻塞，即释放了锁
                    return;
                }else {
                    // 进入监听进入阻塞状态
                    ListenWait(pathChildrenCache);
                }
            } catch (Exception e) {
                try {
                    ListenWait(pathChildrenCache);
                } catch (Exception ex) {
                }
            }
        }
    }

    public void ListenWait(PathChildrenCache pathChildrenCache) throws Exception {
        // 强制阻塞
        final CountDownLatch countDownLatch = new CountDownLatch(1);

        // 定义监听器 监听workspace目录变化情况，如果发生改变，就会触发zk的watch机制
        pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
            @Override
            public void childEvent(CuratorFramework curatorFramework, PathChildrenCacheEvent pathChildrenCacheEvent) throws Exception {
                if (pathChildrenCacheEvent.getData().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)
                &&pathChildrenCacheEvent.getData().getPath().contains(lockName)){
                    //System.out.println("子节点删除了，开始释放锁......"+lockName);
                    // 临时节点发生变化 唤醒线程
                    countDownLatch.countDown();
                }
            }
        });
        // 线程阻塞
        countDownLatch.wait();
    }

    @Override
    public void unlock() {
        String lockPath = WORKSPACE + "/"+lockName;
        try {
            client.delete().forPath(lockPath);
        } catch (Exception e) {
        }
    }












    @Override
    public void lockInterruptibly() throws InterruptedException {

    }

    @Override
    public boolean tryLock() {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return false;
    }



    @Override
    public Condition newCondition() {
        return null;
    }
}
