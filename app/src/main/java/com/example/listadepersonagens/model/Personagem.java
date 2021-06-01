package com.example.listadepersonagens.model;

import androidx.annotation.NonNull;

import java.io.Serializable;

//Isso daqui é uma classe comum como já foi visto em POO. Com atributos e métodos e um construtor. Coisa básica.

public class Personagem implements Serializable {
    private  String nome;
    private  String altura;
    private  String nascimento;
    private String endereco;
    private String genero;
    private String cep;
    private String telefone;
    private String rg;
    private int id =0;

    public Personagem(String nome, String altura, String nascimento, String endereco, String genero, String cep, String telefone, String rg) {

        this.nome = nome;
        this.altura = altura;
        this.nascimento = nascimento;
        this.endereco = endereco;
        this.genero = genero;
        this.cep = cep;
        this.telefone = telefone;
        this.rg = rg;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAltura() {
        return altura;
    }

    public void setAltura(String altura) {
        this.altura = altura;
    }

    public String getNascimento() {
        return nascimento;
    }

    public void setNascimento(String nascimento) {
        this.nascimento = nascimento;
    }

    public String getEndereco(){return  endereco;}

    public void setEndereco(String endereco) { this.endereco = endereco;}

    public String getGenero(){return genero;}

    public void setGenero(String genero){this.genero = genero;}

    public String getCep(){return cep;}

    public void setCep(String cep){this.cep = cep;}

    public String getTelefone(){return telefone;}

    public void setTelefone(String telefone){this.telefone = telefone;}

    public String getRg(){return rg;}

    public void setRg(String rg){this.rg = rg;}

    public Personagem() {

    }
    @NonNull
    @Override
    public String toString(){
        return nome;
    }
    public void setId(int id){
        this.id = id;
    }
    public  int getId(){
        return id;
    }
    public boolean IdValido() {
        return id>0;
    }
}

