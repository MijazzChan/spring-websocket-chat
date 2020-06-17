package com.zstu.mijazz;

import com.zstu.mijazz.robots.CallRobotExternalApi;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class WebSocketProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testRobot() {
        System.out.println(new CallRobotExternalApi().getRobotReply("你是谁"));
    }

}
