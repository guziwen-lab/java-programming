package com.lagou.edu.service.impl;

import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.dao.impl.JdbcAccountDaoImpl;
import com.lagou.edu.factory.BeanFactory;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.TransferService;
import com.lagou.edu.utils.ConnectionUtils;
import com.lagou.edu.utils.TransactionManager;

import java.sql.Connection;

/**
 * @author 应癫
 */
public class TransferServiceImpl implements TransferService {

    //    private AccountDao accountDao = new JdbcAccountDaoImpl();
//    private AccountDao accountDao = BeanFactory.getBean("accountDao");
    private AccountDao accountDao;

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
        TransactionManager.beginTransaction();

        try {
            Account from = accountDao.queryAccountByCardNo(fromCardNo);
            Account to = accountDao.queryAccountByCardNo(toCardNo);

            from.setMoney(from.getMoney() - money);
            to.setMoney(to.getMoney() + money);

            accountDao.updateAccountByCardNo(to);
            int i = 10 / 0;
            accountDao.updateAccountByCardNo(from);

            TransactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionManager.rollback();
            throw e;
        }
    }
}
