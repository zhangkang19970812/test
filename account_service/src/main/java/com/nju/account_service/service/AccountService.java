package com.nju.account_service.service;

import com.nju.account_service.dao.AccountDAO;
import com.nju.account_service.model.AccountEntity;
import com.nju.account_service.restController.info.UserInfo;
import com.nju.account_service.restController.vo.UserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    @Autowired
    AccountDAO accountDAO;

    public int addUser(UserInfo userInfo){
        AccountEntity accountEntity = new AccountEntity();
        Integer id = accountDAO.findMaxId();
        if (id == null) id = 0;
        id++;
        accountEntity.setId(id);
        accountEntity.setEmail(userInfo.getEmail());
        accountEntity.setPassword(userInfo.getPassword());
        accountEntity.setUsername(userInfo.getUsername());
        accountDAO.save(accountEntity);
        accountDAO.flush();
        return 1;
    }

    public UserVO getUser(int id){
        AccountEntity accountEntity = accountDAO.findOne(id);
        UserVO userVO = new UserVO(accountEntity.getUsername(),
                accountEntity.getId(),
                accountEntity.getPassword(),
                accountEntity.getEmail());
        return userVO;
    }

    public UserVO getUser(String username){
        AccountEntity accountEntity = accountDAO.findAccountEntityByUsername(username);
        UserVO userVO = new UserVO(accountEntity.getUsername(),
                accountEntity.getId(),
                accountEntity.getPassword(),
                accountEntity.getEmail());
        return userVO;
    }

    public int updateUser(UserInfo userInfo){
        AccountEntity accountEntity = accountDAO.findOne(userInfo.getId());
        accountEntity.setEmail(userInfo.getEmail());
        accountEntity.setPassword(userInfo.getPassword());
        accountEntity.setUsername(userInfo.getUsername());
        return 1;
    }

}
