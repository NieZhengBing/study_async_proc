package com.nzb.controller;

import com.nzb.service.IUserReg;
import com.nzb.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author M
 * @create 2018/2/1
 */
@Controller
public class UserRegController {
    private static final String SUCCESS = "suc";
    private static final String FAILURE = "failure";

    @Autowired
    @Qualifier("para")
    private IUserReg userReg;

    @RequestMapping("/userReg")
    public String userReg() {
        return "index";
    }

    @RequestMapping("/saveUser")
    @ResponseBody
    public String saveUser(@RequestParam("userName") String userName,
                           @RequestParam("email") String email,
                           @RequestParam("phoneNumber") String phoneNumber) {
        try {
            if (userReg.userRegister(User.makeUser(userName, email, phoneNumber))) {
                return SUCCESS;
            }
            return FAILURE;
        } catch (Exception e) {
            return FAILURE;
        }
    }
}
