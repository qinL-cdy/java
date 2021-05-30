package io.kimmking.dubbo.demo.provider;

import java.util.HashMap;
import java.util.Map;

public class AccountMapper {
    Map<Integer, User> map = new HashMap();

    public boolean payment(Account account) {
        map.get(account.getId()).addUs(account.getUs_wallet()).addCny(account.getCny_wallet());
        return true;
    }
}
