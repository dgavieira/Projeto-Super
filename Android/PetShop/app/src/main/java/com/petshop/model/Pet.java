package com.petshop.model;

import java.io.Serializable;

public class Pet implements Serializable {
    String nomePet;
    byte[] photoPet;
    String categoriaPet;
    String racaPet;


    public Pet(String nomePet, byte[] photoPet, String categoriaPet, String racaPet) {
        this.nomePet = nomePet;
        this.photoPet = photoPet;
        this.categoriaPet = categoriaPet;
        this.racaPet = racaPet;
    }

    public String getNomePet() {
        return nomePet;
    }

    public void setNomePet(String nomePet) {
        this.nomePet = nomePet;
    }

    public byte[] getPhotoPet() {
        return photoPet;
    }

    public void setPhotoPet(byte[] photoPet) {
        this.photoPet = photoPet;
    }

    public String getCategoriaPet() {
        return categoriaPet;
    }

    public void setCategoriaPet(String categoriaPet) {
        this.categoriaPet = categoriaPet;
    }

    public String getRacaPet() {
        return racaPet;
    }

    public void setRacaPet(String racaPet) {
        this.racaPet = racaPet;
    }
}
