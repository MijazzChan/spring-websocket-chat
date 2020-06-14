package com.zstu.mijazz;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class WebSocketProjectApplication {

    private static Logger logger = LoggerFactory.getLogger(WebSocketProjectApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(WebSocketProjectApplication.class, args);
        logger.info("Service port at -> {}", 8083);
        logger.info("Websocket Endpoint at -> {}", "/chat");
        logger.info("Websocket Subscription (for $username$) -> {}", "/topic/messages/ + $userName$");
        logger.info("Websocket Subscription (for group) -> {}", "/topic/messages/GROUP");
        logger.info("Websocket Sending Address (to $userName$) -> {}", "/app/chat/ + $userName$");
        logger.info("Websocket Sending Address (to group) -> {}", "/app/chat/GROUP");
        logger.info("Access http://localhost:8083/help for Api help");
    }


}
