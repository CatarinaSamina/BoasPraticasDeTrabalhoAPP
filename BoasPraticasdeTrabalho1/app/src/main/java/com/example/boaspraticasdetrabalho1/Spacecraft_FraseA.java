package com.example.boaspraticasdetrabalho1;

public class Spacecraft_FraseA {

    private String id, data, subject, action_name, artefact, receiver, resource;
    private String id_agrupamento, nome_agrupamento, descricao_agrupamento, data_agrupamento, criador_agrupamento;

    public String getId(){return id;}

    public void setId(String id){
        this.id = id;
    }

    public String getData(){
        return data;
    }

    public void setData(String data){
        this.data = data;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubject(String subject){
        this.subject = subject;
    }

    public String getAction_name(){
        return action_name;
    }

    public void setAction_name(String action_name){
        this.action_name = action_name;
    }

    public String getArtefact(){
        return artefact;
    }

    public void setArtefact(String artefact){
        this.artefact = artefact;
    }

    public String getReceiver(){ return receiver; }

    public void setReceiver(String receiver){this.receiver = receiver;}

    public String getResource(){ return resource;}

    public void setResource(String resource){this.resource = resource; }

    public String getId_agrupamento(){ return id_agrupamento;}

    public void setId_agrupamento(String id_agrupamento){this.id_agrupamento = id_agrupamento; }

    public String getNome_agrupamento(){ return nome_agrupamento;}

    public void setNome_agrupamento(String nome_agrupamento){this.nome_agrupamento = nome_agrupamento; }

    public String getDescricao_agrupamento(){ return descricao_agrupamento;}

    public void setDescricao_agrupamento(String descricao_agrupamento){this.descricao_agrupamento = descricao_agrupamento; }

    public String getData_agrupamento(){ return data_agrupamento;}

    public void setData_agrupamento(String data_agrupamento){this.data_agrupamento = data_agrupamento; }

    public String getCriador_agrupamento(){ return criador_agrupamento;}

    public void setCriador_agrupamento(String criador_agrupamento){this.criador_agrupamento = criador_agrupamento; }

}
