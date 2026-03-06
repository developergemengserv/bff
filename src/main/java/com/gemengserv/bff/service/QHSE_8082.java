package com.gemengserv.bff.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;

@FeignClient(name = "service-8082", url = "http://localhost:8082")
public interface QHSE_8082 {

    @GetMapping("/company")
    List<Object> getCompanies(@RequestHeader("userId") int userId, @RequestHeader("token") String token);

    @PostMapping(value = "/registerUser", produces = "application/json")
    ResponseEntity<Object> registerUser(@RequestBody Object userRegisterRequest);

}
