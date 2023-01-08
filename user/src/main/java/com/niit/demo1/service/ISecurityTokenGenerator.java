package com.niit.demo1.service;

import com.niit.demo1.domain.User;

import java.util.Map;

public interface ISecurityTokenGenerator {
    public Map<String,String> tokenGenerator(User user);
}
