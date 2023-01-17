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

public class verPadrao extends AppCompatActivity {

    TableView<String[]> tb;
    TableHelper_Padroes tableHelper;

    static final String id = "ID";
    static final String nome = "Nome";
    static final String descricao = "Descricao";
    static final String data = "Data";
    static final String criador = "Criador";
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_padrao);

        final Intent verpadrao = getIntent();
        final Intent agrupamento_verpadrao = getIntent();

        final String username = verpadrao.getStringExtra("Username");
        String username1 = agrupamento_verpadrao.getStringExtra("Username");


        String nome_verpadrao = verpadrao.getStringExtra("Nome");
        String nome_verpadrao1 = agrupamento_verpadrao.getStringExtra("Nome");


        TextView NOME_PADRAO =(TextView) findViewById(R.id.dbNome_verpadrao);
        NOME_PADRAO.setText(nome_verpadrao);

        String descricao_verpadrao = verpadrao.getStringExtra("Descricao");
        String descricao_verpadrao1 = agrupamento_verpadrao.getStringExtra("Descricao");


        TextView DESCRICAO_PADRAO =(TextView) findViewById(R.id.dbDescricao_verpadrao);
        DESCRICAO_PADRAO.setText(descricao_verpadrao);

        String criador_verpadrao = verpadrao.getStringExtra("Criador");
        String criador_verpadrao1 = agrupamento_verpadrao.getStringExtra("Criador");


        TextView CRIADOR_PADRAO =(TextView) findViewById(R.id.dbCriador_verpadrao);
        CRIADOR_PADRAO.setText(criador_verpadrao);

        String data_verpadrao = verpadrao.getStringExtra("Data");
        String data_verpadrao1 = agrupamento_verpadrao.getStringExtra("Data");

        TextView DATA_PADRAO =(TextView) findViewById(R.id.dbData_verpadrao);
        DATA_PADRAO.setText(data_verpadrao);

        String id_verpadrao = verpadrao.getStringExtra("ID");


        //----------------------------- Tabela de agrupamentos-----------------

        tableHelper = new TableHelper_Padroes(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_agrupamentos_associados);
        tb.setColumnCount(2);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders_Agrupamentos()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes_Agrupamentos(id_verpadrao)));

        //--------------------------- ver agrupamento especifico --------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent padrao_veragrupamento = new Intent(verPadrao.this, verAgrupamento.class );

                padrao_veragrupamento.putExtra("Nome", ((String[])clickedData)[0]);
                padrao_veragrupamento.putExtra("Descricao", ((String[])clickedData)[1]);
                padrao_veragrupamento.putExtra("Criador", ((String[])clickedData)[2]);
                padrao_veragrupamento.putExtra("Data", ((String[])clickedData)[3]);
                padrao_veragrupamento.putExtra("ID", ((String[])clickedData)[4]);
                padrao_veragrupamento.putExtra("Username", username);

                startActivity(padrao_veragrupamento);

            }
        });

        //------------------------ Perfil ---------------------------------------------------------

        LinearLayout perfil = (LinearLayout) findViewById(R.id.perfil_verpadrao);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent perfil_verpadrao = new Intent(verPadrao.this, Perfil_Analista.class);
                perfil_verpadrao.putExtra("Username", username);
                startActivity(perfil_verpadrao);
            }
        });

        //------------------------------ Frases -----------------------------------------------

        LinearLayout frases = (LinearLayout) findViewById(R.id.lista_verpadrao);
        frases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent frases_verpadrao = new Intent(verPadrao.this, MainFraseAnalista.class);
                frases_verpadrao.putExtra("Username", username);
                startActivity(frases_verpadrao);
            }
        });

        //---------------------------- Agrupamentos -------------------------------------------

        LinearLayout agrupamentos = (LinearLayout) findViewById(R.id.agrupamento_verpadrao);
        agrupamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agrupamentos_verpadrao = new Intent(verPadrao.this, Agrupamentos.class);
                agrupamento_verpadrao.putExtra("Username", username);
                startActivity(agrupamentos_verpadrao);
            }
        });

        //--------------------- Padroes------------------------------------------------

        LinearLayout padroes = (LinearLayout) findViewById(R.id.padrao_verpadrao);
        padroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent padroes_verpadrao = new Intent(verPadrao.this, Padroes.class);
                padroes_verpadrao.putExtra("Username", username);
                startActivity(padroes_verpadrao);
            }
        });

        //--------------------------- Terminar Sessão -------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_verpadrao = (LinearLayout) findViewById(R.id.terminar_verpadrao);
        terminar_verpadrao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_verpadrao = new Intent(verPadrao.this, Login.class);
                        startActivity(sair_verpadrao);
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
