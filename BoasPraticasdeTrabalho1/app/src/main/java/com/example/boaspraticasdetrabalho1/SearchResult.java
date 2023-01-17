package com.example.boaspraticasdetrabalho1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class SearchResult extends AppCompatActivity {

    private final AppCompatActivity activity = SearchResult.this;
    private DBHelper helper;
    private DBAdapter adapter;
    TableView<String[]> tb;
    TableHelper tableHelper;
    TableHelperSearch tableHelperSearch;
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_result);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //vai buscar username e keyword
        Intent result = getIntent();
        Bundle USERNAME = new Bundle();
        Bundle KEYWORD = new Bundle();
        Bundle CHOICE = new Bundle();
        USERNAME = result.getExtras();
        final String username = USERNAME.getString("Username");
        KEYWORD = result.getExtras();
        final String keyword = KEYWORD.getString("Keyword");
        CHOICE = result.getExtras();
        final String choice = CHOICE.getString("Choice");


        Log.w("keyword", String.valueOf(keyword));
        Log.w("username", String.valueOf(username));
        Log.w("choice", String.valueOf(choice));


        tableHelper = new TableHelper(this);
        tableHelperSearch = new TableHelperSearch(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView);
        tb.setColumnCount(3);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelperSearch.getSpaceProbes_Search(username, keyword, choice)));

        //----------------------- Ver frase especifica -------------------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent verfrase = new Intent(SearchResult.this, verFrase.class );

                verfrase.putExtra("ID", ((String[])clickedData)[0]);
                verfrase.putExtra("Sujeito", ((String[])clickedData)[1]);
                verfrase.putExtra("Acao", ((String[])clickedData)[2]);
                verfrase.putExtra("Artefacto", ((String[])clickedData)[3]);
                verfrase.putExtra("Data", ((String[])clickedData)[4]);
                verfrase.putExtra("Recurso", ((String[])clickedData)[5]);

                Bundle USER_VERFRASE = new Bundle();
                USER_VERFRASE.putString("Username", username);
                verfrase.putExtras(USER_VERFRASE);
                startActivity(verfrase);
                Toast.makeText(SearchResult.this, ((String[]) clickedData)[3], Toast.LENGTH_SHORT).show();
            }
        });

        helper = new DBHelper(activity);

        //-------------------------- Lista de frases ------------------------------------------

        LinearLayout lista = (LinearLayout)findViewById(R.id.lista_search_result);
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent tabela = new Intent(SearchResult.this, MainActivity.class);
                tabela.putExtra("Username", username);
                startActivity(tabela);
            }
        });

        //-----------------------------------Entrar no perfil-------------------------------

        LinearLayout perfil = (LinearLayout)findViewById(R.id.perfil_search_result);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent perfil = new Intent(SearchResult.this, Perfil.class);
                Bundle USER_PERFIL = new Bundle();
                USER_PERFIL.putString("Username", username);
                perfil.putExtras(USER_PERFIL);
                startActivity(perfil);
            }
        });

        //-------------------- Procurar ---------------------------------

        LinearLayout procurar = (LinearLayout) findViewById(R.id.procurar_search_option);
        procurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(SearchResult.this, "Já se encontra no procurar", Toast.LENGTH_SHORT).show();

            }
        });


        //-------------------- Terminar SessÃ£o ------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final LinearLayout terminar_lista = (LinearLayout)findViewById(R.id.terminar_search_result);
        terminar_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_lista = new Intent(SearchResult.this, Login.class);
                        startActivity(sair_lista);
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
