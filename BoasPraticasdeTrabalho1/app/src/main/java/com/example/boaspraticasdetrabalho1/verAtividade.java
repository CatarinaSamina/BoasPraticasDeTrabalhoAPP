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

public class verAtividade extends AppCompatActivity {

    TableView<String[]> tb;
    TableView<String[]> tb1;
    TableHelper_Atividades tableHelper;
    TableHelper_Atividades tableHelper1;


    static final String id = "ID_atividade";
    static final String nome = "Nome";
    static final String descricao = "Descricao";
    static final String data = "Data";
    static final String criador = "Criador";
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_atividade);

        Intent veratividade = getIntent();
        Intent atividade_verprocesso = getIntent();

        final String id = veratividade.getStringExtra("ID_atividade");
        final String id1 = atividade_verprocesso.getStringExtra("ID_atividade");

        final String username = veratividade.getStringExtra("Username");
        final String username1 = atividade_verprocesso.getStringExtra("Username");

        String nome = veratividade.getStringExtra("Nome");
        String nome1 = atividade_verprocesso.getStringExtra("Nome");

        TextView NOME_AGRUPAMENTO = (TextView) findViewById(R.id.dbNome_veratividade);
        NOME_AGRUPAMENTO.setText(nome);

        String descricao = veratividade.getStringExtra("Descricao");
        String descricao1 = atividade_verprocesso.getStringExtra("Descricao");

        TextView DESCRICAO = (TextView) findViewById(R.id.dbDescricao_veratividade);
        DESCRICAO.setText(descricao);

        String data = veratividade.getStringExtra("Data");
        String data1 = atividade_verprocesso.getStringExtra("Data");

        TextView DATA = (TextView) findViewById(R.id.dbData_veratividade);
        DATA.setText(data);

        String criador = veratividade.getStringExtra("Criador");
        String criador1 = atividade_verprocesso.getStringExtra("Criador");

        TextView CRIADOR = (TextView) findViewById(R.id.dbCriador_veratividade);
        CRIADOR.setText(criador);

        //--------------------------- ver produto -------------------------------

        tableHelper = new TableHelper_Atividades(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView_produtos_averatividade);
        tb.setColumnCount(2);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders_produto()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes_produto(id)));

        //--------------------------- ver detalhes do produto ----------------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent produto_veratividade = new Intent(verAtividade.this, Produto.class );

                produto_veratividade.putExtra("Nome", ((String[])clickedData)[0]);
                produto_veratividade.putExtra("Descricao", ((String[])clickedData)[1]);
                produto_veratividade.putExtra("Criador", ((String[])clickedData)[2]);
                produto_veratividade.putExtra("Data", ((String[])clickedData)[3]);
                produto_veratividade.putExtra("ID_produto", ((String[])clickedData)[4]);
                produto_veratividade.putExtra("Username", username);

                startActivity(produto_veratividade);

            }
        });

        //--------------------------- ver funçoes -----------------------------------------

        tableHelper1 = new TableHelper_Atividades(this);
        TableView tb1 = (TableView<String[]>) findViewById(R.id.tableView_papeis_veratividade);
        tb1.setColumnCount(2);
        tb1.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb1.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper1.getSpaceProbeHeaders_funcao()));
        tb1.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper1.getSpaceProbes_funcao(id)));

        //---------------------------- ver funcao especifica ----------------------

        tb1.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent funcao_veratividade = new Intent(verAtividade.this, Funcoes.class );

                funcao_veratividade.putExtra("Nome", ((String[])clickedData)[0]);
                funcao_veratividade.putExtra("Descricao", ((String[])clickedData)[1]);
                funcao_veratividade.putExtra("Criador", ((String[])clickedData)[2]);
                funcao_veratividade.putExtra("Data", ((String[])clickedData)[3]);
                funcao_veratividade.putExtra("ID_produto", ((String[])clickedData)[4]);
                funcao_veratividade.putExtra("Username", username);

                startActivity(funcao_veratividade);
            }
        });

        // ------------------------------ Perfil --------------------------------------------

        final LinearLayout perfil = (LinearLayout) findViewById(R.id.perfil_veratividade);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent perfil_veratividade = new Intent(verAtividade.this, Perfil_Processo.class);
                perfil_veratividade.putExtra("Username", username);
                startActivity(perfil_veratividade);
            }
        });

        //--------------------------- Processos --------------------------------------

        final LinearLayout processo = (LinearLayout) findViewById(R.id.lista_veratividade);
        processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista_veratividade = new Intent(verAtividade.this, MainProcesso.class);
                lista_veratividade.putExtra("Username", username);
                startActivity(lista_veratividade);
            }
        });

        //--------------------------------Atividade -------------------------------------

        final LinearLayout atividade = (LinearLayout) findViewById(R.id.atividade_veratividade);
        atividade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent atividade_veratividade = new Intent(verAtividade.this, Atividades.class);
                atividade_veratividade.putExtra("Username", username);
                startActivity(atividade_veratividade);
            }
        });

        //----------------------------- Terminar sessão -------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_processo = (LinearLayout)findViewById(R.id.terminar_veratividade);
        terminar_processo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_veratividade = new Intent(verAtividade.this, Login.class);
                        startActivity(sair_veratividade);
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
