package com.security.security.Controller;

import com.security.security.model.Users;
import com.security.security.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class UserController {
    @Autowired
    private UserService serv;

    @PostMapping("/register")
    public Users register(@RequestBody Users user){
        return serv.log(user);
    }
    @PostMapping("/login")
    public String login(@RequestBody Users user){
        return serv.verify(user);
    }
    
}
