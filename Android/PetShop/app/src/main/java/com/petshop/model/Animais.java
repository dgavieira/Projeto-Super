package com.petshop.model;

public class Animais {

    Integer id;
    String nome;
    String categoria;
    String raca;
    byte[] foto;


    public Animais(String nome, String categoria, String raca, byte[] foto) {
        this.nome = nome;
        this.categoria = categoria;
        this.raca = raca;
        this.foto = foto;
    }

    public Animais(int id, String nome, String descricao, String raca, byte[] foto) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.raca = raca;
        this.foto = foto;
    }

    public Animais(){

    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public byte[] getFoto() {
        return foto;
    }

    public void setFoto(byte[] foto) {
        this.foto = foto;
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

    public String getRaca() {
        return raca;
    }

    public void setRaca(String raca) {
        this.raca = raca;
    }

}
