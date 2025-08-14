package com.s6.avion.service;

import com.s6.avion.dto.ReservationDetailDTO;
import com.itextpdf.io.image.ImageData;
import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.FileOutputStream;
import java.util.List;

@Service
public class PdfGeneratorService {

    public void generateReservationPdf(List<ReservationDetailDTO> details, ByteArrayOutputStream outputStream) {
        try (PdfWriter writer = new PdfWriter(outputStream);
            PdfDocument pdfDoc = new PdfDocument(writer);
            Document document = new Document(pdfDoc)) {

            document.add(new Paragraph("Détails de la réservation")
                    .setBold()
                    .setFontSize(18));

            for (ReservationDetailDTO detail : details) {
                document.add(new Paragraph("Reservation ID : " + detail.getIdReservation()));
                document.add(new Paragraph("Classe : " + detail.getClasse()));
                document.add(new Paragraph("Catégorie d'âge : " + detail.getCategorieAge()));
                document.add(new Paragraph("Prix : " + detail.getPrix() + " MGA"));
                document.add(new Paragraph("Date dépôt : " + detail.getDateDepot()));
                document.add(new Paragraph("Nom du fichier : " + detail.getNomFichier()));

                if (detail.getPasseport() != null && detail.getPasseport().length > 0) {
                    ImageData imageData = ImageDataFactory.create(detail.getPasseport());
                    Image image = new Image(imageData).scaleToFit(200, 200);
                    document.add(new Paragraph("Passeport :"));
                    document.add(image);
                }

                document.add(new Paragraph("----------------------------------------"));
            }

        } catch (Exception e) {
            throw new RuntimeException("Erreur lors de la génération du PDF: " + e.getMessage(), e);
        }
    }

}
