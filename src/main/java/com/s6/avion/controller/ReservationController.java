package com.s6.avion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.s6.avion.model.Reservation;
import com.s6.avion.service.ReservationService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping("/reservations")
public class ReservationController {
    @Autowired
    private ReservationService reservationService;

    @GetMapping
    public ModelAndView getAllReservations(HttpSession session) {
        Integer utilisateurId =(Integer) session.getAttribute("utilisateur");
        List<Reservation> reservations = reservationService.getAllReservations(utilisateurId);
        ModelAndView mv = new ModelAndView("template-front");

        mv.addObject("page", "reservations/reservations"); 
        mv.addObject("reservations", reservations);
        return mv;  
    }

    @GetMapping("annuler")
    public String annulerReservation(@RequestParam String id, RedirectAttributes redirectAttributes) {
        Integer idReservation = Integer.parseInt(id);
        try {
            reservationService.annulerReservation(idReservation);
            redirectAttributes.addFlashAttribute("succes", "Réservation annulée avec succès");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("erreur", "Erreur lors de l'annulation : " + e.getMessage());
        }
        return "redirect:/reservations";
    }



    @GetMapping("payer")
    public String payerReservation(@RequestParam String id, RedirectAttributes redirectAttributes) {
        Integer idReservation = Integer.parseInt(id);
        
        try {
             reservationService.payeeReservation(idReservation);
        
        
        redirectAttributes.addFlashAttribute("succes", "Réservation payee avec succès");
        } catch (Exception e) {
          redirectAttributes.addFlashAttribute("erreur", "Erreur lors du paiement de la réservation : " + e.getMessage());
        }
        return "redirect:/reservations";
    }   
    
    
}
