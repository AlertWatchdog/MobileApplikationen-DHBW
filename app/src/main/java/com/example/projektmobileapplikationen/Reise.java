package com.example.projektmobileapplikationen;

public class Reise {

    private String startDatum;
    private String endDatum;
    private String startZeit;
    private String endZeit;
    private String bezeichnung;
    private String id;
    private String auszahlung;

    public Reise(String id, String bezeichnung, String startZeit, String endZeit, String startDatum, String endDatum, String auszahlung){
        this.startDatum = startDatum;
        this.endDatum = endDatum;
        this.startZeit = startZeit;
        this.endZeit = endZeit;
        this.bezeichnung = bezeichnung;
        this.id = id;
        this.auszahlung = auszahlung;
    }

    public Reise(String bezeichnung, String startZeit, String endZeit, String startDatum, String endDatum, String auszahlung){
        this.startDatum = startDatum;
        this.endDatum = endDatum;
        this.startZeit = startZeit;
        this.endZeit = endZeit;
        this.bezeichnung = bezeichnung;
        this.auszahlung = auszahlung;
    }

    public String getStartDatum() {
        return startDatum;
    }

    public void setStartDatum(String startDatum) {
        this.startDatum = startDatum;
    }

    public String getEndDatum() {
        return endDatum;
    }

    public void setEndDatum(String endDatum) {
        this.endDatum = endDatum;
    }

    public String getStartZeit() {
        return startZeit;
    }

    public void setStartZeit(String startZeit) {
        this.startZeit = startZeit;
    }

    public String getEndZeit() {
        return endZeit;
    }

    public void setEndZeit(String endZeit) {
        this.endZeit = endZeit;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuszahlung() {
        return auszahlung;
    }

    public void setAuszahlung(String auszahlung) {
        this.auszahlung = auszahlung;
    }



}
