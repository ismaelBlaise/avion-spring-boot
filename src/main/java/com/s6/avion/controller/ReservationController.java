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
    public ModelAndView annulerReservation(@RequestParam String id) {
        Integer idReservation = Integer.parseInt(id);
        reservationService.annulerReservation(idReservation);
        
        ModelAndView mv = new ModelAndView("redirect:/reservations");
        // mv.addObject("message", "Réservation annulée avec succès");
        return mv;
    }


    @GetMapping("payer")
    public ModelAndView payerReservation(@RequestParam String id) {
        Integer idReservation = Integer.parseInt(id);
        reservationService.payeeReservation(idReservation);
        
        ModelAndView mv = new ModelAndView("redirect:/reservations");
        // mv.addObject("message", "Réservation payée avec succès");
        return mv;
    }   
    
    
}
