package org.fnkcode.spring_sec_3.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@ResponseBody
@Controller
public class BaseController {

    @GetMapping(value = "/demo")
    public String getDemo() {
        return "demo";
    }
}
