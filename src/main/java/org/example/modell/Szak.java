package org.example.modell;

import java.io.Serializable;
import java.text.Collator;
import java.util.*;

/* NÉV;KREDIT;TANÁR1;TANÁR2;FÉLÉV;CSAK_VIZSGA*/
public class Szak implements Serializable{
    private String nev;
    private int kredit;
    private List<String> tanarok;
    private String tanarEgy;
    private String tanarKetto;
    private int felev;
    private String vizsga;
    private transient UUID id;

    public Szak(String nev, int kredit, List<String> tanarok, String tanarEgy, String tanarKetto, int felev, String vizsga) {
        this.nev = nev;
        this.kredit = kredit;
        this.tanarok = tanarok;
        this.tanarEgy = tanarEgy;
        this.tanarKetto = tanarKetto;
        this.felev = felev;
        this.vizsga = vizsga;
        this.id = UUID.randomUUID();
    }

    public Szak(String nev, int kredit,String tanarEgy, String tanarKetto, int felev, String vizsga) {
        this(nev,kredit,new ArrayList<>(),tanarEgy,tanarKetto,felev,vizsga);
        this.tanarok = new ArrayList<>();
        tanarok.add(tanarEgy);
        tanarok.add(tanarKetto);
        this.id = UUID.randomUUID();
    }

    public Szak(String sor){
        String[] adatok = sor.split(";");
        String nev = adatok[0];
        int kredit = Integer.parseInt(adatok[1]);
        String tanarEgy = adatok[2];
        String tanarKetto = adatok[3];
        int felev = Integer.parseInt(adatok[4]);
        String vizsga = adatok[5];
        this.nev = nev;
        this.kredit = kredit;
        this.tanarok = new ArrayList<>();
        tanarok.add(tanarEgy);
        tanarok.add(tanarKetto);
        for (String s : tanarok) {
            if(s.contains("-")){
                s.split("-");
            }
        }

        this.tanarEgy = tanarEgy;
        this.tanarKetto = tanarKetto;
        this.felev = felev;
        this.vizsga = vizsga;
        this.id = UUID.randomUUID();
    }

    public String getNev() {
        return nev;
    }

    public int getKredit() {
        return kredit;
    }

    public List<String> getTanarok() {
        return new ArrayList<>(tanarok);
    }

    public String getTanarEgy() {
        return tanarEgy;
    }

    public String getTanarKetto() {
        return tanarKetto;
    }

    public int getFelev() {
        return felev;
    }

    public String getVizsga() {
        return vizsga;
    }

    public static TargyNevComporator rendezTargyNev(){
        return new TargyNevComporator();
    }

    public static KreditSzerintRendezComporator kreditSzerintRendez(){
        return new KreditSzerintRendezComporator();
    }

    public void setFelev(int felev) {
        this.felev = felev;
    }

    public void setVizsga(String vizsga) {
        this.vizsga = vizsga;
    }

    public void setKredit(int kredit) {
        this.kredit = kredit;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Szak szak = (Szak) o;
        return kredit == szak.kredit && felev == szak.felev && Objects.equals(nev, szak.nev) && Objects.equals(tanarok, szak.tanarok) && Objects.equals(vizsga, szak.vizsga) && Objects.equals(id, szak.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nev, kredit, tanarok, felev, vizsga, id);
    }



    private static class TargyNevComporator implements Comparator<Szak>{
        @Override
        public int compare(Szak o1, Szak o2) {
            Collator coll = Collator.getInstance();
            return coll.compare(o1.nev,o2.nev);
        }
    }

    private static class KreditSzerintRendezComporator implements Comparator<Szak>{
        @Override
        public int compare(Szak o1, Szak o2) {
            return  o1.kredit- o2.kredit;
        }
    }

}
