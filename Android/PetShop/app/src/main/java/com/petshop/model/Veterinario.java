package com.petshop.model;

public class Veterinario {
    int Codigo;
    String Nome;
    int Idade;
    String Telefone;
    String Especialidade;
    int Tempo_exp;
    String Endereço;
    float Valor_consulta;

    public Veterinario(){

    }

    public Veterinario(int _codigo, String _nome, int _idade, String _telefone, String _especialidade, int _tempo_exp, String _endereco, float _valor_consulta){
        this.Codigo = _codigo;
        this.Nome = _nome;
        this.Idade = _idade;
        this.Telefone = _telefone;
        this.Especialidade = _especialidade;
        this.Tempo_exp = _tempo_exp;
        this.Endereço = _endereco;
        this.Valor_consulta = _valor_consulta;
    }

    public Veterinario(String _nome, int _idade, String _telefone, String _especialidade, int _tempo_exp, String _endereco, float _valor_consulta){
        this.Nome = _nome;
        this.Idade = _idade;
        this.Telefone = _telefone;
        this.Especialidade = _especialidade;
        this.Tempo_exp = _tempo_exp;
        this.Endereço = _endereco;
        this.Valor_consulta = _valor_consulta;
    }

    public int getCodigo() {
        return Codigo;
    }

    public void setCodigo(int codigo) {
        Codigo = codigo;
    }

    public String getNome() {
        return Nome;
    }

    public void setNome(String nome) {
        Nome = nome;
    }

    public int getIdade() {
        return Idade;
    }

    public void setIdade(int idade) {
        Idade = idade;
    }

    public String getTelefone() {
        return Telefone;
    }

    public void setTelefone(String telefone) {
        Telefone = telefone;
    }

    public String getEspecialidade() {
        return Especialidade;
    }

    public void setEspecialidade(String especialidade) {
        Especialidade = especialidade;
    }

    public int getTempo_exp() {
        return Tempo_exp;
    }

    public void setTempo_exp(int tempo_exp) {
        Tempo_exp = tempo_exp;
    }

    public String getEndereço() {
        return Endereço;
    }

    public void setEndereço(String endereço) {
        Endereço = endereço;
    }

    public float getValor_consulta() {
        return Valor_consulta;
    }

    public void setValor_consulta(float valor_consulta) {
        Valor_consulta = valor_consulta;
    }
}
