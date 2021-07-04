package io.kimmking.kmq.core;

import lombok.SneakyThrows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public final class Kmq {

    public Kmq(String topic, int capacity) {
        this.topic = topic;
        this.capacity = capacity;
        this.queue = new LinkedBlockingQueue(capacity);
        this.array = new ArrayList(capacity);
        this.map = new HashMap();
    }

    private String topic;

    private int capacity;

    private LinkedBlockingQueue<KmqMessage> queue;

    private ArrayList<KmqMessage> array;

    private HashMap<String, Integer> map;

    public boolean send(KmqMessage message) {
        array.add(message);
        return queue.offer(message);
    }

    public KmqMessage poll() {
        return queue.poll();
    }

    @SneakyThrows
    public KmqMessage poll(long timeout) {
        return queue.poll(timeout, TimeUnit.MILLISECONDS);
    }

    @SneakyThrows
    public KmqMessage pollByUser(String userId, long timeout) {
        queue.poll(timeout, TimeUnit.MILLISECONDS);
        if (map.containsKey(userId)) {
            int offset = map.get(userId);
            return array.get(offset);
        } else {
            int offset = 0;
            map.put(userId, offset + 1);
            return array.get(offset);
        }
    }

}
