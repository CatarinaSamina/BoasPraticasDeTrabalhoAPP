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

public class ver_FraseA extends AppCompatActivity {

    private  DBAdapter_FraseA adapter;
    TableView<String[]> tb;
    TableHelper_FraseA tableHelper;

    static final String username = "Username";
    static final String sujeito = "Sujeito";
    static final String acao = "Acao";
    static final String artefacto = "Artefacto";
    static final String data = "Data";
    static final String recurso = "Recurso";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver__frase);

        final Intent verfrasea = getIntent();
        final Intent agrupamento_verfrasea = getIntent();

        String id = verfrasea.getStringExtra("ID");
        String id1 = agrupamento_verfrasea.getStringExtra("ID");

        String sujeito = verfrasea.getStringExtra("Sujeito");
        String sujeito1 = agrupamento_verfrasea.getStringExtra("Sujeito");

        TextView SUJEITO = (TextView) findViewById(R.id.dbSujeito_frasea);
        SUJEITO.setText(sujeito);

        String acao = verfrasea.getStringExtra("Acao");
        String acao1 = agrupamento_verfrasea.getStringExtra("Acao");

        TextView ACAO = (TextView) findViewById(R.id.dbAcao_frasea);
        ACAO.setText(acao);

        String artefacto = verfrasea.getStringExtra("Artefacto");
        String artefacto1 = agrupamento_verfrasea.getStringExtra("Artefacto");

        TextView ARTEFACTO = (TextView) findViewById(R.id.dbArtefacto_frasea);
        ARTEFACTO.setText(artefacto);

        String data = verfrasea.getStringExtra("Data");
        String data1 = agrupamento_verfrasea.getStringExtra("Data");

        TextView DATA = (TextView) findViewById(R.id.dbData_frasea);
        DATA.setText(data);

        String recurso = verfrasea.getStringExtra("Recurso");
        String recurso1 = agrupamento_verfrasea.getStringExtra("Recurso");

        TextView RECURSO = (TextView) findViewById(R.id.dbRecurso_frasea);
        RECURSO.setText(recurso);

        final String username = verfrasea.getStringExtra("Username");

        //------------------ Tabela dos agrupamentos ---------------------------

        tableHelper = new TableHelper_FraseA(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_frases_agrupamentos);
        tb.setColumnCount(1);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders_Agrupamentos()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes_Agrupamentos(id)));

        //--------------------- ver Agrupamento Especifico --------------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent veragrupamento_frasea = new Intent(ver_FraseA.this, verAgrupamento.class );

                veragrupamento_frasea.putExtra("Nome", ((String[])clickedData)[0]);
                veragrupamento_frasea.putExtra("Descricao", ((String[])clickedData)[1]);
                veragrupamento_frasea.putExtra("Data", ((String[])clickedData)[2]);
                veragrupamento_frasea.putExtra("Criador", ((String[])clickedData)[3]);
                veragrupamento_frasea.putExtra("ID", ((String[])clickedData)[4]);


                veragrupamento_frasea.putExtra("Username", username);

                startActivity(veragrupamento_frasea);
            }
        });

        //------------------------------ Lista --------------------------------------

        LinearLayout lista = (LinearLayout) findViewById(R.id.lista_ver_frasea);
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista = new Intent(ver_FraseA.this, MainFraseAnalista.class);
                String username = verfrasea.getStringExtra("Username");
                lista.putExtra("Username", username);
                startActivity(lista);
            }
        });

        //-------------------------------- Agrupamentos ---------------

        LinearLayout agrupamentos = (LinearLayout) findViewById(R.id.agrupamento_ver_frasea);
        agrupamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verfrasea = getIntent();
                String username = verfrasea.getStringExtra("Username");
                Intent agrupamento_verfrasea = new Intent(ver_FraseA.this, Agrupamentos.class);
                agrupamento_verfrasea.putExtra("Username", username);
                startActivity(agrupamento_verfrasea);
            }
        });

        //----------------------------------- Padrao ------------------------------------

        LinearLayout padroes = (LinearLayout) findViewById(R.id.padrao_ver_frasea);
        padroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent padroes_verfrasea = new Intent(ver_FraseA.this, Padroes.class);
                padroes_verfrasea.putExtra("Username", username);
                startActivity(padroes_verfrasea);
            }
        });

        //----------------------------- Perfil ---------------------------------------

        LinearLayout perfil = (LinearLayout) findViewById(R.id.perfil_ver_frasea);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verfrasea = getIntent();
                String username = verfrasea.getStringExtra("Username");
                Intent perfil_verfrasea = new Intent(ver_FraseA.this, Perfil_Analista.class);
                perfil_verfrasea.putExtra("Username", username);
                startActivity(perfil_verfrasea);
            }
        });


        //----------------------------- Terminar Sessão -------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_verfrasea = (LinearLayout)findViewById(R.id.terminar_ver_frasea);
        terminar_verfrasea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_verfrasea = new Intent(ver_FraseA.this, Login.class);
                        startActivity(sair_verfrasea);
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
