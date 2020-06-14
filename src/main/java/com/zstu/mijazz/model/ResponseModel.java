package com.zstu.mijazz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 6/7/2020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseModel<T> {
    private Integer responseStatus;

    private T responseContent;

}
