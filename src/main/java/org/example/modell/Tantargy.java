package org.example.modell;

import java.util.ArrayList;
import java.util.List;

public class Tantargy {
    List<Szak> targyak;

    public Tantargy(String nev) {
        if(nev.length() >= 3){
            throw new RuntimeException("Nem lehet kisebb mint 3");
        }
        targyak = new ArrayList<>();
       // matematika;5;Szekeres Barnabás;Roffer Rozália;3;nem

    }
    public Tantargy() {
        String targy = "Matematika";
        targyak = new ArrayList<>();
        targyak.add(new Szak("matematika",5,"Szekeres Barnabás","Roffer Rozália",3,"nem"));
        // matematika;5;Szekeres Barnabás;Roffer Rozália;3;nem
        // String nev, int kredit,String tanarEgy, String tanarKetto, int felev, String vizsga
    }

    public List<Szak> getTargyak() {
        return new ArrayList<>(targyak);
    }



}
