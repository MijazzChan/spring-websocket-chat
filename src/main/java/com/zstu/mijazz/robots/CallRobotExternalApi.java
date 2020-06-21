package com.zstu.mijazz.robots;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 17-Jun-20.
 */
@Service
public class CallRobotExternalApi {
    private static Logger logger = LoggerFactory.getLogger(CallRobotExternalApi.class);

    public static final String ROBOT_API = "http://api.qingyunke.com/api.php?key=free&appid=0&msg={message}";

    public static final String ROBOT_DEAD_REPLY = "我脑子瓦特了, 晚点再来";

    public String getRobotReply(String msgContent){
        if (StringUtils.isEmpty(msgContent)) {
            return ROBOT_DEAD_REPLY;
        }
        RestTemplate restTemplate = new RestTemplate();
        MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();
        mappingJackson2HttpMessageConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_OCTET_STREAM, MediaType.TEXT_HTML));
        restTemplate.getMessageConverters().add(mappingJackson2HttpMessageConverter);
        Map<String, String> messageParams = new HashMap<>();
        messageParams.put("message", msgContent);
        RobotResponseObject response = restTemplate.getForObject(ROBOT_API, RobotResponseObject.class, messageParams);
        logger.info("Robot");
        return "0".equals(response.result) ? response.getContent() : ROBOT_DEAD_REPLY;
    }


}
