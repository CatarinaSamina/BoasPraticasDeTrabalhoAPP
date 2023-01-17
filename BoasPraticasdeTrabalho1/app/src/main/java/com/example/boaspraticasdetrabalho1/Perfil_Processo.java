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

public class Perfil_Processo extends AppCompatActivity {

    DBAdapter adapter;
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil__processo);

        final DBAdapter adapter = new DBAdapter(this);

        Intent perfil_Processo = getIntent();
        final String username = perfil_Processo.getStringExtra("Username");

        Intent perfil_verprocesso = getIntent();
        String username1 = perfil_verprocesso.getStringExtra("Username");

        Intent perfil_atividades = getIntent();
        String username2 = perfil_atividades.getStringExtra("Username");


        //----------------------- Informações do perfil ----------------------------------

        TextView USERNAME_PROCESSO = (TextView)findViewById(R.id.username_UtilizadorP);
        USERNAME_PROCESSO.setText(String.valueOf(username));

        TextView NOME_USERNAME = (TextView)findViewById(R.id.dbNomeUtilizadorP);
        NOME_USERNAME.setText(String.valueOf(adapter.getName_Perfil(username)));

        TextView ANIVERSARIO = (TextView)findViewById(R.id.dbAniversarioUtilizadorP);
        ANIVERSARIO.setText(String.valueOf(adapter.getAniversário(username)));

        TextView MAIL = (TextView) findViewById(R.id.dbEmailUtilizadorP);
        MAIL.setText(String.valueOf(adapter.getEmail(username)));

        TextView CC = (TextView) findViewById(R.id.dbccUtilizadorP);
        CC.setText(String.valueOf(adapter.getCC(username)));

        TextView EMPRESA = (TextView) findViewById(R.id.dbEmpresaUtilizadorP);
        EMPRESA.setText(String.valueOf(adapter.getEmpresa(username)));

        TextView MORADA = (TextView) findViewById(R.id.dbMoradaUtilizadorP);
        MORADA.setText(String.valueOf(adapter.getMorada(username)));


        //---------------------------- Perfil -----------------------------------------------

        LinearLayout perfil = (LinearLayout) findViewById(R.id.perfil_perfil_processo);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Perfil_Processo.this, "Já se encontra no seu perfil", Toast.LENGTH_SHORT).show();
            }
        });

        //------------------------------- Ver lista de processos -----------------------------

        LinearLayout processo_perfil = (LinearLayout) findViewById(R.id.lista_perfil_processo);
        processo_perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent processo_perfil = new Intent(Perfil_Processo.this, MainProcesso.class);
                processo_perfil.putExtra("Username", username);
                startActivity(processo_perfil);
            }
        });

        //---------------------------- Atividades ------------------

        LinearLayout atividades = (LinearLayout) findViewById(R.id.atividade_perfil_processo);
        atividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atividade_processo = new Intent(Perfil_Processo.this, Atividades.class);
                atividade_processo.putExtra("Username", username);
                startActivity(atividade_processo);
            }
        });

        //------------------------ Terminar Sessão -------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_perfilp = (LinearLayout) findViewById(R.id.terminar_perfil_processo);
        terminar_perfilp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_perfilp = new Intent(Perfil_Processo.this, Login.class);
                        startActivity(sair_perfilp);
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
