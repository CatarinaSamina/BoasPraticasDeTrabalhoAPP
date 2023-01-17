package com.example.boaspraticasdetrabalho1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Perfil_Analista extends AppCompatActivity {
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__analista);

        final DBAdapter adapter = new DBAdapter(this);

        Intent perfil_analista = getIntent();
        Bundle USER_PERFIL = perfil_analista.getExtras();
        final String username = USER_PERFIL.getString("Username");


        Intent perfil_verfrasea = getIntent();
        String username1 = perfil_verfrasea.getStringExtra("Username");

        Intent perfil_agrupamentos = getIntent();
        String username2 = perfil_agrupamentos.getStringExtra("Username");

        Intent perfil_veragrupamento = getIntent();
        String username3 = perfil_veragrupamento.getStringExtra("Username");

        Intent perfil_padroes = getIntent();
        String username4 = perfil_padroes.getStringExtra("Username");

        TextView USERNAME = (TextView)findViewById(R.id.username_Utilizadora);
        USERNAME.setText(String.valueOf(username));

        TextView NOME_USERNAME = (TextView)findViewById(R.id.dbNomeUtilizadora);
        NOME_USERNAME.setText(String.valueOf(adapter.getName_Perfil(username)));

        TextView ANIVERSARIO = (TextView)findViewById(R.id.dbAniversarioUtilizadora);
        ANIVERSARIO.setText(String.valueOf(adapter.getAniversário(username)));

        TextView MAIL = (TextView) findViewById(R.id.dbEmailUtilizadora);
        MAIL.setText(String.valueOf(adapter.getEmail(username)));

        TextView CC = (TextView) findViewById(R.id.dbccUtilizadora);
        CC.setText(String.valueOf(adapter.getCC(username)));

        TextView EMPRESA = (TextView) findViewById(R.id.dbEmpresaUtilizadora);
        EMPRESA.setText(String.valueOf(adapter.getEmpresa(username)));

        TextView MORADA = (TextView) findViewById(R.id.dbMoradaUtilizadora);
        MORADA.setText(String.valueOf(adapter.getMorada(username)));

//----------------------------------- Perfil ------------------------------------

        LinearLayout perfil = (LinearLayout) findViewById(R.id.perfil_perfil_analista);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Perfil_Analista.this, "Já se encontra no seu perfil", Toast.LENGTH_SHORT).show();
            }
        });

//------------------------------- Agrupamentos -------------------------------------------

        LinearLayout agrupamentos = (LinearLayout) findViewById(R.id.agrupamento_perfil_analista);
        agrupamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agrupamento = new Intent(Perfil_Analista.this, Agrupamentos.class);
                agrupamento.putExtra("Username", username);
                startActivity(agrupamento);
            }
        });

        //------------------------------ Padrao --------------------------------------

        LinearLayout padrao = (LinearLayout) findViewById(R.id.padrao_perfil_analista);
        padrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent padrao = new Intent(Perfil_Analista.this, Padroes.class);
                padrao.putExtra("Username", username);
                startActivity(padrao);
            }
        });

        //---------------------------- Frases -----------------------------------------

        LinearLayout frases = (LinearLayout) findViewById(R.id.lista_perfil_analista);
        frases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista_frase = new Intent(Perfil_Analista.this, MainFraseAnalista.class);
                lista_frase.putExtra("Username", username);
                startActivity(lista_frase);
            }
        });

        //----------------------------- Terminar Sessão ------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);


        LinearLayout terminar_perfil = (LinearLayout) findViewById(R.id.terminar_perfil_analista);
        terminar_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_perfil = new Intent(Perfil_Analista.this, Login.class);
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
