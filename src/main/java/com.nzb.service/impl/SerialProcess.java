package com.nzb.service.impl;/**
 * Created by M on 2018/2/1.
 */

import com.nzb.service.IUserReg;
import com.nzb.service.busi.SaveUser;
import com.nzb.service.busi.SendEmail;
import com.nzb.service.busi.SendSms;
import com.nzb.vo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author M
 * @create 2018/2/1
 */
@Service
@Qualifier("serial")
public class SerialProcess implements IUserReg {
    @Autowired
    private SaveUser saveUser;
    @Autowired
    private SendEmail sendEmail;
    @Autowired
    private SendSms sendSms;

    public boolean userRegister(User user) {
        try {
            saveUser.saveUser(user);
            sendEmail.sendEmail(user.getEmail());
            sendSms.sendSms(user.getPhoneNumber());
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
