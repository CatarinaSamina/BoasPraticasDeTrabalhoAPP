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

public class Funcoes extends AppCompatActivity {

    TableView<String[]> tb;
    TableHelper_Atividades tableHelper;

    static final String id = "ID_produto";
    static final String nome = "Nome";
    static final String descricao = "Descricao";
    static final String data = "Data";
    static final String criador = "Criador";
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_funcoes);

        final Intent funcao_veratividade = getIntent();

        final String id = funcao_veratividade.getStringExtra("ID_produto");

        final String username = funcao_veratividade.getStringExtra("Username");

        String nome = funcao_veratividade.getStringExtra("Nome");
        TextView NOME = (TextView) findViewById(R.id.dbNome_funcao);
        NOME.setText(nome);

        String descricao = funcao_veratividade.getStringExtra("Descricao");
        TextView DESCRICAO = (TextView) findViewById(R.id.dbDescricao_funcao);
        DESCRICAO.setText(descricao);

        String data = funcao_veratividade.getStringExtra("Data");
        TextView DATA = (TextView) findViewById(R.id.dbData_funcao);
        DATA.setText(data);

        String criador = funcao_veratividade.getStringExtra("Criador");
        TextView CRIADOR = (TextView) findViewById(R.id.dbCriador_funcao);
        CRIADOR.setText(criador);

        //-------------------- ver atividades identificado ----------------------------

        tableHelper = new TableHelper_Atividades(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_atividades_funcao);
        tb.setColumnCount(2);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders_atividadefuncao()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes_atividadefuncao(id)));

        //------------------------ ver atividade especifica --------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent veratividade = new Intent(Funcoes.this, verAtividade.class );

                veratividade.putExtra("Nome", ((String[])clickedData)[0]);
                veratividade.putExtra("Descricao", ((String[])clickedData)[1]);
                veratividade.putExtra("Data", ((String[])clickedData)[2]);
                veratividade.putExtra("Criador", ((String[])clickedData)[3]);
                veratividade.putExtra("ID_atividade", ((String[])clickedData)[4]);

                veratividade.putExtra("Username", username);
                startActivity(veratividade);

            }
        });

        // ------------------------------ Perfil --------------------------------------------

        final LinearLayout perfil = (LinearLayout) findViewById(R.id.perfil_funcao);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil_funcao = new Intent(Funcoes.this, Perfil_Processo.class);
                perfil_funcao.putExtra("Username", username);
                startActivity(perfil_funcao);
            }
        });

        //--------------------------- Processos --------------------------------------

        final LinearLayout processo = (LinearLayout) findViewById(R.id.lista_funcao);
        processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista_funcao = new Intent(Funcoes.this, MainProcesso.class);
                lista_funcao.putExtra("Username", username);
                startActivity(lista_funcao);
            }
        });

        //--------------------------------Atividade -------------------------------------

        final LinearLayout atividade = (LinearLayout) findViewById(R.id.atividade_funcao);
        atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent atividade_funcao = new Intent(Funcoes.this, Atividades.class);
                atividade_funcao.putExtra("Username", username);
                startActivity(atividade_funcao);
            }
        });

        //----------------------------- Terminar sessão -------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_processo = (LinearLayout)findViewById(R.id.terminar_funcao);
        terminar_processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_funcao = new Intent(Funcoes.this, Login.class);
                        startActivity(sair_funcao);
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
