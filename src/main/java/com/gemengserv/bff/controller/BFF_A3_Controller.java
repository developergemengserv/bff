package com.gemengserv.bff.controller;

import com.gemengserv.bff.service.QHSE_8083;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/A3")
public class BFF_A3_Controller {

    @Autowired
    QHSE_8083 qhse_8083_Service ;

    @GetMapping("/company")
    public Object getCombinedResponse(@RequestHeader("userId") int userId, @RequestHeader("token") String token) {
        return qhse_8083_Service.getCompanies(userId, token);
    }

    @GetMapping("/registerUser")
    public Object registerUser(@RequestBody Object userRegisterRequest) {
        return qhse_8083_Service.registerUser(userRegisterRequest);
    }
}
