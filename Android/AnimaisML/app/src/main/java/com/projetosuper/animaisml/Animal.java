package com.projetosuper.animaisml;

import android.net.Uri;

public class Animal {
    private int id;
    private Uri imageUri;
    private String nome;
    private String categoria;
    private String especie;
    private int idade;

    public Animal(int id, Uri imageUri, String nome, String categoria, String especie, int idade) {
        this.id = id;
        this.imageUri = imageUri;
        this.nome = nome;
        this.categoria = categoria;
        this.especie = especie;
        this.idade = idade;
    }

    public Animal(String nome, String categoria, String especie, int idade) {
        this.nome = nome;
        this.categoria = categoria;
        this.especie = especie;
        this.idade = idade;
    }

    public Animal(int id, String nome, String categoria, String especie, int idade) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.especie = especie;
        this.idade = idade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getEspecie() {
        return especie;
    }

    public void setEspecie(String especie) {
        this.especie = especie;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }
}
