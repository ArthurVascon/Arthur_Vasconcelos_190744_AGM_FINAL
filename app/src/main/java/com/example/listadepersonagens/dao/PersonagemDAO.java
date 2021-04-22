package com.example.listadepersonagens.dao;

import com.example.listadepersonagens.model.Personagem;

import java.util.ArrayList;
import java.util.List;

public class PersonagemDAO {

    //Criação de uma Lista de Personagens
    private  final  static List<Personagem> personagens = new ArrayList<>();
    //Identificação do personagem
    private  static int contadorDeId = 1;

    private Personagem buscaPersonagemId(Personagem personagem) {
        for(Personagem p:
                personagens){
            if(p.getId() == personagem.getId()){
                return p;
            }
        }
        return null;
    }

    //Salva um personagem e coloca o ID
    public void salva(Personagem personagemSalvo) {
        personagemSalvo.setId(contadorDeId);
        personagens.add(personagemSalvo);
        contadorDeId++;
    }
//Método para edição do personagem, ele encontra ele pelo ID.
    public void edita(Personagem personagem){
        Personagem personagemEscolhido = buscaPersonagemId(personagem);
        if(personagemEscolhido != null){
            int posicaoDoPersonagem = personagens.indexOf(personagemEscolhido);
            personagens.set(posicaoDoPersonagem,personagem);
        }
    }

    public void remove(Personagem personagem){
        Personagem personagemDevolvido = buscaPersonagemId(personagem);
        if(personagemDevolvido != null){
            personagens.remove(personagemDevolvido);
        }
    }

    //É o método que retorna TODOS os personagens (meio óbvio não? rs)
    public List<Personagem> todos() {
        return  new ArrayList<>(personagens);
    }
}
