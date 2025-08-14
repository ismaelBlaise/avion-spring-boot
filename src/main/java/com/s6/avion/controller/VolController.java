package com.s6.avion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.s6.avion.model.ConfVol;
import com.s6.avion.model.Vol;
import com.s6.avion.repository.CategorieAgeRepository;
import com.s6.avion.repository.ClasseRepository;
import com.s6.avion.repository.ConfVolRepository;
import com.s6.avion.service.VolService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;




@Controller
@RequestMapping("/vols")
public class VolController {
    @Autowired
    private VolService volService;

    @Autowired
    private ClasseRepository classeRepository;

    @Autowired
    private CategorieAgeRepository categorieAgeRepository;

    @Autowired
    private ConfVolRepository confVolRepository;

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


    @GetMapping("caracteristique")
    public ModelAndView caracteristiqueForm(@RequestParam String id) {
        ModelAndView mv = new ModelAndView("template-back");
        
        mv.addObject("page", "vols/caracteristique");
        mv.addObject("classes", classeRepository.findAll());
        mv.addObject("categories", categorieAgeRepository.findByCategorie("Enfant"));
        Vol vol = volService.getVolById(Integer.parseInt(id));
        mv.addObject("vol", vol);
        List<ConfVol> confVols = confVolRepository.findByVol(vol);
        mv.addObject("confVols", confVols);

        return mv;
    }


    @PostMapping("caracteristique")
    public ModelAndView ajouterCaracteristique(@RequestParam String idVol,
                                               @RequestParam String idCategorieAge,
                                               @RequestParam Double montant) {
        ModelAndView mv = new ModelAndView("template-back");
        Vol vol = volService.getVolById(Integer.parseInt(idVol));
        int i= 0;
        
        List<ConfVol> confVols = confVolRepository.findByVol(vol);
        for (ConfVol confVol : confVols) {
            if(confVol.getCategorieAge().getIdCategorieAge().equals(Integer.parseInt(idCategorieAge))==false) {
                ConfVol newConfVol = new ConfVol();
                newConfVol.setVol(confVol.getVol());
                newConfVol.setCategorieAge(categorieAgeRepository.findById(Integer.parseInt(idCategorieAge)).orElse(null));
                newConfVol.setMontant(confVol.getMontant()*montant);
                newConfVol.setCapacite(confVol.getCapacite());
                newConfVol.setClasse(confVol.getClasse());
                confVolRepository.save(newConfVol);
                confVols.add(newConfVol);
                i++;
            }
        }
        
        
        mv.addObject("page", "vols/caracteristique");
        mv.addObject("vol", vol);
        mv.addObject("classes", classeRepository.findAll());
        mv.addObject("categories", categorieAgeRepository.findByCategorie("Enfant"));
        mv.addObject("confVols", confVols);
        if(i>0) {
            mv.addObject("succes", "Caractéristique ajoutée avec succès");
        } else {
            mv.addObject("erreur", "Veillez déja configurer pour les adultes.");
        }
        return mv;

    }
    
    
    
    
}
