package io.kimmking.dubbo.demo.provider;

import lombok.Data;

@Data
public class User {
    private int us;
    private int cny;

    public User addUs(int i) {
        this.us += i;
        return this;
    }

    public User addCny(int i) {
        this.cny += i;
        return this;
    }

}
