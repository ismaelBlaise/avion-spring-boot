package com.s6.avion.controller;

import com.s6.avion.model.Utilisateur;
import com.s6.avion.model.Vol;
import com.s6.avion.service.*;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UtilisateurService utilisateurService;

    
    private final VolService volService;
    @GetMapping("/")
    public ModelAndView showLoginForm() {
        return new ModelAndView("index"); 
    }

    
    @PostMapping("/login")
    public ModelAndView processLogin(@RequestParam String email,
                                     @RequestParam String mdp,HttpSession session) {
        ModelAndView mv = new ModelAndView();

        try {
            Utilisateur utilisateur = utilisateurService.login(email, mdp);
            

            if(utilisateur.getRole() == null) {
                throw new RuntimeException("Rôle de l'utilisateur non défini");
            }
            if(utilisateur.getRole().getRole().equalsIgnoreCase("admin")) {
                mv.setViewName("template-back");
            } else  {
                mv.setViewName("template-front");
            } 
            mv.addObject("page", "default");
            mv.addObject("utilisateur", utilisateur);
            session.setAttribute("utilisateur", utilisateur.getIdUtilisateur()); 
        } catch (RuntimeException e) {
            mv.setViewName("index"); 
            mv.addObject("erreur", e.getMessage());
            mv.addObject("email", email); 
        }

        return mv;
    }


    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/"; 
    }

    @GetMapping("/vols/liste")
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
         // Assuming you have a method to get Vol by ID
        mv.addObject("page", "vols/menu"); 
        mv.addObject("vol", id);
        return mv;
    }
}
