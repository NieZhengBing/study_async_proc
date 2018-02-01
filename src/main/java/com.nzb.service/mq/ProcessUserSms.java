package com.nzb.service.mq;/**
 * Created by M on 2018/2/1.
 */

import com.nzb.service.busi.SendSms;
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
public class ProcessUserSms implements MessageListener {
    private Logger logger = LoggerFactory.getLogger(ProcessUserSms.class);

    @Autowired
    private SendSms sendSms;
    public void onMessage(Message message) {
        logger.info("accept message, ready process......");
        sendSms.sendSms(new String(message.getBody()));
    }
}
