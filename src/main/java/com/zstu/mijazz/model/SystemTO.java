package com.zstu.mijazz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 20-Jun-20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SystemTO implements Serializable {
    private String msgType;

    private String msgTypeHelp;

    private String msgContent;

    private static String getTypeHelp(String msgType) {
        switch (Integer.parseInt(msgType)) {
            case 11:
                return "用户上线, content为上线用户名";
            case 12:
                return "用户下线, content为下线用户名";
//            case 21:
//                return "你被邀请到临时小群组中, subscribe content里的链接以获得消息";
            default:
                break;
        }
        return "拓展中";
    }

    public SystemTO(String msgType, String msgContent) {
        this.msgType = msgType;
        this.msgTypeHelp = SystemTO.getTypeHelp(msgType);
        this.msgContent = msgContent;
    }
}
