package com.systeme_commercial.bonCommande;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BonCommandeDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int bonCommandeDetail;
    int idBonCommande;
    int idVarianteArticle;
    double quantiteVoulu;
    double prixUnitaire;
    double tva;
    double prixUnitaireTtc;

    public BonCommandeDetail(){

    }

    public int getBonCommandeDetail() {
        return bonCommandeDetail;
    }

    public void setBonCommandeDetail(int bonCommandeDetail) {
        this.bonCommandeDetail = bonCommandeDetail;
    }

    public int getIdBonCommande() {
        return idBonCommande;
    }

    public void setIdBonCommande(int idBonCommande) {
        this.idBonCommande = idBonCommande;
    }

    public int getIdVarianteArticle() {
        return idVarianteArticle;
    }

    public void setIdVarianteArticle(int idVarianteArticle) {
        this.idVarianteArticle = idVarianteArticle;
    }

    public double getQuantiteVoulu() {
        return quantiteVoulu;
    }

    public void setQuantiteVoulu(double quantiteVoulu) {
        this.quantiteVoulu = quantiteVoulu;
    }

    public double getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(double prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public double getTva() {
        return tva;
    }

    public void setTva(double tva) {
        this.tva = tva;
    }

    public double getPrixUnitaireTtc() {
        return prixUnitaireTtc;
    }

    public void setPrixUnitaireTtc(double prixUnitaireTtc) {
        this.prixUnitaireTtc = prixUnitaireTtc;
    }
}
