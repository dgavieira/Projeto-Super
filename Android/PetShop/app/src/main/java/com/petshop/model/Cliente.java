package com.petshop.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wim on 4/26/16.
 */
public class Cliente implements Parcelable {

    private int id;
    private String name;
    private String phone;
    private Integer idade;
    private byte[] photo;
    private String senha;
    private String cpf;
    private  String email;


    public Cliente() {
    }

    protected Cliente(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.phone = in.readString();
        this.idade = in.readInt();
        this.photo = in.createByteArray();
        this.senha = in.readString();
        this.cpf = in.readString();
        this.email = in.readString();
    }

    public static final Creator<Cliente> CREATOR = new Creator<Cliente>() {
        @Override
        public Cliente createFromParcel(Parcel source) {
            return new Cliente(source);
        }

        @Override
        public Cliente[] newArray(int size) {
            return new Cliente[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeString(this.phone);
        dest.writeInt(this.idade);
        dest.writeByteArray(this.photo);
        dest.writeString(this.senha);
        dest.writeString(this.cpf);
        dest.writeString(this.email);
    }

}