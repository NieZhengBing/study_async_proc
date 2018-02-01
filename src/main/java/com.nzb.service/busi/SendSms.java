package com.nzb.service.busi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author M
 * @create 2018/2/1
 */
@Service
public class SendSms {
    private Logger logger = LoggerFactory.getLogger(SendSms.class);

    public void sendSms(String phoneNumber) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("-------------------Already Send Sms to " + phoneNumber);
    }
}
