package com.example.boaspraticasdetrabalho1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class verFrase extends AppCompatActivity {

    private  DeletePhraseHelper helper;
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_frase);

        helper = new DeletePhraseHelper(this);

        final Intent verFrase = getIntent();
        final String id = verFrase.getStringExtra("ID");
        Log.w("coisas", String.valueOf(id));
        final String username = verFrase.getStringExtra("Username");

        String sujeito = verFrase.getStringExtra("Sujeito");
        TextView SUJEITO = (TextView) findViewById(R.id.dbSujeito);
        SUJEITO.setText(sujeito);

        String acao = verFrase.getStringExtra("Acao");
        TextView ACAO = (TextView) findViewById(R.id.dbAcao);
        ACAO.setText(acao);

        String recetor = verFrase.getStringExtra("Recetor");
        TextView RECETOR = (TextView) findViewById(R.id.dbRecetor);
        RECETOR.setText(recetor);

        String artefacto = verFrase.getStringExtra("Artefacto");
        TextView ARTEFACTO = (TextView) findViewById(R.id.dbArtefacto);
        ARTEFACTO.setText(artefacto);

        String data = verFrase.getStringExtra("Data");
        TextView DATA = (TextView) findViewById(R.id.dbData);
        DATA.setText(data);

        String recurso = verFrase.getStringExtra("Recurso");
        TextView RECURSO = (TextView) findViewById(R.id.dbRecurso);
        RECURSO.setText(recurso);

        //------------------- Eliminar Frase -----------------------------------------------

        final AlertDialog.Builder eliminar = new AlertDialog.Builder(this);

        Button eliminar_frase = (Button) findViewById(R.id.button_eliminar_frase);
        eliminar_frase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminar.setTitle("Eliminar Frase");
                eliminar.setMessage("Tem a certeza que pretende eliminar a frase?");

                eliminar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent lista_atualizada = new Intent(verFrase.this, MainActivity.class);
                        helper.deletePhrase(String.valueOf(verFrase.getStringExtra("ID")));
                        lista_atualizada.putExtra("Username", username);
                        startActivity(lista_atualizada);
                        finish();
                        finish();
                    }
                });

                eliminar.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                eliminar.create().show();


            }
        });

        //------------------------------- Editar ---------------------

        Button editar_frase = (Button) findViewById(R.id.button_editar_frase);
        editar_frase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent editar = new Intent(verFrase.this, EditPhrase.class);
                editar.putExtra("ID", id);
                editar.putExtra("Username", username);
                startActivity(editar);
            }
        });

        //--------------------------Lista de frases ------------------------------------------

        LinearLayout lista = (LinearLayout)findViewById(R.id.lista_verfrase);
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Lista  = new Intent(verFrase.this, MainActivity.class);
                Lista.putExtra("Username", username);
                startActivity(Lista);
            }
        });


        //-----------------------------------Entrar no perfil-------------------------------

        LinearLayout perfil_verfrase = (LinearLayout) findViewById(R.id.perfil_verfrase);
        perfil_verfrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verfrase = getIntent();
                String username = verfrase.getStringExtra("Username");

                Intent perfil_verfrase = new Intent(verFrase.this, Perfil.class);
                perfil_verfrase.putExtra("Username", username);
                startActivity(perfil_verfrase);

            }
        });

        //-------------------- Procurar ---------------------------------

        LinearLayout procurar = (LinearLayout) findViewById(R.id.procurar_verfrase);
        procurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent search = new Intent(verFrase.this, SearchOption.class);
                search.putExtra("Username", username);
                startActivity(search);
            }
        });

        //---------------------------Terminar sessao ---------------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_verfrase = (LinearLayout) findViewById(R.id.terminar_verfrase);
        terminar_verfrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_verfrase = new Intent(verFrase.this, Login.class);
                        startActivity(sair_verfrase);
                        finish();
                    }
                });

                builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                builder.create().show();

            }
        });
    }
}
