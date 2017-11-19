package com.cts.service.authserver;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2RequestFactory;
import org.springframework.security.oauth2.provider.TokenRequest;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.web.client.RestTemplate;

public class SocialTokenGranter extends AbstractTokenGranter {

private static final String GRANT_TYPE = "facebook_social";    
UserDetailsService userDetailsService; // custom UserDetails service

SocialTokenGranter(
		UserDetailsService userDetailsService,
        AuthorizationServerTokenServices tokenServices,
        OAuth2RequestFactory defaultOauth2RequestFactory,
        ClientDetailsService clientDetailsService) {
    super(tokenServices, clientDetailsService, defaultOauth2RequestFactory, GRANT_TYPE);
    this.userDetailsService = userDetailsService;
}

@Override
protected OAuth2Authentication getOAuth2Authentication(ClientDetails clientDetails, TokenRequest request) {

    // retrieve social token sent by the client
    Map<String, String> parameters = request.getRequestParameters();
    String socialToken = parameters.get("social_token");

    //validate social token and receive user information from external authentication server
    String url = "https://graph.facebook.com/me?access_token=" + socialToken;

    Authentication userAuth = null;
    try {    
        ResponseEntity<FacebookUserInformation> response = new RestTemplate().getForEntity(url, FacebookUserInformation.class);

        if (response.getStatusCode().is4xxClientError()) throw new Exception("Invalid Access Token");

        FacebookUserInformation userInformation = response.getBody();
        //User giraffeSocialUserDetails = userDetailsService.loadOrCreateSocialUser(userInformation.getId(), userInformation.getEmail(), User.SocialProvider.FACEBOOK);

        //userAuth = new UsernamePasswordAuthenticationToken(giraffeSocialUserDetails, "N/A", giraffeSocialUserDetails.getAuthorities());
    } catch (Exception e) {               
        // log the stacktrace
    	logger.error("error in creating access Token");
    }
    return new OAuth2Authentication(request.createOAuth2Request(clientDetails), userAuth);
}

private static class FacebookUserInformation {
    private String id;
    private String email;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}  
    
    // getters, setters, constructor
}    

}