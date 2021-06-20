package io.kimmking.cache.redission;

import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.stereotype.Component;

@Component
public class RedissionService {

    RLock lock;

    RedissonClient client;

    RedissionService() {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");

        client = Redisson.create(config);
        lock = client.getLock("lock1");
    }

    public RLock getLock() {
        return lock;
    }

    public RedissonClient getClient() {
        return client;
    }
}
