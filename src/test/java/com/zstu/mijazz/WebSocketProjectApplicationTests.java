package com.zstu.mijazz;

import com.zstu.mijazz.robots.CallRobotExternalApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.Date;

@SpringBootTest
class WebSocketProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testDate() {
        Date m = new Date();
        Long times = m.getTime() - new Date().getTime();
        System.out.println(times);
    }


}
