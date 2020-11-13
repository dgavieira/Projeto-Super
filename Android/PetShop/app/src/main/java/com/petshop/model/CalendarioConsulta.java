package com.petshop.model;

public class CalendarioConsulta {
    int id;
    String dia;
    String hora;
    String ano;

    public CalendarioConsulta(){

    }

    public CalendarioConsulta(int _id, String _dia, String _hora, String _ano){
        this.id = _id;
        this.dia = _dia;
        this.hora = _hora;
        this.ano = _ano;
    }

    public CalendarioConsulta( String _dia, String _hora, String _ano){
        this.dia = _dia;
        this.hora = _hora;
        this.ano = _ano;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDia() {
        return dia;
    }

    public void setDia(String dia) {
        this.dia = dia;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }
}
