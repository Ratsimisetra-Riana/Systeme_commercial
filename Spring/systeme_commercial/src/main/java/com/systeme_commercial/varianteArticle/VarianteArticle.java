package com.systeme_commercial.varianteArticle;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class VarianteArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int idVarianteArticle;
    int idArticle;//foreign key
    String nomVarianteArticle;
    String description;
    String photo;

    public VarianteArticle(){
    }

    public int getIdVarianteArticle() {
        return idVarianteArticle;
    }

    public void setIdVarianteArticle(int idVarianteArticle) {
        this.idVarianteArticle = idVarianteArticle;
    }

    public int getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(int idArticle) {
        this.idArticle = idArticle;
    }

    public String getNomVarianteArticle() {
        return nomVarianteArticle;
    }

    public void setNomVarianteArticle(String nomVarianteArticle) {
        this.nomVarianteArticle = nomVarianteArticle;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    
}
