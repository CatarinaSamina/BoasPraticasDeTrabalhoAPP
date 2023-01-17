package com.example.boaspraticasdetrabalho1;

import android.content.Context;

import java.util.ArrayList;

public class TableHelper_Processo {
    Context c;
    private String[] spaceProbeHeaders_Processo={"Nome", "Descrição", "Empresa"};
    private String[][] spaceProbes_Processo;
    public TableHelper_Processo(Context c){
        this.c = c;
    }

    public String[]getSpaceProbeHeaders_Processo(){
        return spaceProbeHeaders_Processo;
    }

    public String[][] getSpaceProbes_Processo(){
        ArrayList<Spacecraft_Processo> spacecrafts_processo = new DBAdapter_Processo(c).retrieveSpacecrafts_process();
        Spacecraft_Processo s;
        spaceProbes_Processo = new String[spacecrafts_processo.size()][5];
        for (int i = 0; i < spacecrafts_processo.size(); i++){
            s = spacecrafts_processo.get(i);
            spaceProbes_Processo[i][0] = s.getProcess();
            spaceProbes_Processo[i][1] = s.getDescription();
            spaceProbes_Processo[i][2] = s.getAccount_name();
            spaceProbes_Processo[i][3] = s.getData();
            spaceProbes_Processo[i][4] = s.getId_process();

        }
        return spaceProbes_Processo;
    }

    private String[] spaceProbeHeaders_Atividade={"Nome", "Descrição"};
    private String[][] spaceProbes_Atividade;

    public String[]getSpaceProbeHeaders_Atividade(){
        return spaceProbeHeaders_Atividade;
    }

    public String[][] getSpaceProbes_Atividade(String a){
        ArrayList<Spacecraft_Processo> spacecrafts_processo = new DBAdapter_Processo(c).retrieveSpacecrafts_atividade(a);
        Spacecraft_Processo s;
        spaceProbes_Processo = new String[spacecrafts_processo.size()][6];
        for (int i = 0; i < spacecrafts_processo.size(); i++){
            s = spacecrafts_processo.get(i);
            spaceProbes_Processo[i][0] = s.getProcess();
            spaceProbes_Processo[i][1] = s.getDescription();
            spaceProbes_Processo[i][2] = s.getAccount_name();
            spaceProbes_Processo[i][3] = s.getData();
            spaceProbes_Processo[i][4] = s.getId_ativity();
            spaceProbes_Processo[i][5] = s.getId_process();

        }
        return spaceProbes_Processo;
    }
}
