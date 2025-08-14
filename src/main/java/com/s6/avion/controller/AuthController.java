package com.s6.avion.controller;

import com.s6.avion.model.Utilisateur;
import com.s6.avion.service.*;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final UtilisateurService utilisateurService;

    
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

   
}
