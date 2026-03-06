package com.gemengserv.bff.controller;

import com.gemengserv.bff.service.QHSE_8082;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/A2")
public class BFF_A2_Controller {

    @Autowired
    QHSE_8082 qhse_8082_Service ;

    @GetMapping("/company")
    public Object getCombinedResponse(@RequestHeader("userId") int userId, @RequestHeader("token") String token) {
        return qhse_8082_Service.getCompanies(userId, token);
    }

    @GetMapping("/registerUser")
    public Object registerUser(@RequestBody Object userRegisterRequest) {
        return qhse_8082_Service.registerUser(userRegisterRequest);
    }
}
