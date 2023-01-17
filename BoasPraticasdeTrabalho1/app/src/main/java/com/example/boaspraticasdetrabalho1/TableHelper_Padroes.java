package com.example.boaspraticasdetrabalho1;

import android.content.Context;

import java.util.ArrayList;

public class TableHelper_Padroes {

    Context c;
    private String[] spaceProbeHeaders={"Nome", "Descrição", "Criador"};
    private String[][] spaceProbes;
    public TableHelper_Padroes(Context c){
        this.c = c;
    }

    public String[]getSpaceProbeHeaders(){
        return spaceProbeHeaders;
    }

    public String[][] getSpaceProbes(){
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new DBAdapter_Padroes(c).retrieveSpacecrafts();
        Spacecraft_Agrupamentos s;
        spaceProbes = new String[spacecrafts.size()][5];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes[i][0] = s.getName_group();
            spaceProbes[i][1] = s.getDescription();
            spaceProbes[i][2] = s.getCriador();
            spaceProbes[i][3] = s.getData();
            spaceProbes[i][4] = s.getId();
        }
        return spaceProbes;
    }

    private String[] spaceProbeHeaders_Agrupamentos={"Nome", "Descrição"};
    private String[][] spaceProbes_Agrupamentos;

    public String[]getSpaceProbeHeaders_Agrupamentos(){
        return spaceProbeHeaders_Agrupamentos;
    }

    public String[][] getSpaceProbes_Agrupamentos(String a){
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new DBAdapter_Padroes(c).retrieveSpacecrafts_Agrupamentos(a);
        Spacecraft_Agrupamentos s;
        spaceProbes = new String[spacecrafts.size()][5];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes[i][0] = s.getName_group();
            spaceProbes[i][1] = s.getDescription();
            spaceProbes[i][2] = s.getCriador();
            spaceProbes[i][3] = s.getData();
            spaceProbes[i][4] = s.getId();
        }
        return spaceProbes;
    }
}
