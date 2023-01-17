package com.example.boaspraticasdetrabalho1;

import android.content.Context;

import java.util.ArrayList;

public class TableHelper_FraseA {

    Context c;
    private String[] spaceProbeHeaders={"Sujeito", "Ação", "Artefacto"};
    private String[][] spaceProbes;
    private String[][] perfil;
    public TableHelper_FraseA(Context c){
        this.c = c;
    }

    public String[]getSpaceProbeHeaders(){
        return spaceProbeHeaders;
    }

    public String[][] getSpaceProbes(){
        ArrayList<Spacecraft> spacecrafts = new DBAdapter_FraseA(c).retrieveSpacecrafts();
        Spacecraft s;
        spaceProbes = new String[spacecrafts.size()][6];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes[i][0] = s.getSubject();
            spaceProbes[i][1] = s.getAction_name();
            spaceProbes[i][2] = s.getArtefact();
            spaceProbes[i][3] = s.getData();
            spaceProbes[i][4] = s.getResource();
            spaceProbes[i][5] = s.getId();

        }
        return spaceProbes;
    }


    private String[] spaceProbeHeaders_Agrupamentos={"Nome do Agrupamento"};
    private String[][] spaceProbes_Agrupamentos;

    public String[]getSpaceProbeHeaders_Agrupamentos(){return spaceProbeHeaders_Agrupamentos; }

    public String[][] getSpaceProbes_Agrupamentos(String a){
        ArrayList<Spacecraft_FraseA> spacecrafts = new DBAdapter_FraseA(c).verfrase_agrupamentos(a);
        Spacecraft_FraseA s;
        spaceProbes_Agrupamentos = new String[spacecrafts.size()][6];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes_Agrupamentos[i][0] = s.getNome_agrupamento();
            spaceProbes_Agrupamentos[i][1] = s.getDescricao_agrupamento();
            spaceProbes_Agrupamentos[i][2] = s.getData_agrupamento();
            spaceProbes_Agrupamentos[i][3] = s.getCriador_agrupamento();
            spaceProbes_Agrupamentos[i][4] = s.getId_agrupamento();
            spaceProbes_Agrupamentos[i][5] = s.getId();

        }
        return spaceProbes_Agrupamentos;
    }

}
