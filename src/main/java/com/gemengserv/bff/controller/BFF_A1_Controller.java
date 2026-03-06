package com.gemengserv.bff.controller;

import com.gemengserv.bff.service.QHSE_8081;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/A1")
public class BFF_A1_Controller {

    @Autowired
    QHSE_8081 qhse_8081_Service ;

    @GetMapping("/company")
    public Object getCombinedResponse(@RequestParam("userId") int userId, @RequestParam("token") String token) {
        return qhse_8081_Service.getCompanies(userId, token);
    }

    @GetMapping("/registerUser")
    public Object registerUser(@RequestBody Object userRegisterRequest) {
        return qhse_8081_Service.registerUser(userRegisterRequest);
    }

    @GetMapping("/rest/v2/login")
    public ResponseEntity<?> loginV2(@RequestParam("username") String username, @RequestParam("password") String password) {
        return qhse_8081_Service.loginAPI(username, password);
    }

    @RequestMapping(value = "/rest/v1/login", method = RequestMethod.GET)
    public ResponseEntity<Map<String, Object>> login(@RequestParam("username") String username, @RequestParam("password") String password)
    {
        return qhse_8081_Service.login(username, password);
    }
}
