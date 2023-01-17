package com.example.boaspraticasdetrabalho1;

import android.content.Context;

import java.util.ArrayList;

public class TableHelper_Atividades {
    Context c;
    private String[] spaceProbeHeaders={"Nome", "Descrição", "Criador"};
    private String[][] spaceProbes;
    public TableHelper_Atividades(Context c){
        this.c = c;
    }

    public String[]getSpaceProbeHeaders(){
        return spaceProbeHeaders;
    }

    public String[][] getSpaceProbes(){
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new DBAdapter_Atividades(c).retrieveSpacecrafts();
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

    private String[] spaceProbeHeaders_produto={"Nome", "Descrição"};
    private String[][] spaceProbes_produto;

    public String[]getSpaceProbeHeaders_produto(){
        return spaceProbeHeaders_produto;
    }

    public String[][] getSpaceProbes_produto(String a){
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new DBAdapter_Atividades(c).retrieveSpacecrafts_produtos(a);
        Spacecraft_Agrupamentos s;
        spaceProbes = new String[spacecrafts.size()][6];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes[i][0] = s.getName_group();
            spaceProbes[i][1] = s.getDescription();
            spaceProbes[i][2] = s.getCriador();
            spaceProbes[i][3] = s.getData();
            spaceProbes[i][4] = s.getId();
            spaceProbes[i][5] = s.getId_atividade();
        }
        return spaceProbes;
    }

    private String[] spaceProbeHeaders_funcao={"Nome", "Descrição"};
    private String[][] spaceProbes_funcao;

    public String[]getSpaceProbeHeaders_funcao(){
        return spaceProbeHeaders_funcao;
    }

    public String[][] getSpaceProbes_funcao(String a){
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new DBAdapter_Atividades(c).retrieveSpacecrafts_funcoes(a);
        Spacecraft_Agrupamentos s;
        spaceProbes = new String[spacecrafts.size()][6];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes[i][0] = s.getName_group();
            spaceProbes[i][1] = s.getDescription();
            spaceProbes[i][2] = s.getCriador();
            spaceProbes[i][3] = s.getData();
            spaceProbes[i][4] = s.getId();
            spaceProbes[i][5] = s.getId_atividade();
        }
        return spaceProbes;
    }

    private String[] spaceProbeHeaders_atividadeproduto={"Nome", "Descrição"};
    private String[][] spaceProbes_atividadeproduto;

    public String[]getSpaceProbeHeaders_atividadeproduto(){
        return spaceProbeHeaders_atividadeproduto;
    }

    public String[][] getSpaceProbes_atividadeproduto(String a){
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new DBAdapter_Atividades(c).retrieveSpacecrafts_atividadesprodutos(a);
        Spacecraft_Agrupamentos s;
        spaceProbes = new String[spacecrafts.size()][6];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes[i][0] = s.getName_group();
            spaceProbes[i][1] = s.getDescription();
            spaceProbes[i][2] = s.getCriador();
            spaceProbes[i][3] = s.getData();
            spaceProbes[i][4] = s.getId();
            spaceProbes[i][5] = s.getId_atividade();
        }
        return spaceProbes;
    }

    private String[] spaceProbeHeaders_atividadefuncao={"Nome", "Descrição"};
    private String[][] spaceProbes_atividadefuncao;

    public String[]getSpaceProbeHeaders_atividadefuncao(){
        return spaceProbeHeaders_atividadefuncao;
    }

    public String[][] getSpaceProbes_atividadefuncao(String a){
        ArrayList<Spacecraft_Agrupamentos> spacecrafts = new DBAdapter_Atividades(c).retrieveSpacecrafts_atividadesfuncoes(a);
        Spacecraft_Agrupamentos s;
        spaceProbes = new String[spacecrafts.size()][6];
        for (int i = 0; i < spacecrafts.size(); i++){
            s = spacecrafts.get(i);
            spaceProbes[i][0] = s.getName_group();
            spaceProbes[i][1] = s.getDescription();
            spaceProbes[i][2] = s.getCriador();
            spaceProbes[i][3] = s.getData();
            spaceProbes[i][4] = s.getId();
            spaceProbes[i][5] = s.getId_atividade();
        }
        return spaceProbes;
    }
}
