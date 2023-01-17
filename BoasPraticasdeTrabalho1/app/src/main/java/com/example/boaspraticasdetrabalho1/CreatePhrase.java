package com.example.boaspraticasdetrabalho1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;




import java.util.Locale;
import java.text.SimpleDateFormat;
import java.util.Date;


public class CreatePhrase extends AppCompatActivity {


    private CreatePhraseHelper helper;
    EditText editDate, editAction, editReceptor, editArtefact, editResource;
    TextView textSubject;
    Button btnAddData, btnCancel;
    TextInputLayout textInputDate, textInputAction, textInputArtefact, textInputReceiver;
    static final String username = "Username";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_phrase);

        textInputDate = findViewById(R.id.TIL_data);
        textInputAction = findViewById(R.id.TIL_Action);
        textInputArtefact = findViewById(R.id.TIL_Artefact);
        textInputReceiver = findViewById(R.id.TIL_receptor);

        helper = new CreatePhraseHelper(this);
        editDate = (EditText) findViewById(R.id.editText_data);
        textSubject = (TextView) findViewById(R.id.TextView_subject);
        editAction = (EditText) findViewById(R.id.editText_Action);
        editReceptor = (EditText) findViewById(R.id.editText_receptor);
        editArtefact = (EditText) findViewById(R.id.editText_Artefact);
        editResource = (EditText) findViewById(R.id.editText_Resource);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnCancel = (Button) findViewById(R.id.btn_cancel);

        Intent create = getIntent();
        final String username = create.getStringExtra("Username");

        textSubject.setText(String.valueOf(username));
        editDate.setText(new SimpleDateFormat("YYYY/MM/dd", Locale.getDefault()).format(new Date()));

        //------------------ Cancelar -------------------------------------------

        final AlertDialog.Builder cancelar = new AlertDialog.Builder(this);

        btnCancel.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        cancelar.setTitle("Frase não guardada");
                        cancelar.setMessage("Tem a certeza que quer sair da página sem guardar? Irá perder os dados inseridos");

                        cancelar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                                Intent lista_cancelar = new Intent(CreatePhrase.this, MainActivity.class);
                                lista_cancelar.putExtra("Username", username);
                                startActivity(lista_cancelar);
                                finish();
                            }
                        });

                        cancelar.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });

                        cancelar.create().show();
                    }

                }
        );

        //-------------------- Procurar ---------------------------------

        final AlertDialog.Builder procurar_sair = new AlertDialog.Builder(this);


        final LinearLayout procurar = (LinearLayout) findViewById(R.id.procurar_create_phrase);
        procurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procurar_sair.setTitle("Frase não guardada");
                procurar_sair.setMessage("Tem a certeza que quer sair da página sem guardar? Irá perder os dados inseridos");
                procurar_sair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent search = new Intent(CreatePhrase.this, SearchOption.class);
                        search.putExtra("Username", username);
                        startActivity(search);
                        finish();
                    }
                });
                procurar_sair.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                procurar_sair.create().show();
            }
        });


        //------------------------------ Perfil ---------------------------------------------------------

        final AlertDialog.Builder perfil_sair = new AlertDialog.Builder(this);

        LinearLayout perfil_create = (LinearLayout) findViewById(R.id.perfil_create_phrase);
        perfil_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                perfil_sair.setTitle("Frase não guardada");
                perfil_sair.setMessage("Tem a certeza que quer sair da página sem guardar? Irá perder os dados inseridos");

                perfil_sair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent perfil_createphrase = new Intent(CreatePhrase.this, Perfil.class);
                        perfil_createphrase.putExtra("Username", username);
                        startActivity(perfil_createphrase);
                        finish();
                    }
                });

                perfil_sair.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                perfil_sair.create().show();
            }

        });

        //------------------------- Listas -----------------------------------------------

        final AlertDialog.Builder lista_sair = new AlertDialog.Builder(this);

        LinearLayout lista_create = (LinearLayout) findViewById(R.id.lista_create_phrase);
        lista_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lista_sair.setTitle("Frase não guardada");
                lista_sair.setMessage("Tem a certeza que quer sair da página sem guardar? Irá perder os dados inseridos");

                lista_sair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent lista_createphrase = new Intent(CreatePhrase.this, MainActivity.class);
                        lista_createphrase.putExtra("Username", username);
                        startActivity(lista_createphrase);
                        finish();
                    }
                });

                lista_sair.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                lista_sair.create().show();
            }

        });

        //---------------------------- Terminar Sessão --------------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        LinearLayout terminar_create = (LinearLayout) findViewById(R.id.terminar_create_phrase);
        terminar_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_lista = new Intent(CreatePhrase.this, Login.class);
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

    public void  addPreData(){
        final AlertDialog.Builder guardar = new AlertDialog.Builder(this);
        guardar.setTitle("Guardar Frase");
        guardar.setMessage("Tem a certeza que pretende guardar os dados inseridos?");
        guardar.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                int isInserted = helper.insertAction(textSubject.getText().toString(), editDate.getText().toString(), editAction.getText().toString());
                String saveAction = editAction.getText().toString();
                int result = 1;
                if (isInserted != -1) {
                    editAction.setText(String.valueOf(isInserted));
                    Log.w("rita", "action");
                    Toast.makeText(CreatePhrase.this, "Nova Frase criada.", Toast.LENGTH_LONG).show();
                    result = AddData();
                } else
                    result = AddData();
                if(result == 0) {
                    Intent create = getIntent();
                    final String username = create.getStringExtra("Username");
                    Intent lista_createphrase = new Intent(CreatePhrase.this, MainActivity.class);
                    lista_createphrase.putExtra("Username", username);
                    Log.w("rita", String.valueOf(username));
                    startActivity(lista_createphrase);
                }
                else
                    editAction.setText(saveAction);
            }
        });
        guardar.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        guardar.create().show();
    }

    public int AddData() {
        boolean isInserted = helper.insertData(editDate.getText().toString(), textSubject.getText().toString(), editAction.getText().toString(), editReceptor.getText().toString(), editArtefact.getText().toString(), editResource.getText().toString());
        if (isInserted == true) {
            Toast.makeText(CreatePhrase.this, "Nova frase criada.", Toast.LENGTH_LONG).show();
            return 0;
        }
        else
            validateReceiver();
        return -1;
    }

    private boolean validateDate(){
        String date = editDate.getText().toString();
        if(date.isEmpty()){
            textInputDate.setError("Preencher Campo.");
            return false;
        }
        else{
            textInputAction.setError(null);
            textInputAction.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateAction(){
        String action = editAction.getText().toString();
        if(action.isEmpty()){
            textInputAction.setError("Preencher Campo.");
            return false;
        }
        else{
            textInputAction.setError(null);
            textInputAction.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateArtefact(){
        String artefact = editArtefact.getText().toString();
        if(artefact.isEmpty()){
            textInputArtefact.setError("Preencher Campo.");
            return false;
        }
        else{
            textInputArtefact.setError(null);
            textInputArtefact.setErrorEnabled(false);
            return true;
        }
    }

    private boolean validateReceiver(){
        String receiver = editReceptor.getText().toString();
        if(receiver.isEmpty()){
            textInputArtefact.setError(null);
            textInputArtefact.setErrorEnabled(false);
            return true;
        }
        else {
            textInputReceiver.setError("Receptor Desconhecido!");
            return false;
        }
    }

    public void confirmInput(View v){
        if(!validateDate() | !validateAction() | !validateArtefact() | !validateReceiver())
            return;
        addPreData();
    }



}


