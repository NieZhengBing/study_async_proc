package com.nzb.service.impl;

import com.nzb.service.IUserReg;
import com.nzb.service.busi.SaveUser;
import com.nzb.service.busi.SendEmail;
import com.nzb.service.busi.SendSms;
import com.nzb.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * @author M
 * @create 2018/2/1
 */
@Service
@Qualifier("para")
public class ParalllerProcess implements IUserReg{
    private static Logger logger = LoggerFactory.getLogger(ParalllerProcess.class);

    @Autowired
    private SaveUser saveUser;
    @Autowired
    private SendEmail sendEmail;
    @Autowired
    private SendSms sendSms;

    private static class SendEmailThread implements Callable<Boolean> {
        private SendEmail sendEmail;
        private String email;

        public SendEmailThread(SendEmail sendEmail, String email) {
            this.sendEmail = sendEmail;
            this.email = email;
        }

        public Boolean call() throws Exception {
            sendEmail.sendEmail(email);
            logger.info("SendEmailThread send mail to " + email);
            return true;
        }
    }

    private static class SendSmsThread implements Callable<Boolean> {
        private SendSms sendSms;
        private String phoneNumber;

        public SendSmsThread(SendSms sendSms, String phoneNumber) {
            this.sendSms = sendSms;
            this.phoneNumber = phoneNumber;
        }

        public Boolean call() throws Exception {
            sendSms.sendSms(phoneNumber);
            logger.info("SendSmsThread send mail to " + phoneNumber);
            return true;
        }
    }

    public boolean userRegister(User user) {
        FutureTask<Boolean> sendEmailFuture =
                new FutureTask<Boolean>(new SendEmailThread(sendEmail, user.getEmail()));
        FutureTask<Boolean> sendSmsFuture =
                new FutureTask<Boolean>(new SendSmsThread(sendSms, user.getPhoneNumber()));

        saveUser.saveUser(user);
        new Thread(sendEmailFuture).start();
        new Thread(sendSmsFuture).start();
        try {
            sendEmailFuture.get();
            sendSmsFuture.get();
            return true;
        } catch (InterruptedException e) {
            logger.error(e.toString());
            return false;
        } catch (ExecutionException e) {
            logger.error(e.toString());
            return false;
        }
        return false;
    }


}
