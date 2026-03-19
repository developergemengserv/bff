package com.gemengserv.bff.service;

import com.gemengserv.bff.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "tejraj", url = "http://localhost:8081", configuration = FeignConfig.class)
public interface GroundHoldingSafetyService
{
}
