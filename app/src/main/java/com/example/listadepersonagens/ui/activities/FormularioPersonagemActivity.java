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

import static com.example.listadepersonagens.ui.activities.ConstantesActivity.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR_EDITAR_PERSONAGEM = "Editar Personagem";
    public static final String TITULO_APP_BAR_NOVO_PERSONAGEM = "Novo Personagem";

    //Atributos de um personagem
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    //Isso permite usar o dao fora de proteção
    private final PersonagemDAO dao = new PersonagemDAO();

    private Personagem personagem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_formulario_personagem );
        //O título.
        setTitle( "Formulário de Personagens" );
        inicializacaoCampos();
        configuraBotao();
        //Pegando os dados e setando nos campos devidos.
        carregaPersonagem();
    }
    private void carregaPersonagem() {
        Intent dados = getIntent();
        if (dados.hasExtra(CHAVE_PERSONAGEM)) {
            //Titulo do Formulario de Editar
            setTitle(TITULO_APP_BAR_EDITAR_PERSONAGEM);
            personagem = (Personagem) dados.getSerializableExtra(CHAVE_PERSONAGEM);
            preencheCampos();
        } else {
            //Titulo do Formulario Novo
            setTitle(TITULO_APP_BAR_NOVO_PERSONAGEM);
            personagem = new Personagem();

        }
    }

    private void preencheCampos() {
        campoNome.setText(personagem.getNome());
        campoAltura.setText(personagem.getAltura());
        campoNascimento.setText(personagem.getNascimento());
    }


    
    private void configuraBotao() {
        Button botaoSalvar = findViewById(R.id.button_salvar);
        //Click do botão
        botaoSalvar.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finalizaFormulario();
            }
        });
    }

    private void finalizaFormulario() {
        preenchePersonagem();
        if (personagem.IdValido()) {
            dao.edita(personagem);
            finish();
        } else {
            dao.salva(personagem);
        }
        finish();
    }

    private void preenchePersonagem() {
        //Setando os valores
        String nome = campoNome.getText().toString();
        String altura = campoAltura.getText().toString();
        String nascimento = campoNascimento.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);

    }

    private void inicializacaoCampos() {
        //Encontrar os campos pelo ID do campo
        campoNome = findViewById( R.id.editText_nome );
        campoAltura = findViewById( R.id.editText_altura);
        campoNascimento = findViewById( R.id.editText_nascimento);
    }


}