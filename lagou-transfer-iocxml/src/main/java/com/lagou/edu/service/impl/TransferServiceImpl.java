package com.lagou.edu.service.impl;

import com.lagou.edu.dao.AccountDao;
import com.lagou.edu.pojo.Account;
import com.lagou.edu.service.TransferService;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

/**
 * @author 应癫
 */
public class TransferServiceImpl implements TransferService {

    private AccountDao accountDao;

    private String name;

    private Integer age;

    private String[] myArray;

    private Map<String, String> myMap;

    private Set<String> mySet;

    private Properties myProperties;

    public TransferServiceImpl() {
    }

    public TransferServiceImpl(AccountDao accountDao, String name, Integer age) {
        this.accountDao = accountDao;
        this.name = name;
        this.age = age;
    }

    public void setAccountDao(AccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public void setMyArray(String[] myArray) {
        this.myArray = myArray;
    }

    public void setMyMap(Map<String, String> myMap) {
        this.myMap = myMap;
    }

    public void setMySet(Set<String> mySet) {
        this.mySet = mySet;
    }

    public void setMyProperties(Properties myProperties) {
        this.myProperties = myProperties;
    }

    @Override
    public void transfer(String fromCardNo, String toCardNo, int money) throws Exception {
       /* try {
            TransactionManager.beginTransaction();*/

        Account from = accountDao.queryAccountByCardNo(fromCardNo);
        Account to = accountDao.queryAccountByCardNo(toCardNo);

        from.setMoney(from.getMoney() - money);
        to.setMoney(to.getMoney() + money);

        accountDao.updateAccountByCardNo(to);
//        int i = 10 / 0;
        accountDao.updateAccountByCardNo(from);

     /*       TransactionManager.commit();
        } catch (Exception e) {
            e.printStackTrace();
            TransactionManager.rollback();
            throw e;
        }*/
    }
}
