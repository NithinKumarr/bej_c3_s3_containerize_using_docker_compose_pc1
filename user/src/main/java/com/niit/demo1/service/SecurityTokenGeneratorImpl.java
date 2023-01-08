package com.niit.demo1.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import com.niit.demo1.domain.User;
import org.springframework.stereotype.Service;

import java.security.Signature;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
@Service
public class SecurityTokenGeneratorImpl implements ISecurityTokenGenerator {

    @Override
    public Map<String, String> tokenGenerator(User user) {
        String jwtToken = null;
        jwtToken = Jwts.builder().setSubject(user.getFirstName()).setIssuedAt(new Date())
              .signWith(SignatureAlgorithm.HS256, "securityKey").compact();
        Map<String, String> map = new HashMap<>();
        map.put("token", jwtToken);
        map.put("message", "user logged in seccefully");
        return map;
    }
}