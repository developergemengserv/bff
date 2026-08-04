package com.gemengserv.bff.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DynamicWebController {

    @GetMapping("/{clientName}")
    public String forwardToClientIndex(@PathVariable String clientName) {
        System.out.println( "forward:/" + clientName + "/index.html");
        return "forward:/" + clientName + "/index.html";
    }

}
