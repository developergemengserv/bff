package com.gemengserv.bff.config;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class LvmTokenInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {

        // Check if the request is destined for LVM
        if (request.getURI().toString().contains("lvm") || request.getURI().getPath().contains("/lvm")) {

            ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            if (attributes != null) {
                HttpServletRequest incomingRequest = attributes.getRequest();

                // Grab the Authorization header sent by Swagger UI's "Authorize" button
                String authHeader = incomingRequest.getHeader(HttpHeaders.AUTHORIZATION);

                if (authHeader != null && !authHeader.isEmpty()) {
                    // Forward the Bearer token directly to the LVM service
                    request.getHeaders().set(HttpHeaders.AUTHORIZATION, authHeader);
                }
            }
        }

        return execution.execute(request, body);
    }
}