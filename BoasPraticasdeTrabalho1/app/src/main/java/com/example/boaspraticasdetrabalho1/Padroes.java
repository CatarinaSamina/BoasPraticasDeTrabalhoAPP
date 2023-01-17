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

public class Padroes extends AppCompatActivity {

    private final AppCompatActivity activity = Padroes.this;
    private DBAdapter_Padroes adapter;
    TableView<String[]> tb;
    TableHelper_Padroes tableHelper;
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_padroes);

        Intent padroes_frase = getIntent();
        final String username = padroes_frase.getStringExtra("Username");

        tableHelper = new TableHelper_Padroes(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_Padroes);
        tb.setColumnCount(3);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes()));


        //---------------------- ver detalhes padrao -------------------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent verpadrao = new Intent(Padroes.this, verPadrao.class );

                verpadrao.putExtra("Nome", ((String[])clickedData)[0]);
                verpadrao.putExtra("Descricao", ((String[])clickedData)[1]);
                verpadrao.putExtra("Criador", ((String[])clickedData)[2]);
                verpadrao.putExtra("Data", ((String[])clickedData)[3]);
                verpadrao.putExtra("ID", ((String[])clickedData)[4]);
                verpadrao.putExtra("Username", username);
                startActivity(verpadrao);

            }
        });

        //--------------------------- Padroes ------------------------------------

        LinearLayout padroes = (LinearLayout) findViewById(R.id.padrao_padroes);
        padroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Padroes.this, "Já se encontra na lista de padrões", Toast.LENGTH_SHORT).show();
            }
        });

        //----------------------- Perfil ------------------------------------------

        final LinearLayout perfil = (LinearLayout) findViewById(R.id.perfil_padroes);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent perfil_padroes = new Intent(Padroes.this, Perfil_Analista.class);
                perfil_padroes.putExtra("Username", username);
                startActivity(perfil_padroes);
            }
        });

        //------------------------ Agrupamentos -------------------------

        LinearLayout agrupamento = (LinearLayout) findViewById(R.id.agrupamento_padroes);
        agrupamento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent agrupamento_padroes = new Intent(Padroes.this, Agrupamentos.class);
                agrupamento_padroes.putExtra("Username", username);
                startActivity(agrupamento_padroes);
            }
        });

        //--------------------------- Frases -------------------------------

        LinearLayout frase = (LinearLayout) findViewById(R.id.lista_padroes);
        frase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent frase_padroes = new Intent(Padroes.this, MainFraseAnalista.class);
                frase_padroes.putExtra("Username", username);
                startActivity(frase_padroes);
            }
        });

        //------------------------ Terminar Sessão ---------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_padroes = (LinearLayout)findViewById(R.id.terminar_padroes);
        terminar_padroes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_padroes = new Intent(Padroes.this, Login.class);
                        startActivity(sair_padroes);
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
