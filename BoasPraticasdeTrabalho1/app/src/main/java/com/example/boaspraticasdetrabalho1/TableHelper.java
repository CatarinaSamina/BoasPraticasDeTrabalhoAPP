package com.example.boaspraticasdetrabalho1;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class TableHelper {
    Context c;
    private String[] spaceProbeHeaders={"Sujeito", "Ação", "Artefacto"};
    private String[][] spaceProbes;
    public TableHelper(Context c){
        this.c = c;
    }

    public String[]getSpaceProbeHeaders(){
        return spaceProbeHeaders;
    }

    public String[][] getSpaceProbes(String a){
        ArrayList<Spacecraft> spacecrafts = new DBAdapter(c).retrieveSpacecrafts(a);
        Spacecraft s;
        spaceProbes = new String[spacecrafts.size()][7];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes[i][0] = s.getSubject();
            spaceProbes[i][1] = s.getAction_name();
            spaceProbes[i][2] = s.getReceiver();
            spaceProbes[i][3] = s.getArtefact();
            spaceProbes[i][4] = s.getData();
            spaceProbes[i][5] = s.getResource();
            spaceProbes[i][6] = s.getId();


        }
        return spaceProbes;
    }

}
