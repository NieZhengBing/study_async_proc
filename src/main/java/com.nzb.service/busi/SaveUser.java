package com.nzb.service.busi;

import com.nzb.vo.User;
import org.springframework.stereotype.Service;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author M
 * @create 2018/2/1
 */
@Service
public class SaveUser {

    private ConcurrentHashMap<String, User> userData =
            new ConcurrentHashMap<String, User>();

    public void saveUser(User user) {
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        userData.putIfAbsent(user.getUserId(), user);
    }

    public User getUser(String userId) {
        return userData.get(userId);
    }
}
