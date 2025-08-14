package com.s6.avion.controller;

import java.io.ByteArrayOutputStream;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.s6.avion.dto.ReservationDetailDTO;
import com.s6.avion.dto.ReservationDetailResponse;
import com.s6.avion.model.Reservation;
import com.s6.avion.service.PdfService;
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

    @Autowired
    private PdfService pdfGeneratorService;

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


    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generateReservationPdf(@RequestParam String id) {
        try {
            // Récupérer les détails
            ReservationDetailResponse response = reservationService.getReservationDetails(Integer.parseInt(id));
            List<ReservationDetailDTO> details = response.getDetails();

            // Générer le PDF dans un flux mémoire
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            pdfGeneratorService.generateReservationPdf(details, baos);

            // Renvoyer le PDF au navigateur
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=reservation_" + id + ".pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(baos.toByteArray());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(("Erreur lors de la génération du PDF: " + e.getMessage()).getBytes());
        }
    }
    
    
}
