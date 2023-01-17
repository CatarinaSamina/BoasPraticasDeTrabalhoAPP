package com.example.boaspraticasdetrabalho1;

public class Spacecraft {
    private String id, data, subject, action_name, artefact, receiver, resource;
    private String username, username_name, aniversario, mail, cc, empresa, morada;

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

    public String getUsername(){return username;}

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername_name(){return username_name;}

    public void setUsername_name(String username_name){
        this.username_name = username_name;
    }

    public String getAniversario(){return aniversario;}

    public void setAniversario(String aniversario){
        this.aniversario = aniversario;
    }

    public String getMail(){return mail;}

    public void setMail(String mail){
        this.mail = mail;
    }

    public String getCC(){return cc;}

    public void setCC(String cc){ this.cc = cc; }

    public String getEmpresa(){return empresa;}

    public void setEmpresa(String empresa){
        this.empresa = empresa;
    }

    public String getMorada(){return morada;}

    public void setMorada(String morada){
        this.morada = morada;
    }
}
