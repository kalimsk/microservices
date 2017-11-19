package com.cts.service.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

@Service
public class FacebookConnectionSignup implements ConnectionSignUp {
 
   // private UserRepository userRepository;
 
    @Override
    public String execute(Connection<?> connection) {
        /*User user = new User();
        user.setUsername(connection.getDisplayName());
        user.setPassword(randomAlphabetic(8));
        userRepository.save(user);*/
    	System.out.println("signup");
        return connection.getDisplayName();
    }
}
