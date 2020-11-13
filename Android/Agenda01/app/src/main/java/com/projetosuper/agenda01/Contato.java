package com.projetosuper.agenda01;

import android.net.Uri;

public class Contato {
    private String _nome, _telefone, _endereco, _email;
    private Uri _imageURI;
    private long _id;

    public Contato(String _nome, String _telefone, String _endereco, String _email, Uri _imageURI) {
        this._nome = _nome;
        this._telefone = _telefone;
        this._endereco = _endereco;
        this._email = _email;
        this._imageURI = _imageURI;
    }

    public String get_nome() {
        return _nome;
    }

    public void set_nome(String _nome) {
        this._nome = _nome;
    }

    public String get_telefone() {
        return _telefone;
    }

    public void set_telefone(String _telefone) {
        this._telefone = _telefone;
    }

    public String get_endereco() {
        return _endereco;
    }

    public void set_endereco(String _endereco) {
        this._endereco = _endereco;
    }

    public String get_email() {
        return _email;
    }

    public void set_email(String _email) {
        this._email = _email;
    }

    public Uri get_imageURI() {
        return _imageURI;
    }

    public void set_imageURI(Uri _imageURI) {
        this._imageURI = _imageURI;
    }

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }
}
