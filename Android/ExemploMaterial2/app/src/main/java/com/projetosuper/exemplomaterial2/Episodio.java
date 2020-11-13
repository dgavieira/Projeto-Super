package com.projetosuper.exemplomaterial2;

public class Episodio {
    String titulo;
    String descricao;
    String data_exib;
    String youtubeid;

    public Episodio(String titulo, String descricao, String data_exib, String youtubeid) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.data_exib = data_exib;
        this.youtubeid = youtubeid;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getData_exib() {
        return data_exib;
    }

    public void setData_exib(String data_exib) {
        this.data_exib = data_exib;
    }

    public String getYoutubeid() {
        return youtubeid;
    }

    public void setYoutubeid(String youtubeid) {
        this.youtubeid = youtubeid;
    }
}
