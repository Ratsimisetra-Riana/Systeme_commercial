package com.systeme_commercial.bonCommande;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.stream.Stream;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BonCommande {

    BonCommandeHeader header;
    BonCommandeDetail[] details;

    public BonCommande(){
    }

    public BonCommandeHeader getHeader() {
        return header;
    }

    public void setHeader(BonCommandeHeader header) {
        this.header = header;
    }

    public BonCommandeDetail[] getDetails() {
        return details;
    }

    public void setDetails(BonCommandeDetail[] details) {
        this.details = details;
    }

    public void save(BonCommandeHeaderRepository hrepo,BonCommandeDetailRepository drepo){
        hrepo.save(this.header);
        int latestIdBonCommande = hrepo.findLatest().get(0).getIdBonCommande();
        int i = 0;
        for(i=0;i<this.details.length;i++){
            this.details[i].setIdBonCommande(latestIdBonCommande);
            drepo.save(this.details[i]);
        }
        
    }

    public void generatePdf(){
        Document doc = new Document();  
        try  
        {  
            //generate a PDF at the specified location  
            PdfWriter writer = PdfWriter.getInstance(doc, new FileOutputStream("/home/riana/Documents/firstPDF.pdf"));  
            System.out.println("PDF created.");  
            //opens the PDF  
            doc.open();  
            //adds paragraph to the PDF file  
            doc.add(new Paragraph("Bon de commande"));   
            //close the PDF file  
            Font font = FontFactory.getFont(FontFactory.COURIER, 15, BaseColor.BLACK);
            Chunk chunk = new Chunk("Hello World", font);
            doc.add(chunk);
            PdfPTable table = new PdfPTable(3);
            
            addTableHeader(table);
            addRows(table);
            //addCustomRows(table);

            doc.add(table);
            doc.close(); 
            //closes the writer  
            writer.close();  
        }   
        catch (Exception e)  
        {  
            e.printStackTrace();  
        }   

    }
    
    private void addTableHeader(PdfPTable table) {
    Stream.of("idBonCommande", "idVarianteArticle", "quantiteVoulu","prixUnitaire","tva","prixUnitaireTtc")
      .forEach(columnTitle -> {
        PdfPCell header = new PdfPCell();
        header.setBackgroundColor(BaseColor.LIGHT_GRAY);
        header.setBorderWidth(2);
        header.setPhrase(new Phrase(columnTitle));
        table.addCell(header);
    });
}

    private void addRows(PdfPTable table) {
        int i = 0;
        for(i=0;i<this.details.length;i++){
            table.addCell(Integer.toString(this.details[i].getIdBonCommande()));
            table.addCell(Integer.toString(this.details[i].getIdVarianteArticle()));
            table.addCell(Double.toString(this.details[i].getQuantiteVoulu()));
            table.addCell(Double.toString(this.details[i].getPrixUnitaire()));
            table.addCell(Double.toString(this.details[i].getTva()));
            table.addCell(Double.toString(this.details[i].getPrixUnitaireTtc()));
        }
    }
}
