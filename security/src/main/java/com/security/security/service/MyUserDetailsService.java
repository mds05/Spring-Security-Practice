package com.security.security.service;

import com.security.security.model.UserData;
import com.security.security.model.Users;
import com.security.security.repo.UserRepo;

import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService{
    @Autowired
    private UserRepo repo;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users user=repo.findByUsername(username);
        if(user==null){
            System.out.println("user not found");
            throw new UsernameNotFoundException("user not found");
        }
        else{
        
            System.out.println("user found");
            return new UserData(user);
        }
    }
    
}
