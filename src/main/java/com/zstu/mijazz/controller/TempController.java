package com.zstu.mijazz.controller;

import com.zstu.mijazz.model.UserVO;
import com.zstu.mijazz.storage.UserStorage;
import io.swagger.annotations.Api;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author MijazzChan
 * @stuID ZSTU.2017326603075
 * Created on 10-Jun-20.
 */
@Controller
@Api(hidden = true)
public class TempController {
    @GetMapping("/help")
    public String help() {
        return "redirect:/swagger-ui.html";
    }

    @GetMapping("/restart")
    public void restart() {
        System.exit(0);
    }

    @GetMapping("/cleanalluser")
    public ResponseEntity<String> cleanalluser() {
        UserStorage.delInstance();
        return ResponseEntity.ok().build();
    }
}
