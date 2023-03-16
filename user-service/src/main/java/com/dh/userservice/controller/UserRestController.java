package com.dh.userservice.controller;

import com.dh.userservice.model.User;
import com.dh.userservice.service.UserService;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserRestController {

    private UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }
    @GetMapping("/find/{id}")
    public User findById(@PathVariable Integer id){
        return userService.findById(id);
    }



//    public UserRestController(UserService userService) {
//        this.userService = userService;
//    }
//    @GetMapping("/find/{id}")
//    public User findById(@PathVariable Integer id, @RequestHeader(name = "authorization") String token){
//        System.out.println("User token "+token);
//        return userService.findById(id);
//    }
}
