package com.zstu.mijazz.storage;

import com.zstu.mijazz.model.UserVO;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class UserStorage {

    private static Map<String, UserVO> userVOMap;

    private static UserStorage instance;

    private UserStorage() {
        userVOMap = new ConcurrentHashMap<>(32);
        userVOMap.put("GROUP", UserVO.getAnonymousInstance());
    }

    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }

    public Set<String> getUsers() {
        return userVOMap.keySet();
    }

    public Integer getUserCount() {
        return userVOMap.size();
    }

    public boolean setUser(String userName, UserVO userVO) {
        if (userVOMap.containsKey(userName)) {
            return false;
        }
        userVOMap.put(userName, userVO);
        return true;
    }

    public boolean isDuplicateUser(String userName) {
        return userVOMap.containsKey(userName);
    }

    public UserVO getUser(String userName) {
        return userVOMap.getOrDefault(userName, null);
    }

    public void deleteUser(String userName) {
        userVOMap.remove(userName);
        return;
    }
}
