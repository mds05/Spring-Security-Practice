package com.security.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.security.security.model.Users;
import com.security.security.repo.UserRepo;

@Service
public class UserService {
    @Autowired
    private UserRepo repo;
    @Autowired
    private AuthenticationManager authManager;
    @Autowired
    private JWTService jwt;
    private BCryptPasswordEncoder encoder=new BCryptPasswordEncoder(12);
    public Users log(Users user){
        String pass=encoder.encode(user.getPassword());
        user.setPassword(pass);
        return repo.save(user);
    }
    public String verify(Users user) {
        Authentication authentication=authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword()));
        if(authentication.isAuthenticated()) return jwt.generateToken(user.getUsername());
        else return "unknown user";
    }
}
