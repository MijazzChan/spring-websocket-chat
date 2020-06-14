package com.zstu.mijazz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 14-Jun-20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UploadFileResponseModel {
    private String fileName;

    private String fileDownloadUri;

    private String fileMetaType;

    private Long fileSize;

}
