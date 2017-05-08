package com.donateme.ongraph.services.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
public class welcomcontroller {
    @RequestMapping(value = "/")
    public String welcome() {
        return "index";
    }
    @RequestMapping(value="/app/newuser")
    public String newuser(){
        return "new user";
    }
}
