package com.gemengserv.bff.config;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class DynamicWebController {

    @GetMapping("/{clientName}")
    public String forwardToClientIndex(@PathVariable String clientName) {
        return "forward:/" + clientName + "/index.html";
    }
}
