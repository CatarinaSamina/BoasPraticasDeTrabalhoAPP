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

public class Produto extends AppCompatActivity {

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
        setContentView(R.layout.activity_produto);

        Intent produto_veratividade = getIntent();

        final String id = produto_veratividade.getStringExtra("ID_produto");

        final String username = produto_veratividade.getStringExtra("Username");

        String nome = produto_veratividade.getStringExtra("Nome");
        TextView NOME = (TextView) findViewById(R.id.dbNome_produto);
        NOME.setText(nome);

        String descricao = produto_veratividade.getStringExtra("Descricao");
        TextView DESCRICAO = (TextView) findViewById(R.id.dbDescricao_produto);
        DESCRICAO.setText(descricao);

        String data = produto_veratividade.getStringExtra("Data");
        TextView DATA = (TextView) findViewById(R.id.dbData_produto);
        DATA.setText(data);

        String criador = produto_veratividade.getStringExtra("Criador");
        TextView CRIADOR = (TextView) findViewById(R.id.dbCriador_produto);
        CRIADOR.setText(criador);

        //-------------------- ver atividades identificado ----------------------------

        tableHelper = new TableHelper_Atividades(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_atividades_produto);
        tb.setColumnCount(2);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders_atividadeproduto()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes_atividadeproduto(id)));

        //------------------------ ver atividade especifica --------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent veratividade = new Intent(Produto.this, verAtividade.class );

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

        final LinearLayout perfil = (LinearLayout) findViewById(R.id.perfil_produto);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil_produto = new Intent(Produto.this, Perfil_Processo.class);
                perfil_produto.putExtra("Username", username);
                startActivity(perfil_produto);
            }
        });

        //--------------------------- Processos --------------------------------------

        final LinearLayout processo = (LinearLayout) findViewById(R.id.lista_produto);
        processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista_produto = new Intent(Produto.this, MainProcesso.class);
                lista_produto.putExtra("Username", username);
                startActivity(lista_produto);
            }
        });

        //--------------------------------Atividade -------------------------------------

        final LinearLayout atividade = (LinearLayout) findViewById(R.id.atividade_produto);
        atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent atividade_produto = new Intent(Produto.this, Atividades.class);
                atividade_produto.putExtra("Username", username);
                startActivity(atividade_produto);
            }
        });

        //----------------------------- Terminar sessão -------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_processo = (LinearLayout)findViewById(R.id.terminar_produto);
        terminar_processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_produto = new Intent(Produto.this, Login.class);
                        startActivity(sair_produto);
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
