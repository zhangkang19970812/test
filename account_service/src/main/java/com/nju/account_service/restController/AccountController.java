package com.nju.account_service.restController;

import com.nju.account_service.restController.info.UserInfo;
import com.nju.account_service.restController.vo.ResultVO;
import com.nju.account_service.restController.vo.UserVO;
import com.nju.account_service.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    AccountService accountService;

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public ResultVO health(){
        return new ResultVO("health is ok!", 1);
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public ResultVO add(@RequestBody UserInfo userInfo){
        if (accountService.addUser(userInfo) == 1){
            return new ResultVO("add a user", 1);
        }
        return new ResultVO("fail", 0);
    }

    @RequestMapping(value = "/detail/{uid}", method = RequestMethod.GET)
    public UserVO getUser(@PathVariable Integer uid){
        return accountService.getUser(uid);
    }

    @RequestMapping(value = "/detailByEmail/{uname}", method = RequestMethod.GET)
    public UserVO getUserByEmail(@PathVariable String uname){
        return accountService.getUser(uname);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public ResultVO update(@RequestBody UserInfo userInfo){
        if (accountService.updateUser(userInfo) == 1){
            return new ResultVO("update success", 1);
        }
        return new ResultVO("fail", 0);
    }
}
