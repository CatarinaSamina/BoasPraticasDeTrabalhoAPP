package com.example.boaspraticasdetrabalho1;

import android.content.Context;

import java.util.ArrayList;

public class TableHelper_Agrupamentos {

    Context c;
    private String[] spaceProbeHeaders={"Nome", "Descrição", "Criador"};
    private String[][] spaceProbes;
    public TableHelper_Agrupamentos(Context c){
        this.c = c;
    }

    public String[]getSpaceProbeHeaders(){
        return spaceProbeHeaders;
    }

    public String[][] getSpaceProbes(){
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new DBAdapter_Agrupamentos(c).retrieveSpacecrafts();
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


    private String[] spaceProbeHeaders_Frases={"Ação", "Artefacto"};
    private String[][] spaceProbes_Frases;

    public String[]getSpaceProbeHeaders_Frases(){
        return spaceProbeHeaders_Frases;
    }

    public String[][] getSpaceProbes_Frases(String a){
        ArrayList<Spacecraft> spacecrafts = new DBAdapter_Agrupamentos(c).veragrupamento_frases(a);
        Spacecraft s;
        spaceProbes_Frases = new String[spacecrafts.size()][6];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes_Frases[i][0] = s.getAction_name();
            spaceProbes_Frases[i][1] = s.getArtefact();
            spaceProbes_Frases[i][2] = s.getSubject();
            spaceProbes_Frases[i][3] = s.getData();
            spaceProbes_Frases[i][4] = s.getResource();
            spaceProbes_Frases[i][5] = s.getId();

        }
        return spaceProbes_Frases;
    }

    private String[] spaceProbeHeaders_Padroes={"Nome", "Descrição"};
    private String[][] spaceProbes_Padroes;

    public String[]getSpaceProbeHeaders_Padroes(){
        return spaceProbeHeaders_Padroes;
    }

    public String[][] getSpaceProbes_Padroes(String a){
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new DBAdapter_Agrupamentos(c).padroes_veragrupamento(a);
        Spacecraft_Agrupamentos s;
        spaceProbes_Padroes = new String[spacecrafts.size()][5];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes_Padroes[i][0] = s.getName_group();
            spaceProbes_Padroes[i][1] = s.getDescription();
            spaceProbes_Padroes[i][2] = s.getCriador();
            spaceProbes_Padroes[i][3] = s.getData();
            spaceProbes_Padroes[i][4] = s.getId();

        }
        return spaceProbes_Padroes;
    }

}
