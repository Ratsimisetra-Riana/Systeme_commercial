package com.systeme_commercial.bonCommande;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BonCommandeHeader {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idBonCommande;
    int idProforma;

    public BonCommandeHeader(){

    }

    public int getIdBonCommande() {
        return idBonCommande;
    }

    public void setIdBonCommande(int idBonCommande) {
        this.idBonCommande = idBonCommande;
    }

    public int getIdProforma() {
        return idProforma;
    }

    public void setIdProforma(int idproforma) {
        this.idProforma = idproforma;
    }

}
