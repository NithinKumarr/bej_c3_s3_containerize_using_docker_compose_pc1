package com.niit.demo1.service;

import com.niit.demo1.domain.User;
import com.niit.demo1.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.logging.log4j.ThreadContext.isEmpty;

@Service
public class IUserServiceImpl implements IUserService {
    @Autowired
    public IUserServiceImpl(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    IUserRepository userRepository;

    @Override
    public User addUser(User user) {
        if (userRepository.findById(user.getEmail()).isEmpty()) {
            return userRepository.save(user);
        }
        return null;
    }
    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }
    @Override
    public User updateUser(User user) {
        if (userRepository.findById(user.getEmail()).isEmpty()) {
            return null;
        }
        User tempUser = userRepository.findById(user.getEmail()).get();
        tempUser.setFirstName(user.getFirstName());
        tempUser.setLastName(user.getLastName());
        tempUser.setPassword(user.getPassword());
        return userRepository.save(tempUser);
    }
    @Override
    public String deleteUser(String email) {
        if (userRepository.findById(email).isEmpty()) {
            return "user does not exist";
        }
        userRepository.deleteById(email);
        return "user deleted succefully";
    }
    @Override
    public List<User> getAllUserByFirstName(String firstname) {
        return userRepository.findByfirstName(firstname);
    }

    @Override
    public User loginCheck(String emailId, String password) {
        //check user is present or not
        if(userRepository.findById(emailId).isPresent()) {
            //fetch user object by using emailId
            User user = userRepository.findById(emailId).get();
            //checkpassword
            if (User.getPassword().equals(password)) {
                //valid user
                return user;
            } else {
                //invalid user
                return null;
            }
        }else{
            //if user does not exist
            return null;
        }

    }
}

