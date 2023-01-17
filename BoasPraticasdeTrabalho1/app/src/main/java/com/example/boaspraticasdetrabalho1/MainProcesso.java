package com.example.boaspraticasdetrabalho1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
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

public class MainProcesso extends AppCompatActivity {

    private final AppCompatActivity activity = MainProcesso.this;
    private DBHelper helper;
    TableView<String[]> tb;
    TableHelper_Processo tableHelper_processo;
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_processo);

        final Intent lista_processo = getIntent();
        final String username = lista_processo.getStringExtra("Username");

        tableHelper_processo = new TableHelper_Processo(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableViewProcesso);
        tb.setColumnCount(3);
        tb.setColumnWeight(0, 120);
        tb.setColumnWeight(1, 120);
        tb.setColumnWeight(2, 100);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper_processo.getSpaceProbeHeaders_Processo()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper_processo.getSpaceProbes_Processo()));

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent verprocesso = new Intent(MainProcesso.this, verProcesso.class);

                verprocesso.putExtra("Processo", ((String[])clickedData)[0]);
                verprocesso.putExtra("Descricao", ((String[])clickedData)[1]);
                verprocesso.putExtra("Criador", ((String[])clickedData)[2]);
                verprocesso.putExtra("Data", ((String[])clickedData)[3]);
                verprocesso.putExtra("ID_processo", ((String[])clickedData)[4]);

                verprocesso.putExtra("Username", username);

                startActivity(verprocesso);
            }
        });

        //---------------------------- Atividades ------------------

        LinearLayout atividades = (LinearLayout) findViewById(R.id.atividade_main_processo);
        atividades.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atividade_processo = new Intent(MainProcesso.this, Atividades.class);
                atividade_processo.putExtra("Username", username);
                startActivity(atividade_processo);
            }
        });

        //------------------------------Processos -----------------------------

        LinearLayout processo = (LinearLayout) findViewById(R.id.lista_main_processo);
        processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainProcesso.this, "Já se encontra na lista de processos", Toast.LENGTH_SHORT).show();
            }
        });

        //--------------------------- Perfil -----------------------------------

        final LinearLayout perfil_processo = (LinearLayout) findViewById(R.id.perfil_main_processo);
        perfil_processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent lista_processo = getIntent();
                String username = lista_processo.getStringExtra("Username");

                Intent perfil_Processo = new Intent(MainProcesso.this, Perfil_Processo.class);
                perfil_Processo.putExtra("Username", username);
                startActivity(perfil_Processo);
            }
        });

        //----------------------------- Terminar sessão -------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_processo = (LinearLayout)findViewById(R.id.terminar_main_processo);
        terminar_processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_processo = new Intent(MainProcesso.this, Login.class);
                        startActivity(sair_processo);
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

        helper = new DBHelper(activity);

    }
}
