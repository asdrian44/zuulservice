package com.argo.gateway.filters;

import com.netflix.zuul.exception.ZuulException;

public class gatewayException extends ZuulException {

    public gatewayException(int code, String message) {
        super(message, code, message);
    }
}
