package com.gemengserv.bff.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@FeignClient(name = "raymond-safety", url = "http://localhost:8081")
public interface QHSE_8081 {

    @GetMapping("/company")
    List<Object> getCompanies( @RequestHeader("userId") int userId,  @RequestHeader("token") String token);

    @PostMapping(value = "/registerUser", produces = "application/json")
    ResponseEntity<Object> registerUser(@RequestBody Object userRegisterRequest);

    @PostMapping("/rest/v2/login")
    public ResponseEntity<?> loginAPI(@RequestParam("username") String username, @RequestParam("password") String password);

    @RequestMapping(value = "/rest/v1/login", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username, @RequestParam("password") String password);
}
