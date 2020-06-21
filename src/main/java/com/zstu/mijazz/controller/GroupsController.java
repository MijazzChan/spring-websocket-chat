package com.zstu.mijazz.controller;

import com.zstu.mijazz.model.GroupVO;
import com.zstu.mijazz.model.ResponseModel;
import com.zstu.mijazz.model.UserVO;
import com.zstu.mijazz.storage.GroupStorage;
import com.zstu.mijazz.storage.UserStorage;
import io.swagger.annotations.Api;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 19-Jun-20.
 */
@Api(description = "群组接口")
@RestController
@CrossOrigin()
public class GroupsController {

    @PostMapping("/allocGroup")
    public ResponseModel<GroupVO> allocGroup(@RequestBody Map<String, String> paramsMap) {
        String preAllocGroupId = "GROUP" + UUID.randomUUID().toString().replace("-", "").substring(0, 10);;
        String preAllocGroupName = "临时群组";
        if (paramsMap.containsKey("groupname") && !StringUtils.isEmpty(paramsMap.getOrDefault("groupname", ""))){
            preAllocGroupName = paramsMap.get("groupname");
            paramsMap.remove("groupname");
        }
        ArrayList<UserVO> groupMembers = new ArrayList<>(20);
        for (String userName : paramsMap.keySet()) {
            if (UserStorage.getInstance().isDuplicateUser(userName)) {
                groupMembers.add(UserStorage.getInstance().getUser(userName));
            }
        }
        if (groupMembers.size() < 2) {
            return new ResponseModel<>(0, null);
        }
        boolean isSuccess = false;
        GroupVO groupVO = null;
        if (!GroupStorage.getInstance().isDuplicateGroup(preAllocGroupId)) {
            groupVO = new GroupVO(preAllocGroupId, preAllocGroupName, groupMembers);
            GroupStorage.getInstance().setGroup(preAllocGroupId, groupVO);
            isSuccess = true;
        }
        return new ResponseModel<>(isSuccess ? 1 : 0, groupVO);
    }
}
