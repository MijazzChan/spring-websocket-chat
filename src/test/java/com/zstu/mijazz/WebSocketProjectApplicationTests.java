package com.zstu.mijazz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
class WebSocketProjectApplicationTests {

    @Test
    void contextLoads() {
    }

    @Test
    void testDate() {
        System.out.println(LocalDateTime.now());
    }

}
