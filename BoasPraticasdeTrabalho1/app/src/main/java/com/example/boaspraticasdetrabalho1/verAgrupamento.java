package com.example.boaspraticasdetrabalho1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class verAgrupamento extends AppCompatActivity {

    private  DBAdapter_FraseA adapter;
    TableView<String[]> tb;
    TableView<String[]> tb1;
    TableHelper_Agrupamentos tableHelper;
    TableHelper_Agrupamentos tableHelper1;

    static final String id = "ID";
    static final String nome = "Nome";
    static final String descricao = "Descricao";
    static final String data = "Data";
    static final String criador = "Criador";
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_agrupamento);

        final Intent veragrupamento = getIntent();
        final Intent veragrupamento_frasea = getIntent();
        final Intent padrao_veragrupamento = getIntent();

        final String id = veragrupamento.getStringExtra("ID");


        final String username = veragrupamento.getStringExtra("Username");
        final String username1 = veragrupamento_frasea.getStringExtra("Username");
        final String username2 = padrao_veragrupamento.getStringExtra("Username");

        String nome = veragrupamento.getStringExtra("Nome");
        String nome_veragrupamento1 = veragrupamento_frasea.getStringExtra("Nome");
        String nome_veragrupamento2 = padrao_veragrupamento.getStringExtra("Nome");

        TextView NOME_AGRUPAMENTO = (TextView) findViewById(R.id.dbNome_veragrupamento);
        NOME_AGRUPAMENTO.setText(nome);

        String descricao = veragrupamento.getStringExtra("Descricao");
        String descricao1 = veragrupamento_frasea.getStringExtra("Descricao");
        String descricao2 = padrao_veragrupamento.getStringExtra("Descricao");

        TextView DESCRICAO = (TextView) findViewById(R.id.dbDescricao_veragrupamento);
        DESCRICAO.setText(descricao);

        String data = veragrupamento.getStringExtra("Data");
        String data_veragrupamento1 = veragrupamento_frasea.getStringExtra("Data");
        String data_veragrupamento2 = padrao_veragrupamento.getStringExtra("Data");

        TextView DATA = (TextView) findViewById(R.id.dbData_veragrupamento);
        DATA.setText(data);

        String criador = veragrupamento.getStringExtra("Criador");
        String criador1 = veragrupamento_frasea.getStringExtra("Criador");
        String criador2 = padrao_veragrupamento.getStringExtra("Criador");

        TextView CRIADOR = (TextView) findViewById(R.id.dbCriador_veragrupamento);
        CRIADOR.setText(criador);

        //--------------------- Tabelas de frases -------------------------------

        tableHelper = new TableHelper_Agrupamentos(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_frases_associadas);
        tb.setColumnCount(2);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders_Frases()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes_Frases(id)));

        //----------------------- ver frases especifico ----------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent agrupamento_verfrasea = new Intent(verAgrupamento.this, ver_FraseA.class );

                agrupamento_verfrasea.putExtra("Acao", ((String[])clickedData)[0]);
                agrupamento_verfrasea.putExtra("Artefacto", ((String[])clickedData)[1]);
                agrupamento_verfrasea.putExtra("Sujeito", ((String[])clickedData)[2]);
                agrupamento_verfrasea.putExtra("Data", ((String[])clickedData)[3]);
                agrupamento_verfrasea.putExtra("Recurso", ((String[])clickedData)[4]);
                agrupamento_verfrasea.putExtra("ID", ((String[])clickedData)[5]);
                agrupamento_verfrasea.putExtra("Username", username);

                startActivity(agrupamento_verfrasea);

            }
        });

        //---------------------------- Tabela de padroes --------------------------

        tableHelper1 = new TableHelper_Agrupamentos(this);
        TableView tb1 = (TableView<String[]>) findViewById(R.id.tableView_veragrupamento_padroes);
        tb1.setColumnCount(2);
        tb1.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb1.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper1.getSpaceProbeHeaders_Padroes()));
        tb1.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper1.getSpaceProbes_Padroes(id)));

        //--------------------------------- ver padrao especifico ---------------------------

        tb1.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent agrupamento_verpadrao = new Intent(verAgrupamento.this, verPadrao.class);

                agrupamento_verpadrao.putExtra("Nome", ((String[])clickedData)[0]);
                agrupamento_verpadrao.putExtra("Descricao", ((String[])clickedData)[1]);
                agrupamento_verpadrao.putExtra("Criador", ((String[])clickedData)[2]);
                agrupamento_verpadrao.putExtra("Data", ((String[])clickedData)[3]);
                agrupamento_verpadrao.putExtra("ID", ((String[])clickedData)[4]);
                agrupamento_verpadrao.putExtra("Username", username);

                startActivity(agrupamento_verpadrao);
            }
        });

        //---------------------------- lista de frases ---------------------------

        LinearLayout lista_veragrupamento = (LinearLayout)findViewById(R.id.lista_veragrupamento);
        lista_veragrupamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent Lista_veragrupamento = new Intent(verAgrupamento.this, MainFraseAnalista.class);
                Lista_veragrupamento.putExtra("Username", username);
                startActivity(Lista_veragrupamento);
            }
        });

        //---------------------------- Agrupamentos ---------------------------

        LinearLayout agrupamento_veragupamento = (LinearLayout)findViewById(R.id.agrupamento_veragrupamento);
        agrupamento_veragupamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agrupamento_veragrupamento = new Intent(verAgrupamento.this, Agrupamentos.class);
                agrupamento_veragrupamento.putExtra("Username", username);
                startActivity(agrupamento_veragrupamento);
            }
        });

        //----------------------------------- Padrao ------------------------------------

        LinearLayout padroes = (LinearLayout) findViewById(R.id.padrao_veragrupamento);
        padroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent padroes_veragrupamento = new Intent(verAgrupamento.this, Padroes.class);
                padroes_veragrupamento.putExtra("Username", username);
                startActivity(padroes_veragrupamento);
            }
        });

        //---------------------------------- Perfil ----------------------------------------

        LinearLayout perfil_veragrupamento = (LinearLayout) findViewById(R.id.perfil_veragrupamento);
        perfil_veragrupamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil_veragrupamento = new Intent(verAgrupamento.this, Perfil_Analista.class);
                perfil_veragrupamento.putExtra("Username", username);
                startActivity(perfil_veragrupamento);
            }
        });

        //----------------------------------- Terminar Sessão --------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_veragrupamento = (LinearLayout) findViewById(R.id.terminar_veragrupamento);
        terminar_veragrupamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_veragrupamento = new Intent(verAgrupamento.this, Login.class);
                        startActivity(sair_veragrupamento);
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
