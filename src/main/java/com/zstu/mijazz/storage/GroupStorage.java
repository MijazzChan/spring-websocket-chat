package com.zstu.mijazz.storage;

import com.zstu.mijazz.model.GroupVO;
import com.zstu.mijazz.model.UserVO;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 19-Jun-20.
 */

public class GroupStorage {

    private static Map<String, GroupVO> groupVOMap;

    private static GroupStorage instance;

    private GroupStorage() {
        groupVOMap = new ConcurrentHashMap<>(32);
    }

    public static synchronized GroupStorage getInstance() {
        if (instance == null) {
            instance = new GroupStorage();
        }
        return instance;
    }

    public static synchronized void delInstance() {
        instance = null;
        return;
    }

    public Set<String> getGroups() {
        return groupVOMap.keySet();
    }

    public Integer getGroupCount() {
        return groupVOMap.size();
    }

    public boolean setGroup(String groudId, GroupVO groupVO) {
        if (groupVOMap.containsKey(groudId)) {
            return false;
        }
        groupVOMap.put(groudId, groupVO);
        return true;
    }

    public boolean isDuplicateGroup(String groupId) {
        return groupVOMap.containsKey(groupId);
    }

    public GroupVO getGroup(String groupId) {
        return groupVOMap.getOrDefault(groupId, null);
    }

    public void deleteGroup(String groupId) {
        groupVOMap.remove(groupId);
        return;
    }
}
