package com.nzb.service.busi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @author M
 * @create 2018/2/1
 */
@Service
public class SendEmail {
    private Logger logger = LoggerFactory.getLogger(SendEmail.class);

    public void sendEmail(String email) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("-----------------------Already send email to " + email);
    }
}
