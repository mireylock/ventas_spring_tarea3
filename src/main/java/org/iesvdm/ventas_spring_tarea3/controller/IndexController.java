package org.iesvdm.ventas_spring_tarea3.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/index")
    public String mostrarIndex () {
        return "index";
    }
}
