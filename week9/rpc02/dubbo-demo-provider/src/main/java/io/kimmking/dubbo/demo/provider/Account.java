package io.kimmking.dubbo.demo.provider;

import lombok.Data;

@Data
public class Account {
    private int id;
    private int us_wallet;
    private int cny_wallet;
}
