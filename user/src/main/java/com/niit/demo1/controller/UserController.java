package com.niit.demo1.controller;

import com.niit.demo1.service.ISecurityTokenGenerator;
import com.niit.demo1.service.IUserService;
import com.niit.demo1.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class UserController {


    ISecurityTokenGenerator securityTokenGenerator;
    IUserService IUserService;
    @Autowired
    public UserController(ISecurityTokenGenerator securityTokenGenerator, com.niit.demo1.service.IUserService IUserService) {
        this.securityTokenGenerator = securityTokenGenerator;
        this.IUserService = IUserService;
    }



    @PostMapping("/user")
    public ResponseEntity<?>saveUser(@RequestBody User user){
        return new ResponseEntity<>(IUserService.addUser(user), HttpStatus.CREATED);
    }
    @PostMapping("/login")
    public ResponseEntity<?>loginCheck(@RequestBody User user){
        User result=IUserService.loginCheck(user.getEmail(),user.getPassword());
        if(result!=null){
            Map<String,String> map=securityTokenGenerator.tokenGenerator(result);
            return new ResponseEntity<>(map,HttpStatus.OK);
        }else{
            return new ResponseEntity<>("invalid user or user does not exist",HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/user")
    public ResponseEntity<?>getAllUser(){
        return new ResponseEntity<>(IUserService.getAllUser(),HttpStatus.OK);
    }
    @DeleteMapping("/user/{email}")
    public ResponseEntity<?> deleteUser(@PathVariable String email){
        return new ResponseEntity<>(IUserService.deleteUser(email),HttpStatus.OK);
    }
   @PutMapping("/user")
   public ResponseEntity<?>updateUser(@RequestBody User user){
       return new ResponseEntity<>(IUserService.updateUser(user),HttpStatus.OK);
   }
    @GetMapping("/user/{firstname}")
   public ResponseEntity<?>getAllUserByFirstname(@PathVariable String firstname){
        return new ResponseEntity<>(IUserService.getAllUserByFirstName(firstname),HttpStatus.FOUND);
    }
}
