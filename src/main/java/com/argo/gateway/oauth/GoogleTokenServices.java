package com.argo.gateway.oauth;

import com.argo.gateway.oauth.dto.responseAuthGoogle;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.OAuth2Request;
import org.springframework.security.oauth2.provider.token.AccessTokenConverter;
import org.springframework.security.oauth2.provider.token.DefaultAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

import static java.util.Collections.singleton;

@Service("googleService")
public class GoogleTokenServices implements ResourceServerTokenServices {


    private String userInfoUrl;

    @Autowired
    private RestTemplate restTemplate;


    private final AccessTokenConverter tokenConverter = new DefaultAccessTokenConverter();



    @Autowired
    @Qualifier("google")
    private GoogleAccessTokenValidator googleAccessTokenValidator;

    @Override
    public OAuth2Authentication loadAuthentication(String accessToken) throws AuthenticationException, InvalidTokenException {
        System.out.println("entre aca");
        responseAuthGoogle validate = this.googleAccessTokenValidator.validate(accessToken);
        if (!validate.isValidate()) {
            throw new UnapprovedClientAuthenticationException("Este token no sirve ga.");
        }
        Map<String, ?> tokenInfo = validate.getResponse();
        OAuth2Authentication authentication = getAuthentication(tokenInfo, accessToken);
        return authentication;
    }

    private OAuth2Authentication getAuthentication(Map<String, ?> tokenInfo, String accessToken) {
        System.out.println("entre aca2");
        OAuth2Request request = tokenConverter.extractAuthentication(tokenInfo).getOAuth2Request();
        Authentication authentication = getAuthenticationToken(accessToken);
        return new OAuth2Authentication(request, authentication);
    }

    private Authentication getAuthenticationToken(String accessToken) {
        System.out.println("entre aca3");
        Map<String, ?> userInfo = getUserInfo(accessToken);
        String idStr = ((String) userInfo.get("resourceName")).split("/")[1];
        if (idStr == null) {
            throw new InternalAuthenticationServiceException("Cannot get id from user info");
        }
        return new UsernamePasswordAuthenticationToken(new GooglePrincipal(new BigInteger(idStr)), null, singleton(new SimpleGrantedAuthority("ROLE_USER")));
    }

    private Map<String, ?> getUserInfo(String accessToken) {
        System.out.println("entre aca4");
        HttpHeaders headers = getHttpHeaders(accessToken);
        Map map = restTemplate.exchange(userInfoUrl, HttpMethod.GET, new HttpEntity<>(headers), Map.class).getBody();
        return (Map<String, Object>) map;
    }

    private HttpHeaders getHttpHeaders(String accessToken) {
        System.out.println("entre aca");
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + accessToken);
        return headers;
    }

    @Override
    public OAuth2AccessToken readAccessToken(String accessToken) {
        throw new UnsupportedOperationException("Not supported: read access token");
    }

    public void setUserInfoUrl(String userInfoUrl) {
        this.userInfoUrl = userInfoUrl;
    }
}
