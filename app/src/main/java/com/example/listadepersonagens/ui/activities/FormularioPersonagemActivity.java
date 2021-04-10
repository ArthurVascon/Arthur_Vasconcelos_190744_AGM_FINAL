package com.example.listadepersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listadepersonagens.R;
import com.example.listadepersonagens.dao.PersonagemDAO;
import com.example.listadepersonagens.model.Personagem;

public class FormularioPersonagemActivity extends AppCompatActivity {

    //Atributos de um personagem
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    //Isso permite usar o dao fora de proteção
    private final PersonagemDAO dao = new PersonagemDAO();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulario_personagem );
        //O título.
        setTitle( "Formulário de Personagens" );

        //Encontrar os campos pelo ID do campo
        campoNome = findViewById( R.id.editText_nome );
        campoAltura = findViewById( R.id.editText_altura);
        campoNascimento = findViewById( R.id.editText_nascimento);
        Button botaoSalvar = findViewById(R.id.button_salvar);

        botaoSalvar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nome = campoNome.getText().toString();
                String altura = campoAltura.getText().toString();
                String nascimento = campoNascimento.getText().toString();
                //Construtor mais o método de salvar o personagem
                Personagem personagemSalvo = new Personagem(nome, altura, nascimento);
                dao.salva(personagemSalvo);
                finish();
                //Setando os valores
                personagemSalvo.setNome( nome );
                personagemSalvo.setAltura( altura );
                personagemSalvo.setNascimento( nascimento );
                dao.edita( personagemSalvo );
            }
        });
        //Pegando os dados e setando nos campos devidos.
        Intent dados = getIntent();
        Personagem personagem = (Personagem) dados.getSerializableExtra( "personagem" );
        campoNome.setText( personagem.getNome() );
        campoAltura.setText( personagem.getAltura() );
        campoNascimento.setText( personagem.getNascimento() );
    }
}