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

public class verProcesso extends AppCompatActivity {

    TableView<String[]> tb;
    TableHelper_Processo tableHelper;

    static final String processo = "Processo";
    static final String descricao = "Descricao";
    static final String data = "Data";
    static final String id_processo = "ID_processo";
    static final String id_atividade = "ID_atividade";


    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_processo);

        final Intent verprocesso = getIntent();
        final String username = verprocesso.getStringExtra("Username");
        final String id_process  = verprocesso.getStringExtra("ID_processo");

        String processo = verprocesso.getStringExtra("Processo");
        TextView NOME_PROCESSO = (TextView) findViewById(R.id.dbNome_verprocesso);
        NOME_PROCESSO.setText(processo);

        String descricao = verprocesso.getStringExtra("Descricao");
        TextView DESCRICAO = (TextView) findViewById(R.id.dbDescricao_verprocesso);
        DESCRICAO.setText(descricao);

        String data = verprocesso.getStringExtra("Data");
        TextView DATA = (TextView) findViewById(R.id.dbData_verprocesso);
        DATA.setText(data);

        String criador = verprocesso.getStringExtra("Criador");
        TextView CRIADOR = (TextView) findViewById(R.id.dbCriador_verprocesso);
        CRIADOR.setText(criador);

        //--------------------------------Atividades associadas -------------------------

        tableHelper = new TableHelper_Processo(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_atividades_associadas);
        tb.setColumnCount(2);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders_Atividade()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes_Atividade(id_process)));

        //------------------------- ver atividade detalhada ------------------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent atividade_verprocesso = new Intent(verProcesso.this, verAtividade.class );

                atividade_verprocesso.putExtra("Nome", ((String[])clickedData)[0]);
                atividade_verprocesso.putExtra("Descricao", ((String[])clickedData)[1]);
                atividade_verprocesso.putExtra("Criador", ((String[])clickedData)[2]);
                atividade_verprocesso.putExtra("Data", ((String[])clickedData)[3]);
                atividade_verprocesso.putExtra("ID_atividade", ((String[])clickedData)[4]);

                atividade_verprocesso.putExtra("Username", username);

                startActivity(atividade_verprocesso);

            }
        });


        //---------------------------- lista de processos ---------------------------

        LinearLayout lista_verprocesso = (LinearLayout)findViewById(R.id.lista_ver_processo);
        lista_verprocesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verprocesso = getIntent();
                String username = verprocesso.getStringExtra("Username");

                Intent Lista_verprocesso = new Intent(verProcesso.this, MainProcesso.class);
                Lista_verprocesso.putExtra("Username", username);
                startActivity(Lista_verprocesso);
            }
        });

        //---------------------------Atividades ----------------------------------------

        LinearLayout atividade = (LinearLayout) findViewById(R.id.atividade_ver_processo);
        atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent atividade_verprocesso = new Intent(verProcesso.this, Atividades.class);
                atividade_verprocesso.putExtra("Username", username);
                startActivity(atividade_verprocesso);
            }
        });

        //---------------------------------- Perfil ----------------------------------------

        LinearLayout perfil_verprocesso = (LinearLayout) findViewById(R.id.perfil_ver_processo);
        perfil_verprocesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent verprocesso = getIntent();
                String username = verprocesso.getStringExtra("Username");

                Intent perfil_verprocesso = new Intent(verProcesso.this, Perfil_Processo.class);
                perfil_verprocesso.putExtra("Username", username);
                startActivity(perfil_verprocesso);
            }
        });

        //----------------------------------- Terminar Sessão --------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_verprocesso = (LinearLayout) findViewById(R.id.terminar_ver_processo);
        terminar_verprocesso.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_verprocesso = new Intent(verProcesso.this, Login.class);
                        startActivity(sair_verprocesso);
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
