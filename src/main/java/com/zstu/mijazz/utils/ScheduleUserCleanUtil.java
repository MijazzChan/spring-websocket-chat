package com.zstu.mijazz.utils;

import com.zstu.mijazz.storage.UserStorage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 24-Jun-20.
 */
@Component
public class ScheduleUserCleanUtil {
    @Scheduled(cron = "0 */5 * * * *")
    public void cleanUserScheduleTask() {
        if (UserStorage.getInstance().getUserCount() < 3) {
            return;
        }
        long now = new Date().getTime();
        long hour1 = 60 * 60 * 1000L;
        for (String user : UserStorage.getInstance().getUsers()) {
            if (UserStorage.getInstance().getUser(user).getRegisterTime().getTime() - now > hour1) {
                UserStorage.getInstance().deleteUser(user);
            }
        }
        return;
    }
}
