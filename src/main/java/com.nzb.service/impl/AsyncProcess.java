package com.nzb.service.impl;

import com.nzb.service.IUserReg;
import com.nzb.service.busi.SaveUser;
import com.nzb.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * @author M
 * @create 2018/2/1
 */
@Service
@Qualifier("async")
public class AsyncProcess implements IUserReg {
    private Logger logger = LoggerFactory.getLogger(AsyncProcess.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private SaveUser saveUser;

    public boolean userRegister(User user) {
        try {
            saveUser.saveUser(user);
            rabbitTemplate.send("user-reg-exchange", "email",
                    new Message(user.getEmail().getBytes(), new MessageProperties()));
            rabbitTemplate.send("user-reg-exchange", "sms",
                    new Message(user.getEmail().getBytes(), new MessageProperties()));
        } catch (AmqpException e) {
            logger.error(e.toString());
            return false;
        }
        return true;
    }
}
