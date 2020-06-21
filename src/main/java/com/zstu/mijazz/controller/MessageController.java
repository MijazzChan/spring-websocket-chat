package com.zstu.mijazz.controller;

import com.zstu.mijazz.model.MessageFrom;
import com.zstu.mijazz.model.MessageTO;
import com.zstu.mijazz.model.UserVO;
import com.zstu.mijazz.robots.CallRobotExternalApi;
import com.zstu.mijazz.storage.UserStorage;
import io.swagger.annotations.Api;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 10-Jun-20.
 */

@RestController
public class MessageController {

    private static Logger logger = LoggerFactory.getLogger(MessageController.class);

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private CallRobotExternalApi callRobotExternalApi;

    @Value("${lovechina.toggle}")
    private boolean loveChina;

    @MessageMapping("/chat/{to}")
    public void sendMessage(@DestinationVariable String to, MessageFrom from) {
        logger.info("Handling imcoming message {} -> {}", from.getUserName() , to);
        boolean isExists = UserStorage.getInstance().isDuplicateUser(to);
        if (isExists) {
            logger.info("Successfully deliver message -> {}", to);
            simpMessagingTemplate.convertAndSend("/topic/messages/" + to, new MessageTO(from, loveChina));
        }else {
            logger.warn("Destination username {} not exist/online, check username", to);
        }
        return;
    }

    @MessageMapping("/chat/GROUP")
    public void sendGroupMessage(MessageFrom from) {
        logger.info("Handling incoming group message {} -> GROUP", from.getUserName());
        simpMessagingTemplate.convertAndSend("/topic/messages/GROUP", new MessageTO(from, loveChina));
        return;
    }

    @MessageMapping("/chat/ROBOT")
    public void sendRobotMessage(MessageFrom from) {
        logger.info("Handling incoming robot message from {}", from.getUserName());
        robotSendMessage(from);
        return;
    }

    public void robotSendMessage(MessageFrom from) {
        String robotReply = callRobotExternalApi.getRobotReply(from.getMsgContent());
        logger.info("Robot message callback {}", robotReply);
        simpMessagingTemplate.convertAndSend("/topic/messages/" + from.getUserName(), MessageTO.getRobotMessageTO(robotReply));
        return;
    }
}
