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

public class MainFraseAnalista extends AppCompatActivity {

    private final AppCompatActivity activity = MainFraseAnalista.this;
    private DBHelper helper;
    private DBAdapter_FraseA adapter;
    TableView<String[]> tb;
    TableHelper_FraseA tableHelper_FraseA;
    static final String username = "Username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_frase_analista);

        Intent lista_fraseanalista = getIntent();
        Bundle USERNAME = new Bundle();
        USERNAME = lista_fraseanalista.getExtras();
        final String username = USERNAME.getString("Username");

        tableHelper_FraseA = new TableHelper_FraseA(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_fraseanalista);
        tb.setColumnCount(3);
        tb.setColumnWeight(0, 100);
        tb.setColumnWeight(1, 100);
        tb.setColumnWeight(2, 100);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper_FraseA.getSpaceProbeHeaders()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper_FraseA.getSpaceProbes()));

        //----------------------- Ver frase especifica -------------------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent verfrasea = new Intent(MainFraseAnalista.this, ver_FraseA.class );

                verfrasea.putExtra("Sujeito", ((String[])clickedData)[0]);
                verfrasea.putExtra("Acao", ((String[])clickedData)[1]);
                verfrasea.putExtra("Artefacto", ((String[])clickedData)[2]);
                verfrasea.putExtra("Data", ((String[])clickedData)[3]);
                verfrasea.putExtra("Recurso", ((String[])clickedData)[4]);
                verfrasea.putExtra("ID", ((String[])clickedData)[5]);

                verfrasea.putExtra("Username", username);

                startActivity(verfrasea);

            }
        });

        helper = new DBHelper(activity);


        //------------------------------- Lista -----------------------------------

        LinearLayout lista = (LinearLayout) findViewById(R.id.lista_frasea);
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainFraseAnalista.this, "Já se encontra na lista de frases", Toast.LENGTH_SHORT).show();
            }
        });

        //------------------------------ Agrupamentos -------------------------------

        LinearLayout agrupamentos = (LinearLayout) findViewById(R.id.agrupamento_frasea);
        agrupamentos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent agrupamento_mainfrase = new Intent(MainFraseAnalista.this, Agrupamentos.class);
                agrupamento_mainfrase.putExtra("Username", username);
                startActivity(agrupamento_mainfrase);
            }
        });

        //------------------------------- Perfil ---------------------------------------

        LinearLayout perfil = (LinearLayout) findViewById(R.id.perfil_frasea);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil_analista = new Intent(MainFraseAnalista.this, Perfil_Analista.class);
                Bundle USER_PERFIL = new Bundle();
                USER_PERFIL.putString("Username", username);
                perfil_analista.putExtras(USER_PERFIL);
                startActivity(perfil_analista);
            }
        });

        //------------------------------ Padroes -------------------------------------

        LinearLayout padroes = (LinearLayout) findViewById(R.id.padrao_frasea);
        padroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent padroes_frase = new Intent(MainFraseAnalista.this, Padroes.class);
                padroes_frase.putExtra("Username", username);
                startActivity(padroes_frase);
            }
        });


        //--------------------- Terminar Sessão ---------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_frasea = (LinearLayout)findViewById(R.id.terminar_frasea);
        terminar_frasea.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_frasea = new Intent(MainFraseAnalista.this, Login.class);
                        startActivity(sair_frasea);
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
