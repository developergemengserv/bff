package com.gemengserv.bff.service;

import com.gemengserv.bff.config.FeignConfig;
import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "gemsafety", url = "http://localhost:8101", configuration = FeignConfig.class)
public interface GemSafetyService
{

}
