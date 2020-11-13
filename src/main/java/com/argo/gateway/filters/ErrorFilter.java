package com.argo.gateway.filters;

import com.netflix.zuul.ZuulFilter;
import org.springframework.stereotype.Component;

@Component
public class ErrorFilter extends ZuulFilter {


    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return -1; // Needs to run before SendErrorFilter which has filterOrder == 0
    }

    @Override
    public boolean shouldFilter() {
        // only forward to errorPath if it hasn't been forwarded to already
        return true;
    }

    @Override
    public Object run() {

        return null;
    }
}
