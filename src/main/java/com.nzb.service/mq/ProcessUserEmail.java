package com.nzb.service.mq;

import com.nzb.service.busi.SendEmail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author M
 * @create 2018/2/1
 */
@Component
public class ProcessUserEmail implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(ProcessUserEmail.class);

    @Autowired
    private SendEmail sendEmail;
    public void onMessage(Message message) {
        logger.info("accept message, ready process......");
        sendEmail.sendEmail(new String(message.getBody()));
    }
}
