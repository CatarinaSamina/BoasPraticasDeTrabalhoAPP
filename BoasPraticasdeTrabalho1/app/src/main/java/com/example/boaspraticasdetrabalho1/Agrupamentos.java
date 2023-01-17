package com.example.boaspraticasdetrabalho1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class Agrupamentos extends AppCompatActivity {

    private final AppCompatActivity activity = Agrupamentos.this;
    private DBHelper helper;
    private DBAdapter_Agrupamentos adapter;
    TableView<String[]> tb;
    TableHelper_Agrupamentos tableHelper;
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agrupamentos);

        Intent agrupamento_mainfrase = getIntent();
        final String username = agrupamento_mainfrase.getStringExtra("Username");
        Intent agrupamento_verfrasea = getIntent();
        String username1 = agrupamento_verfrasea.getStringExtra("Username");
        Intent agrupamento_padroes = getIntent();
        String username2 = agrupamento_padroes.getStringExtra("Username");




        tableHelper = new TableHelper_Agrupamentos(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_Agrupamentos);
        tb.setColumnCount(3);
        tb.setColumnWeight(0, 100);
        tb.setColumnWeight(1, 100);
        tb.setColumnWeight(2, 100);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes()));


        //----------------------------- ver agrupamento detalhado -----------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent veragrupamento = new Intent(Agrupamentos.this, verAgrupamento.class );

                veragrupamento.putExtra("Nome", ((String[])clickedData)[0]);
                veragrupamento.putExtra("Descricao", ((String[])clickedData)[1]);
                veragrupamento.putExtra("Data", ((String[])clickedData)[2]);
                veragrupamento.putExtra("Criador", ((String[])clickedData)[3]);
                veragrupamento.putExtra("ID", ((String[])clickedData)[4]);

                veragrupamento.putExtra("Username", username);
                startActivity(veragrupamento);

            }
        });

        helper = new DBHelper(activity);

        //------------------------------- Lista de frases ----------------------------------

        LinearLayout lista_frases = (LinearLayout) findViewById(R.id.lista_agrupamentos);
        lista_frases.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista_frases = new Intent(Agrupamentos.this, MainFraseAnalista.class);
                startActivity(lista_frases);
            }
        });

        //---------------------------------- Agrupamentos ----------------------------------

        LinearLayout agrupamento = (LinearLayout) findViewById(R.id.agrupamento_agrupamentos);
        agrupamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Agrupamentos.this, "Já se encontra na lista de agrupamentos", Toast.LENGTH_SHORT).show();
            }
        });

        //------------------------- Perfil ------------------------------------------------

        LinearLayout perfil_agrupamentos = (LinearLayout) findViewById(R.id.perfil_agrupamentos);
        perfil_agrupamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil_agrupamentos = new Intent(Agrupamentos.this, Perfil_Analista.class);
                perfil_agrupamentos.putExtra("Username", username);
                startActivity(perfil_agrupamentos);
            }
        });

        //-------------------------------- Padroes -------------------------------------

        LinearLayout padroes = (LinearLayout) findViewById(R.id.padrao_agrupamentos);
        padroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent padroes_agrupamentos = new Intent(Agrupamentos.this, Padroes.class);
                padroes_agrupamentos.putExtra("Username", username);
                startActivity(padroes_agrupamentos);
            }
        });

        //----------------------------- Terminar Sessão --------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_agrupamentos = (LinearLayout)findViewById(R.id.terminar_agrupamentos);
        terminar_agrupamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_agrupamentos = new Intent(Agrupamentos.this, Login.class);
                        startActivity(sair_agrupamentos);
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
