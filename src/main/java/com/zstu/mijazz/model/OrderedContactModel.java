package com.zstu.mijazz.model;

import com.zstu.mijazz.storage.UserStorage;
import com.zstu.mijazz.utils.PinYinUtil;
import lombok.Data;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 21-Jun-20.
 */
@Data
public class OrderedContactModel {
    private ConcurrentHashMap<String, List<UserVO>> contactMap;

    public OrderedContactModel() {
        this.contactMap = new ConcurrentHashMap<String, List<UserVO>>(32);
        Set<String> nameSet = UserStorage.getInstance().getUsers();
        for (String name : nameSet) {
            String firstPinyin = PinYinUtil.getHeadPinYin(name);
            if (!contactMap.containsKey(firstPinyin)) {
                ArrayList<UserVO> tmpArray = new ArrayList<UserVO>();
                tmpArray.add(UserStorage.getInstance().getUser(name));
                contactMap.put(firstPinyin, tmpArray);
            } else {
                contactMap.get(firstPinyin).add(UserStorage.getInstance().getUser(name));
            }
        }
    }
}
