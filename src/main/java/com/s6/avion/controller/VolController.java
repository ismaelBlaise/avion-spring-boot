package com.s6.avion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.s6.avion.model.Vol;
import com.s6.avion.service.VolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/vols")
public class VolController {
    @Autowired
    private VolService volService;

    @GetMapping
    public ModelAndView vols() {
        List<Vol>  vols=volService.getAllVols();
        ModelAndView mv = new ModelAndView("template-back");
        mv.addObject("page", "vols/vols");
        mv.addObject("vols", vols);
        return mv;

    }
    

    @GetMapping("menu")
    public ModelAndView menu(@RequestParam(name = "id") String id) {
        ModelAndView mv = new ModelAndView("template-back");
        mv.addObject("page", "vols/menu");
        Vol vol = volService.getVolById(Integer.parseInt(id));
        mv.addObject("vol", vol);
        return mv;
    }
    
    
}
