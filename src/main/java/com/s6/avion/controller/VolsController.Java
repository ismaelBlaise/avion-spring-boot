package com.s6.avion.controller;

import com.s6.avion.model.Vol;
import com.s6.avion.service.VolService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/vols")
@RequiredArgsConstructor
public class VolsController {

    private final VolService volService;


    @GetMapping
    public ModelAndView listeVols() {
        List<Vol> vols = volService.getAllVols();
        ModelAndView mv = new ModelAndView("template-back");

        mv.addObject("page", "vols/vols"); 
        mv.addObject("vols", vols);
        return mv;  
    }

    @GetMapping("/menu")
    public ModelAndView menu(@RequestParam String id) {
        ModelAndView mv = new ModelAndView("template-back");
        
        mv.addObject("page", "vols/menu"); 
        mv.addObject("vol", id);
        return mv;
    }

    
    
}
