package com.zstu.mijazz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.zstu.mijazz.storage.UserStorage;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.catalina.User;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 10-Jun-20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageTO implements Serializable {
    private UserVO userVO;

    private String msgType;

    private String msgContent;

    private LocalDateTime msgTimeStamp;

    public MessageTO(MessageFrom messageFrom) {
        this.userVO = null;
        this.userVO = UserStorage.getInstance().getUser(messageFrom.getUserName());
        if (this.userVO == null) {
            this.userVO = UserVO.getAnonymousInstance();
        }
        this.msgType = messageFrom.getMsgType();
        this.msgContent = messageFrom.getMsgContent();
        this.msgTimeStamp = LocalDateTime.now();
    }

    public static MessageTO getRobotMessageTO(String msgContent) {
        MessageTO messageTO = new MessageTO();
        messageTO.setMsgType("0");
        messageTO.setMsgContent(msgContent);
        messageTO.setUserVO(UserVO.getRobotInstance());
        messageTO.setMsgTimeStamp(LocalDateTime.now());
        return messageTO;
    }

}
