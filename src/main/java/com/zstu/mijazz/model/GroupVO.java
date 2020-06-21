package com.zstu.mijazz.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 19-Jun-20.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupVO implements Serializable {
    private String groupId;

    private String groupName;

    private List<UserVO> groupMembers;

    private LocalDateTime createTime;

    private String subscriptionUrl;

    public GroupVO(String groupId, String groupName, List<UserVO> groupMembers) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.groupMembers = groupMembers;
        this.createTime = LocalDateTime.now();
        this.subscriptionUrl = "/topic/messages/" + this.groupId;
    }

    public Integer getGroupMemberCount() {
        return this.groupMembers.size();
    }
}
