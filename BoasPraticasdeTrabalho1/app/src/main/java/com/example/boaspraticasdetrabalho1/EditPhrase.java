package com.example.boaspraticasdetrabalho1;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.app.AlertDialog;


public class EditPhrase extends AppCompatActivity {

    private EditPhraseHelper helper;
    EditText editDate, editAction, editReceptor, editArtefact, editResource;
    TextView textSubject;
    Button btnAddData, btnCancel;
    TextInputLayout textInputDate, textInputAction, textInputArtefact, textInputReceiver;
    static final String username = "Username";
    static final String id_phrase = "ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_phrase);


        Intent editActivity = getIntent();
        final String username = editActivity.getStringExtra("Username");
        final String id_phrase = editActivity.getStringExtra("ID");

        textInputDate = findViewById(R.id.TIL_data);
        textInputAction = findViewById(R.id.TIL_Action);
        textInputArtefact = findViewById(R.id.TIL_Artefact);
        textInputReceiver = findViewById(R.id.TIL_receptor);

        helper = new EditPhraseHelper(this);
        editDate = (EditText) findViewById(R.id.editText_data);
        textSubject = (TextView) findViewById(R.id.TextView_subject);
        editAction = (EditText) findViewById(R.id.editText_Action);
        editReceptor = (EditText) findViewById(R.id.editText_receptor);
        editArtefact = (EditText) findViewById(R.id.editText_Artefact);
        editResource = (EditText) findViewById(R.id.editText_Resource);
        btnAddData = (Button) findViewById(R.id.button_add);
        btnCancel = (Button) findViewById(R.id.btn_cancel);
        int sub_id = helper.getID_Subject(username);
        editDate.setText(String.valueOf(helper.getDate(id_phrase)));
        editAction.setText(String.valueOf(helper.getAction(id_phrase)));
        editReceptor.setText(String.valueOf(helper.getReceiver(id_phrase)));
        editArtefact.setText(String.valueOf(helper.getArtefact(id_phrase)));
        editResource.setText(String.valueOf(helper.getResource(id_phrase)));
        textSubject.setText(String.valueOf(username));

        //------------------------- Cancelar ---------------------------------

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
                                Intent lista_cancelar = new Intent(EditPhrase.this, MainActivity.class);
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

        //------------------------------ Perfil --------------------------------------------

        final AlertDialog.Builder perfil_sair = new AlertDialog.Builder(this);

        LinearLayout perfil = (LinearLayout) findViewById(R.id.perfil_edit_phrase);
        perfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                perfil_sair.setTitle("Frase não guardada");
                perfil_sair.setMessage("Tem a certeza que quer sair da página sem guardar? Irá perder os dados inseridos");

                perfil_sair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent perfil_editphrase = new Intent(EditPhrase.this, Perfil.class);
                        perfil_editphrase.putExtra("Username", username);
                        startActivity(perfil_editphrase);
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


        //------------------------------------- Frases ----------------------------------------

        final AlertDialog.Builder frases_sair = new AlertDialog.Builder(this);

        LinearLayout listas = (LinearLayout) findViewById(R.id.lista_edit_phrase);
        listas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frases_sair.setTitle("Frase não guardada");
                frases_sair.setMessage("Tem a certeza que quer sair da página sem guardar? Irá perder os dados inseridos");

                frases_sair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent frase_editphrase = new Intent(EditPhrase.this, MainActivity.class);
                        frase_editphrase.putExtra("Username", username);
                        startActivity(frase_editphrase);
                        finish();
                    }
                });

                frases_sair.setNegativeButton("Não", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                frases_sair.create().show();
            }
        });

        //-------------------- Procurar ---------------------------------

        final AlertDialog.Builder procurar_sair = new AlertDialog.Builder(this);


        final LinearLayout procurar = (LinearLayout) findViewById(R.id.procurar_edit_phrase);
        procurar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                procurar_sair.setTitle("Frase não guardada");
                procurar_sair.setMessage("Tem a certeza que quer sair da página sem guardar? Irá perder os dados inseridos");
                procurar_sair.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent search = new Intent(EditPhrase.this, SearchOption.class);
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

        //---------------------------------- Terminar Sessão ------------------------------

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

        final LinearLayout terminar_lista = (LinearLayout)findViewById(R.id.terminar_edit_phrase);
        terminar_lista.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                builder.setTitle("Terminar Sessão");
                builder.setMessage("Tem a certeza que quer terminar sessão?");

                builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        Intent sair_editphrase = new Intent(EditPhrase.this, Login.class);
                        startActivity(sair_editphrase);
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
        guardar.setTitle("Atualizar Frase");
        guardar.setMessage("Tem a certeza que pretende atualizar os dados da frase?");
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
                    result = AddData();
                } else
                    result = AddData();
                if(result == 0) {
                    Intent editActivity = getIntent();
                    final String username = editActivity.getStringExtra("Username");
                    Intent backActivity = new Intent(EditPhrase.this, MainActivity.class);
                    backActivity.putExtra("Username", username);
                    startActivity(backActivity);
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
        Intent editActivity = getIntent();
        final String id_phrase = editActivity.getStringExtra("ID");
        boolean isInserted = helper.updateData(editDate.getText().toString(), textSubject.getText().toString(), editAction.getText().toString(), editReceptor.getText().toString(), editArtefact.getText().toString(), editResource.getText().toString(), id_phrase);
        Log.w("id frase", id_phrase);
        if (isInserted == true) {
            Toast.makeText(EditPhrase.this, "Frase atualizada.", Toast.LENGTH_LONG).show();
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
