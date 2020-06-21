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
    private TreeMap<String, List<String>> contactMap;

    public OrderedContactModel() {
        this.contactMap = new TreeMap<String, List<String>>();
        Set<String> nameSet = UserStorage.getInstance().getUsers();
        for (String name : nameSet) {
            String firstPinyin = PinYinUtil.getHeadPinYin(name);
            if (!contactMap.containsKey(firstPinyin)) {
                ArrayList<String> tmpArray = new ArrayList<String>();
                tmpArray.add(name);
                contactMap.put(firstPinyin, tmpArray);
            } else {
                contactMap.get(firstPinyin).add(name);
            }
        }
        for (List<String> nameList : contactMap.values()) {
            Collections.sort(nameList);
        }
    }
}
