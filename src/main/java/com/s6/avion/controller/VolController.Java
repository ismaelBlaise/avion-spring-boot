package com.s6.avion.controller;

import com.s6.avion.model.Vol;
import com.s6.avion.service.VolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@RequestMapping("/vols")
@RequiredArgsConstructor
public class VolController {

    private final VolService volService;

    @GetMapping
    public ModelAndView listeVols() {
        List<Vol> vols = volService.getAllVols();
        ModelAndView mv = new ModelAndView("template-back");

        mv.addObject("page", "vols/vols"); 
        mv.addObject("vols", vols);
        return mv;  
    }
}
