package com.example.boaspraticasdetrabalho1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SearchOption extends AppCompatActivity {

    Button btn_filter_receiver, btn_filter_action, btn_filter_resource;
    EditText text_keyword;
    public int choice;
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_option);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        btn_filter_receiver = (Button) findViewById(R.id.filtra_receiver);
        btn_filter_action = (Button) findViewById(R.id.filtra_acao);
        btn_filter_resource = (Button) findViewById(R.id.filtra_recurso);
        text_keyword = (EditText) findViewById(R.id.editText_keyword);

        Intent search = getIntent();
        Bundle USERNAME = new Bundle();
        USERNAME = search.getExtras();

        final String username = USERNAME.getString("Username");


        final String[] result = {""};
        btn_filter_receiver.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choice = 1;
                        //envia username e KEYWORD
                        Intent result = new Intent(SearchOption.this, SearchResult.class);
                        Bundle USERNAME = new Bundle();
                        Bundle KEYWORD = new Bundle();
                        Bundle CHOICE = new Bundle();
                        USERNAME.putString("Username", username);
                        KEYWORD.putString("Keyword", text_keyword.getText().toString());
                        CHOICE.putString("Choice", String.valueOf(choice));
                        result.putExtras(USERNAME);
                        result.putExtras(KEYWORD);
                        result.putExtras(CHOICE);
                        startActivity(result);
                    }
                }
        );

        btn_filter_action.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choice = 2;
                        Intent result = new Intent(SearchOption.this, SearchResult.class);
                        Bundle USERNAME = new Bundle();
                        Bundle KEYWORD = new Bundle();
                        Bundle CHOICE = new Bundle();
                        USERNAME.putString("Username", username);
                        KEYWORD.putString("Keyword", text_keyword.getText().toString());
                        CHOICE.putString("Choice", String.valueOf(choice));
                        result.putExtras(USERNAME);
                        result.putExtras(KEYWORD);
                        result.putExtras(CHOICE);
                        startActivity(result);
                    }
                }
        );

        btn_filter_resource.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        choice = 3;
                        Intent result = new Intent(SearchOption.this, SearchResult.class);
                        Bundle USERNAME = new Bundle();
                        Bundle KEYWORD = new Bundle();
                        Bundle CHOICE = new Bundle();
                        USERNAME.putString("Username", username);
                        KEYWORD.putString("Keyword", text_keyword.getText().toString());
                        CHOICE.putString("Choice", String.valueOf(choice));
                        result.putExtras(USERNAME);
                        result.putExtras(KEYWORD);
                        result.putExtras(CHOICE);
                        startActivity(result);
                    }
                }
        );

        //-------------------------- Lista de frases ------------------------------------------

        LinearLayout lista = (LinearLayout)findViewById(R.id.lista_search_option);
        lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lista_perfil = new Intent(SearchOption.this, MainActivity.class);
                lista_perfil.putExtra("Username", username);
                startActivity(lista_perfil);
            }
        });

        //-----------------------------------Entrar no perfil-------------------------------

        LinearLayout perfil = (LinearLayout)findViewById(R.id.perfil_search_option);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent perfil = new Intent(SearchOption.this, Perfil.class);
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
                Toast.makeText(SearchOption.this, "Já se encontra no procurar", Toast.LENGTH_SHORT).show();

            }
        });

        //---------------------------Terminar sessao ---------------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_verfrase = (LinearLayout) findViewById(R.id.terminar_search_option);
        terminar_verfrase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessÃ£o?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_verfrase = new Intent(SearchOption.this, Login.class);
                        startActivity(sair_verfrase);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
