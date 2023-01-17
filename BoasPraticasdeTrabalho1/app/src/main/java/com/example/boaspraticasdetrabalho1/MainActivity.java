package com.example.boaspraticasdetrabalho1;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.Toast;

import de.codecrafters.tableview.TableView;
import de.codecrafters.tableview.listeners.TableDataClickListener;
import de.codecrafters.tableview.toolkit.SimpleTableDataAdapter;
import de.codecrafters.tableview.toolkit.SimpleTableHeaderAdapter;

public class MainActivity extends AppCompatActivity {

    private final AppCompatActivity activity = MainActivity.this;
    private DBHelper helper;
    private DBAdapter adapter;
    TableView<String[]> tb;
    TableHelper tableHelper;
    static final String username = "Username";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        Intent tabela = getIntent();
        final String username = tabela.getStringExtra("Username");

        Intent lista_createphrase = getIntent();
        String username1 = lista_createphrase.getStringExtra("Username");


        tableHelper = new TableHelper(this);
        TableView tb = (TableView<String[]>) findViewById(R.id.tableView);
        tb.setColumnCount(3);
        tb.setHeaderBackgroundColor(Color.parseColor("#476B9D"));
        tb.setHeaderAdapter(new SimpleTableHeaderAdapter(this, tableHelper.getSpaceProbeHeaders()));
        tb.setDataAdapter(new SimpleTableDataAdapter(this, tableHelper.getSpaceProbes(username)));


        //----------------------- Ver frase especifica -------------------------------

        tb.addDataClickListener(new TableDataClickListener<String[]>() {
            @Override
            public void onDataClicked(int rowIndex, String[] clickedData) {
                Intent verfrase = new Intent(MainActivity.this, verFrase.class );

                verfrase.putExtra("Sujeito", ((String[])clickedData)[0]);
                verfrase.putExtra("Acao", ((String[])clickedData)[1]);
                verfrase.putExtra("Recetor", ((String[])clickedData)[2]);
                verfrase.putExtra("Artefacto", ((String[])clickedData)[3]);
                verfrase.putExtra("Data", ((String[])clickedData)[4]);
                verfrase.putExtra("Recurso", ((String[])clickedData)[5]);
                verfrase.putExtra("ID", ((String[])clickedData)[6]);

                verfrase.putExtra("Username", username);

                startActivity(verfrase);
            }
        });

        helper = new DBHelper(activity);

        //------------------------------- Lista ----------------------

        LinearLayout lista = (LinearLayout) findViewById(R.id.lista_lista);
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Já se encontra na sua lista de frases", Toast.LENGTH_SHORT).show();

            }
        });

        //-------------------- Procurar ---------------------------------

        LinearLayout procurar = (LinearLayout) findViewById(R.id.procurar_lista);
        procurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent search = new Intent(MainActivity.this, SearchOption.class);
                search.putExtra("Username", username);
                startActivity(search);
            }
        });


        //------------------------------ Perfil -------------------------------------

        LinearLayout perfil = (LinearLayout)findViewById(R.id.perfil_lista);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent perfil = new Intent(MainActivity.this, Perfil.class);
                Bundle USER_PERFIL = new Bundle();
                USER_PERFIL.putString("Username", username);
                perfil.putExtras(USER_PERFIL);
                startActivity(perfil);
            }
        });


        //-------------------- Terminar Sessão ------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final LinearLayout terminar_lista = (LinearLayout)findViewById(R.id.terminar_lista);
        terminar_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_lista = new Intent(MainActivity.this, Login.class);
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


// ------------------------ Criar Frase -------------------------------------------------

        FloatingActionButton fab = findViewById(R.id.nova_frase);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent create = new Intent(MainActivity.this, CreatePhrase.class);
                create.putExtra("Username", username);
                startActivity(create);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


}


