package io.kimmking.dubbo.demo.provider;

import io.kimmking.dubbo.demo.api.TransactionService;
import org.dromara.hmily.annotation.HmilyTCC;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    AccountService accountService;

    @Override
    @HmilyTCC(confirmMethod = "confirmOrderStatus", cancelMethod = "cancelOrderStatus")
    public void transaction() {
        transactionA();
        transactionB();
    }

    private void transactionA() {
        Account account = new Account();
        account.setId(1);
        account.setUs_wallet(-1);
        account.setCny_wallet(7);
        accountService.pay(account);
    }

    private void transactionB() {
        Account account = new Account();
        account.setId(2);
        account.setUs_wallet(1);
        account.setCny_wallet(-7);
        accountService.pay(account);
    }
}