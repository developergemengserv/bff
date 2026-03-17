package com.gemengserv.bff.config;

import feign.Response;
import feign.codec.ErrorDecoder;
import io.micrometer.core.instrument.util.IOUtils;
import org.apache.coyote.BadRequestException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MyFeignErrorDecoder implements ErrorDecoder {
    @Override
    public Exception decode(String methodKey, Response response) {
        String body = "Empty body";
        try {
            if (response.body() != null) {
                body = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
            }
        } catch (IOException e) {
            return new Exception("Failed to read error body");
        }

        // Print the actual error here!
        System.out.println("Error Body from 8081: " + body);

        switch (response.status()) {
            case 400:
                return new BadRequestException("8081 said: " + body);
            default:
                return new Exception("Generic error: " + body);
        }
    }
}
