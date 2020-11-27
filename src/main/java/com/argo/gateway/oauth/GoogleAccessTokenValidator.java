package com.argo.gateway.oauth;

import com.argo.gateway.oauth.dto.responseAuthGoogle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.oauth2.common.exceptions.InvalidTokenException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.Map;

@Service("google")
public class GoogleAccessTokenValidator {


    @Value("${oauth.clientId}")
    private String clientId;

    @Value("${oauth.checkTokenUrl}")
    private String checkTokenUrl;

    @Autowired
    private RestTemplate restTemplate;

    @PostConstruct
    public void defineHandler() {
        this.restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                if (response.getRawStatusCode() == 400) {
                    throw new InvalidTokenException("El token es invalido");
                }
            }
        });
    }


    public responseAuthGoogle validate(String accessToken) {
        System.out.println("aca3");
        Map<String, ?> response = getGoogleResponse(accessToken);
        boolean b = validateResponse(response);
        return new responseAuthGoogle(response, b);

    }

    private boolean validateResponse(Map<String, ?> response) throws AuthenticationException {
        System.out.println("aca2");
        String aud = (String) response.get("aud");
        return aud.equalsIgnoreCase(this.getClientId());
    }

    private Map<String, ?> getGoogleResponse(String accessToken) {
        System.out.println("aca1");

        Map map = restTemplate.exchange(checkTokenUrl + accessToken, HttpMethod.GET, null, Map.class).getBody();
        return (Map<String, Object>) map;
    }


    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getCheckTokenUrl() {
        return checkTokenUrl;
    }

    public void setCheckTokenUrl(String checkTokenUrl) {
        this.checkTokenUrl = checkTokenUrl;
    }
}
