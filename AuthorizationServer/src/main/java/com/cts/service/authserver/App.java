package com.cts.service.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.connect.mem.InMemoryUsersConnectionRepository;
import org.springframework.social.connect.web.ProviderSignInController;

@SpringBootApplication
//@EnableDiscoveryClient
public class App 
{
    public static void main( String[] args ) {
       SpringApplication.run(App.class, args);
    }
    
    @Autowired
    private ConnectionFactoryLocator connectionFactoryLocator;
 
    @Autowired
    private UsersConnectionRepository usersConnectionRepository;
 
    @Autowired
    private FacebookConnectionSignup facebookConnectionSignup;
    
    @Bean
    public ProviderSignInController providerSignInController() {
        ((InMemoryUsersConnectionRepository) usersConnectionRepository)
          .setConnectionSignUp(facebookConnectionSignup);
        
        ProviderSignInController signController = new ProviderSignInController(
                connectionFactoryLocator, 
                usersConnectionRepository, 
                new FacebookSignInAdapter());
        signController.setPostSignInUrl("/oauth/token");
        
        return signController;
    }
}
