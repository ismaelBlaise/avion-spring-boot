package com.s6.avion.dto;



import lombok.Data;

@Data
public class ReservationDetailDTO {

    private Integer idReservationDetail;
    private Integer idReservation;
    private Integer idCategorieAge;
    private Integer idClasse;
    private Double prix;
    private byte[] passeport; // liste de fichiers sous forme de byte[]
    private String nomFichier;
    private String dateDepot;
    private String categorieAge;
    private String classe;

}
