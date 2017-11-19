package com.cts.service.authserver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.web.SignInAdapter;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class FacebookSignInAdapter implements SignInAdapter {
	

	
    @Override
    public String signIn(String localUserId, Connection<?> connection, NativeWebRequest request) {
       /* SecurityContextHolder.getContext().setAuthentication(
          new UsernamePasswordAuthenticationToken(
          connection.getDisplayName(), null, 
          Arrays.asList(new SimpleGrantedAuthority("FACEBOOK_USER"))));*/
    	//SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken());
    	System.out.println("sign in");
    	System.out.println("param1"+request.getParameterMap());

         
        return null;
    }
}
