package com.example.boaspraticasdetrabalho1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin = (Button)findViewById(R.id.login);
        final EditText Username = (EditText)findViewById(R.id.username);
        final EditText Password = (EditText)findViewById(R.id.password);

        final DBHelper databaseHelper = new DBHelper(Login.this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isExist = databaseHelper.checkUser(Username.getText().toString(), Password.getText().toString());
                boolean Gestor = databaseHelper.checkGestor(Username.getText().toString(), Password.getText().toString());
                boolean Analista = databaseHelper.checkAnalista(Username.getText().toString(), Password.getText().toString());

                if(isExist){
                   // Intent perfil = new Intent(Login.this, Perfil.class);


                    Intent tabela = new Intent(Login.this, MainActivity.class);
                    tabela.putExtra("Username", Username.getText().toString());
                    startActivity(tabela);
                    Log.w("catarina", "Entrei no if");
                }

                else if(Gestor){
                    Intent lista_processo = new Intent(Login.this, MainProcesso.class);
                    lista_processo.putExtra("Username", Username.getText().toString());
                    startActivity(lista_processo);
                    Log.w("catarina", "Entrei no Gestor");
                }

                else if (Analista) {
                    Intent lista_frase_analista = new Intent(Login.this, MainFraseAnalista.class);
                    lista_frase_analista.putExtra("Username", Username.getText().toString());
                    startActivity(lista_frase_analista);
                }

                else {
                    /*if(Gestor){
                        Toast.makeText(Login.this, "gestor", Toast.LENGTH_SHORT).show();
                    }*/
                    Log.w("catarina", "Entrei no else");
                    Password.setText(null);
                    Toast.makeText(Login.this, "Login failed. Invalid username or password", Toast.LENGTH_SHORT).show();

                }
            }
        });
    }
}
