package com.argo.gateway.configuration.auth;

import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class InfoAditionalToken implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken oAuth2AccessToken, OAuth2Authentication oAuth2Authentication) {

        Map<String,Object> mapa = new HashMap<String,Object>();

        mapa.put("Informacion Extra","Asu esta es informacion uh");


        ((DefaultOAuth2AccessToken)oAuth2AccessToken).setAdditionalInformation(mapa);

        return oAuth2AccessToken;



    }
}
