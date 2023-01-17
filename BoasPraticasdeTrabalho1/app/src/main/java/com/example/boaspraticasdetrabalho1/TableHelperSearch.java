package com.example.boaspraticasdetrabalho1;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import java.util.ArrayList;

public class TableHelperSearch {
    Context c;
    private String[][] spaceProbes;

    public TableHelperSearch(Context c){
        this.c = c;
    }

    public String[][] getSpaceProbes_Search(String a, String b, String d){
        ArrayList<Spacecraft> spacecrafts = new DBAdapterSearch(c).retrieveSpacecrafts_Search(a, b, d);
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
