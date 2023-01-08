package com.niit.demo1.service;

import com.niit.demo1.domain.User;

import java.util.List;

public interface IUserService {
    public User addUser(User user);
    public List<User> getAllUser();
    public User updateUser(User user);
    public String deleteUser(String email);
    public List<User>getAllUserByFirstName(String firstname);
    public User loginCheck(String emailId,String password);

}
