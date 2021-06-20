package io.kimmking.cache.service;

import io.kimmking.cache.redission.RedissionService;
import org.redisson.api.RMap;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RepoService {

    @Autowired
    RedissionService redissionService;

    RedissonClient client;

    RMap<String, Integer> rmap;
    
    public void init(String id) {
        client = redissionService.getClient();
        rmap = client.getMap("map1");
        rmap.put(id, 10);
    }
    
    public void decGoods(String id) {
        try {
            redissionService.getLock().lock();
            if (rmap.containsKey(id) && rmap.get(id) > 0) {
                rmap.put(id, (rmap.get(id) - 1));
            }
        } finally {
            redissionService.getLock().unlock();
        }
    }
}
