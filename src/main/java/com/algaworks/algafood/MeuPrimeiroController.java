package com.algaworks.algafood;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@ResponseBody
public class MeuPrimeiroController {

    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }
}
