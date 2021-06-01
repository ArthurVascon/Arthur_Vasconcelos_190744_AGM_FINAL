package com.example.listadepersonagens.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.listadepersonagens.R;
import com.example.listadepersonagens.dao.PersonagemDAO;
import com.example.listadepersonagens.model.Personagem;
import com.github.rtoshiro.util.format.SimpleMaskFormatter;
import com.github.rtoshiro.util.format.text.MaskTextWatcher;

import static com.example.listadepersonagens.ui.activities.ConstantesActivity.CHAVE_PERSONAGEM;

public class FormularioPersonagemActivity extends AppCompatActivity {

    public static final String TITULO_APP_BAR_EDITAR_PERSONAGEM = "Editar Personagem";
    public static final String TITULO_APP_BAR_NOVO_PERSONAGEM = "Novo Personagem";

    //Atributos de um personagem
    private EditText campoNome;
    private EditText campoAltura;
    private EditText campoNascimento;
    private EditText campoEndereco;
    private EditText campoGenero;
    private EditText campoCep;
    private EditText campoTelefone;
    private EditText campoRg;
    //Isso permite usar o dao fora de proteção
    private final PersonagemDAO dao = new PersonagemDAO();

    private Personagem personagem;

    //Faz aparecer o menu com ícone
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_formulario_personagem_menu_salvar,menu);
        return super.onCreateOptionsMenu(menu);
    }

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
        campoEndereco.setText(personagem.getEndereco());
        campoGenero.setText(personagem.getGenero());
        campoCep.setText(personagem.getCep());
        campoTelefone.setText(personagem.getTelefone());
        campoRg.setText(personagem.getRg());
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

    //Verificação pra salvar o personagem ou editar ele.
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
        String endereco = campoEndereco.getText().toString();
        String genero = campoGenero.getText().toString();
        String cep = campoCep.getText().toString();
        String telefone = campoTelefone.getText().toString();
        String rg = campoRg.getText().toString();

        personagem.setNome(nome);
        personagem.setAltura(altura);
        personagem.setNascimento(nascimento);
        personagem.setEndereco(endereco);
        personagem.setGenero(genero);
        personagem.setCep(cep);
        personagem.setTelefone(telefone);
        personagem.setRg(rg);

    }

    private void inicializacaoCampos() {
        //Encontrar os campos pelo ID do campo
        campoNome = findViewById( R.id.editText_nome );
        campoAltura = findViewById( R.id.editText_altura);
        campoNascimento = findViewById( R.id.editText_nascimento);
        campoEndereco = findViewById(R.id.editText_endereco);
        campoGenero = findViewById(R.id.editText_genero);
        campoCep = findViewById(R.id.editText_cep);
        campoTelefone = findViewById(R.id.editText_telefone);
        campoRg = findViewById(R.id.editText_rg);

        //Máscara de Campos - A partir daqui
        SimpleMaskFormatter smfAltura = new SimpleMaskFormatter("N,NN");
        MaskTextWatcher mtwAltura = new MaskTextWatcher(campoAltura, smfAltura);
        campoAltura.addTextChangedListener(mtwAltura);

        SimpleMaskFormatter smfNascimento = new SimpleMaskFormatter("NN/NN/NNNN");
        MaskTextWatcher mtwNascimento = new MaskTextWatcher(campoNascimento, smfNascimento);
        campoNascimento.addTextChangedListener(mtwNascimento);

        SimpleMaskFormatter smfCep = new SimpleMaskFormatter("NNNNN-NNN");
        MaskTextWatcher mtwCep = new MaskTextWatcher(campoCep, smfCep);
        campoCep.addTextChangedListener(mtwCep);

        SimpleMaskFormatter smfTelefone = new SimpleMaskFormatter("(NN)NNNNN-NNNN");
        MaskTextWatcher mtwTelefone= new MaskTextWatcher(campoTelefone, smfTelefone);
        campoTelefone.addTextChangedListener(mtwTelefone);

        SimpleMaskFormatter smfRg = new SimpleMaskFormatter("NNN.NNN.NNN-NN");
        MaskTextWatcher mtwRg = new MaskTextWatcher(campoRg, smfRg);
        campoRg.addTextChangedListener(mtwRg);
    }


}