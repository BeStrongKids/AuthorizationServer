package com.bestrongkids.chikingauthorizationserver.controller;

import com.bestrongkids.chikingauthorizationserver.dto.UserDto;
import com.bestrongkids.chikingauthorizationserver.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;
    @PostMapping("/register")
    public String userRegister(@RequestBody UserDto user){
        return userService.userRegister(user);
    }

}
