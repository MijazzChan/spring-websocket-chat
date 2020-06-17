package com.zstu.mijazz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 10-Jun-20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageFrom implements Serializable {
    private String userName;

    private String msgType; //2

    private String msgContent;
}
