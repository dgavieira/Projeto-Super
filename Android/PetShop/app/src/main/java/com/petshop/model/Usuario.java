package com.petshop.model;

import java.io.Serializable;

public class Usuario implements Serializable {

    private int id;
    private String nome;
    private String senha;
    private int idade;
    private byte[] img;
    private String email;

    public Usuario(String nome, String senha, int idade, byte[] img, String email) {
        this.nome = nome;
        this.senha = senha;
        this.idade = idade;
        this.img = img;
        this.email = email;
    }

    public Usuario(int id, String nome, String senha, int idade, byte[] img, String email  ) {
        this.nome = nome;
        this.senha = senha;
        this.idade = idade;
        this.img = img;
        this.email = email;
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
