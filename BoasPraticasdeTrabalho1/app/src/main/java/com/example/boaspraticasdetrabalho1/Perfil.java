package com.example.boaspraticasdetrabalho1;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class Perfil extends AppCompatActivity {

    DBAdapter adapter;
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        final DBAdapter adapter = new DBAdapter(this);

        Intent perfil = getIntent();
        Bundle USER_PERFIL = perfil.getExtras();
        final String username = USER_PERFIL.getString("Username");

        Intent perfil_verfrase = getIntent();
        String username1 = perfil_verfrase.getStringExtra("Username");

        Intent perfil_createphrase = getIntent();
        String username2 = perfil_createphrase.getStringExtra("Username");

        TextView USERNAME = (TextView)findViewById(R.id.username_Utilizador);
        USERNAME.setText(String.valueOf(username));

        TextView NOME_USERNAME = (TextView)findViewById(R.id.dbNomeUtilizador);
        NOME_USERNAME.setText(String.valueOf(adapter.getName_Perfil(username)));

        TextView ANIVERSARIO = (TextView)findViewById(R.id.dbAniversarioUtilizador);
        ANIVERSARIO.setText(String.valueOf(adapter.getAniversário(username)));

        TextView MAIL = (TextView) findViewById(R.id.dbEmailUtilizador);
        MAIL.setText(String.valueOf(adapter.getEmail(username)));

        TextView CC = (TextView) findViewById(R.id.dbccUtilizador);
        CC.setText(String.valueOf(adapter.getCC(username)));

        TextView EMPRESA = (TextView) findViewById(R.id.dbEmpresaUtilizador);
        EMPRESA.setText(String.valueOf(adapter.getEmpresa(username)));

        TextView MORADA = (TextView) findViewById(R.id.dbMoradaUtilizador);
        MORADA.setText(String.valueOf(adapter.getMorada(username)));


//--------------------------------- Lista de Frases-----------------------------

        LinearLayout lista = (LinearLayout)findViewById(R.id.lista_perfil);
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista_perfil = new Intent(Perfil.this, MainActivity.class);
                lista_perfil.putExtra("Username", username);
                startActivity(lista_perfil);
            }
        });

        //-------------------- Procurar ---------------------------------

        LinearLayout procurar = (LinearLayout) findViewById(R.id.procurar_perfil);
        procurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent search = new Intent(Perfil.this, SearchOption.class);
                search.putExtra("Username", username);
                startActivity(search);
            }
        });

        //----------------------------- Terminar Sessão -----------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_perfil = (LinearLayout) findViewById(R.id.terminar_perfil);
        terminar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_perfil = new Intent(Perfil.this, Login.class);
                        startActivity(sair_perfil);
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
