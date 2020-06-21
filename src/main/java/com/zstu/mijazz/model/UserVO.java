package com.zstu.mijazz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 6/7/2020.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserVO implements Serializable, Comparable {
    private String userName;

    private Integer userSex;

    private String ipAddr;

    private Date registerTime;

    private String avatarId;

    public UserVO(String userName, Integer userSex, String ipAddr, String avatarId) {
        this.userName = userName;
        this.userSex = userSex;
        this.ipAddr = ipAddr;
        this.avatarId = avatarId;
        this.registerTime = new Date();
    }

    public static UserVO getAnonymousInstance() {
        return new UserVO("UNKNOWN_USER", 1, "0.0.0.0", "1");
    }

    public static UserVO getGroupInstance() {
        return new UserVO("GROUP", 1, "0.0.0.0", "5");
    }

    public static UserVO getRobotInstance(){
        return new UserVO("ROBOT", 0, "0.0.0.0", "0");
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof UserVO) {
            return this.userName.compareTo(((UserVO) o).userName);
        }
        return 0;
    }
}
