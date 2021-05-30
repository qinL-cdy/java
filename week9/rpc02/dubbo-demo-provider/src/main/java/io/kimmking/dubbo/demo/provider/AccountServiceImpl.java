package io.kimmking.dubbo.demo.provider;

import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("accountService")
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountMapper accountMapper;

    @Override
    @HmilyTCC(confirmMethod = "confirm", cancelMethod = "cancel")
    public boolean pay(Account account) {
        boolean isSuccess = accountMapper.payment(account);
        return isSuccess;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean confirm(Account account) {
        return true;
    }

    @Transactional(rollbackFor = Exception.class)
    public boolean cancel(Account account) {
        return true;
    }
}