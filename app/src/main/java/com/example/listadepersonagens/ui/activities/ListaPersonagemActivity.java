package com.example.listadepersonagens.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.listadepersonagens.R;
import com.example.listadepersonagens.dao.PersonagemDAO;
import com.example.listadepersonagens.model.Personagem;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

import static com.example.listadepersonagens.ui.activities.ConstantesActivity.CHAVE_PERSONAGEM;

public class ListaPersonagemActivity extends AppCompatActivity {

     public static final String TITULO_APPBAR = "Lista de Personagens";
    private final PersonagemDAO dao = new PersonagemDAO();
     //Lista de comandos
    private ArrayAdapter<Personagem> adapter;

    //Overridade em cima do lista personagem
    @Override
    protected void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lista_de_personagem );
        //Título
        setTitle(TITULO_APPBAR);
        //criaPersonagens();
        botaoNovoPersonagem();
        configuraLista();
    }

    //Isso faz com que o dados não sejam apagados quando voltar.
    @Override
    protected void onResume() {
        super.onResume();
        atualizaPersonagem();

    }

    private void atualizaPersonagem() {
        adapter.clear();
        adapter.addAll(dao.todos());
    }

    private void configuraLista() {
        //Achar a listinha
        ListView listadePersonagens = findViewById( R.id.activity_main_lista_personagem );
        //Colocando todos os personagens numa lista
        //List<Personagem> personagens = dao.todos();
        ListadePersonagens(listadePersonagens);
        //Listando-as
        //listadePersonagens.setAdapter( new ArrayAdapter<>( this, android.R.layout.simple_list_item_1, personagens ) );
        //Método pra quando clicar no personagem
        configuraItemClick(listadePersonagens);
        registerForContextMenu(listadePersonagens);
    }

    private void personagemEscolhido(Personagem personagemEscolhido) {
        Log.i("posicao", "" + personagemEscolhido );
        Intent vaiParaFormulario = new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class);
        vaiParaFormulario.putExtra(CHAVE_PERSONAGEM, personagemEscolhido);
        startActivity( vaiParaFormulario );
    }

    private void ListadePersonagens(ListView listaDePersonagens){
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        listaDePersonagens.setAdapter(adapter);
    }

    private void botaoNovoPersonagem() {
        //Mais findByID que eu já sei o que faz rs
        FloatingActionButton botaoNovoPersonagem = findViewById( R.id.floatingActionButton );
        botaoNovoPersonagem.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity( new Intent(ListaPersonagemActivity.this, FormularioPersonagemActivity.class) );
            }
        } );
    }

    //private void criaPersonagens() {
        //Alguns personagens já prontos.
      //  dao.salva(new Personagem( "Ken", "1,80", "02041979" ));
      //  dao.salva(new Personagem( "Ryu", "1,90", "03051979" ));
    //}


    private void remove(Personagem personagem){
    dao.remove(personagem);
    adapter.remove(personagem);
   }


    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        //menu.add("Remover");
        getMenuInflater().inflate(R.menu.activity_lista_personagem_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        configuraMenu(item);
        return super.onContextItemSelected(item);
    }

    private boolean configuraMenu(@NonNull MenuItem item){
        int itemId = item.getItemId();
        if(itemId == R.id.activity_lista_personagem_menu_remover) {
            new AlertDialog.Builder(this)
                    .setTitle("Remover personagem")
                    .setMessage("Está ciente que o personagem será removido?")
                    .setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
                            Personagem personagemEscolhido = adapter.getItem(menuInfo.position);
                            remove(personagemEscolhido);
                        }
                    })
                    .setNegativeButton("Não", null)
                    .show();
        }

        return super .onContextItemSelected(item);
    }

    private void configuraItemClick(ListView listadePersonagens) {
        listadePersonagens.setOnItemClickListener( new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int posicao, long id) {
                Personagem personagemEscolhido = (Personagem) parent.getItemAtPosition(posicao);
                personagemEscolhido(personagemEscolhido);
            }
        } );
    }


}

