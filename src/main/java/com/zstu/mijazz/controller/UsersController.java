package com.zstu.mijazz.controller;

import com.zstu.mijazz.model.OrderedContactModel;
import com.zstu.mijazz.model.ResponseModel;
import com.zstu.mijazz.model.SystemTO;
import com.zstu.mijazz.model.UserVO;
import com.zstu.mijazz.storage.UserStorage;
import io.swagger.annotations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Set;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 10-Jun-20.
 */

@Api(description = "用户接口")
@RestController
@CrossOrigin()
public class UsersController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    private static Logger logger = LoggerFactory.getLogger(UsersController.class);

    @ApiOperation(value = "注册接口", notes = "用户进入socket之前的信息初始化", produces = "application/json")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "username", required = true, value = "testuser"),
            @ApiImplicitParam(name = "avatarid", required = false, defaultValue = "1"),
            @ApiImplicitParam(name = "usersex", required = false, defaultValue = "1")
    })
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功且+用户对象"),
            @ApiResponse(code = 0, message = "失败+null")
    })
    @PostMapping(value = "/registration", produces = "application/json")
    public ResponseModel<UserVO> register(@RequestParam(value = "username", required = true) String userName,
                                          @RequestParam(value = "avatarid", required = false, defaultValue = "1") String avatarId,
                                          @RequestParam(value = "usersex", required = false, defaultValue = "1") String userSex,
                                          HttpServletRequest httpServletRequest) {
        logger.info("Handling registration, userName-> {}", userName);
        UserVO userVO = new UserVO(userName, Integer.parseInt(userSex), httpServletRequest.getRemoteAddr(), avatarId);
        boolean isSuccess = UserStorage.getInstance().setUser(userName, userVO);
        if (isSuccess) {
            simpMessagingTemplate.convertAndSend("/topic/messages/system", new SystemTO("11", userName));
            return new ResponseModel<>(1, userVO);
        }else {
            return new ResponseModel<>(0, userVO);
        }
    }

    @ApiOperation(value = "用户列表", notes = "返回所有用户用户名的列表")
    @GetMapping("/fetchAllUsers")
    public Set<String> fetchAll() {
        return UserStorage.getInstance().getUsers();
    }

    @ApiOperation(value = "返回有序用户列表", notes = "带有拼音首字母的用户名列表, 缺省用#")
    @GetMapping("/fetchAllUsersWithOrder")
    public ResponseModel<OrderedContactModel> fetchWithOrder() {
        return new ResponseModel<>(1, new OrderedContactModel());
    }

    @ApiOperation(value = "用户计数", notes = "返回计数")
    @GetMapping("/getusercount")
    public String getUserCount() {
        String count = UserStorage.getInstance().getUserCount().toString();
        logger.info("Handling Counting, Count users -> {}", count);
        return count;
    }

    @ApiOperation(value = "用户信息查询", notes = "返回用户基本信息对象, 或判断用户是否存在", produces = "application/json")
    @ApiImplicitParam(name = "username", required = true, value = "testuser")
    @ApiResponses({
            @ApiResponse(code = 1, message = "存在且+用户对象"),
            @ApiResponse(code = 0, message = "无+null")
    })
    @PostMapping(value = "/getuserinfo", produces = "application/json")
    public ResponseModel<UserVO> getUserInfo(@RequestParam("username") String userName) {
        UserVO userVO = null;
        boolean isUserExists = UserStorage.getInstance().isDuplicateUser(userName);
        if (isUserExists) {
            userVO = UserStorage.getInstance().getUser(userName);
        }
        return new ResponseModel<>(isUserExists ? 1 : 0, userVO);
    }

    @ApiOperation(value = "用户下线", notes = "退出系统, 未作身份验证！")
    @ApiImplicitParam(name = "username", required = true, value = "testuser")
    @ApiResponses({
            @ApiResponse(code = 1, message = "成功"),
            @ApiResponse(code = 0, message = "失败未知错误")
    })
    @GetMapping("/logout")
    public ResponseModel<String> logoutSystem(@RequestParam("username") String userName) {
        boolean isUserExists = UserStorage.getInstance().isDuplicateUser(userName);
        if (isUserExists) {
            UserStorage.getInstance().deleteUser(userName);
            logger.info("Handling Logout, userName-> {}", userName);
            simpMessagingTemplate.convertAndSend("/topic/messages/system", new SystemTO("12", userName));
            return new ResponseModel<>(1, "Success");
        }else {
            return new ResponseModel<>(0, "Unexpected Failure");
        }
    }



}
