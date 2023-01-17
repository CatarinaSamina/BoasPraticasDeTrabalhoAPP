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

public class Atividades extends AppCompatActivity {

    private final AppCompatActivity activity = Atividades.this;
    private DBHelper helper;
    private DBAdapter_Atividades adapter;
    TableView<String[]> tb;
    TableHelper_Atividades tableHelper;
    static final String username = "Username";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_atividades);

        Intent atividade_processo = getIntent();
        final String username = atividade_processo.getStringExtra("Username");



        tableHelper = new TableHelper_Atividades(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_Atividades);
        tb.setColumnCount(3);
        tb.setColumnWeight(0, 120);
        tb.setColumnWeight(1, 120);
        tb.setColumnWeight(2, 100);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes()));

        //------------------------------- ver atividade especifica ---------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent veratividade = new Intent(Atividades.this, verAtividade.class );

                veratividade.putExtra("Nome", ((String[])clickedData)[0]);
                veratividade.putExtra("Descricao", ((String[])clickedData)[1]);
                veratividade.putExtra("Data", ((String[])clickedData)[2]);
                veratividade.putExtra("Criador", ((String[])clickedData)[3]);
                veratividade.putExtra("ID_atividade", ((String[])clickedData)[4]);

                veratividade.putExtra("Username", username);
                startActivity(veratividade);

            }
        });

        //---------------------------------- Perfil ---------------------------------

        final LinearLayout perfil_atividade = (LinearLayout) findViewById(R.id.perfil_atividades);
        perfil_atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil_atividades = new Intent(Atividades.this, Perfil_Processo.class);
                perfil_atividades.putExtra("Username", username);
                startActivity(perfil_atividades);
            }
        });

        //--------------------------- Processos --------------------------------------

        final LinearLayout processo = (LinearLayout) findViewById(R.id.lista_atividades);
        processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista_Processo = new Intent(Atividades.this, MainProcesso.class);
                lista_Processo.putExtra("Username", username);
                startActivity(lista_Processo);
            }
        });

        //--------------------------------- Atividades ---------------------------------

        LinearLayout atividade = (LinearLayout) findViewById(R.id.atividade_atividades);
        atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Atividades.this, "Já se encontra na lista de Atividades", Toast.LENGTH_SHORT).show();
            }
        });

        //----------------------------- Terminar sessão -------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_processo = (LinearLayout)findViewById(R.id.terminar_atividades);
        terminar_processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_atividade = new Intent(Atividades.this, Login.class);
                        startActivity(sair_atividade);
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
