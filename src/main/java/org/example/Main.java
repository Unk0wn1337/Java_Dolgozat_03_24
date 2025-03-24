package org.example;

import org.example.modell.Szak;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Main {
    List<Szak> szakok;
    public static void main(String[] args) throws IOException {
            new Main().Szakok();
        }

    private void Szakok() throws IOException {
        szakok = new ArrayList<>();
        szakKiirasa();
        szakBeolvasas();
        getTargyakNevSzerint();
        statisztika();
        getTargyakKreditSzerint();
        System.out.println("Különböző tárgy: "+kulonbozoTargyak());
        System.out.println("");
        System.out.println("Vizsga tárgyak:");
        System.out.println(VizsgaTargyak());
        System.out.println("");
        System.out.println("összes kredit: "+osszesKredit());
        System.out.println("");
        System.out.println("min kredit:"+Collections.min(kredit()));
        System.out.println("max kredit:"+Collections.max(kredit()));
        System.out.println("");
        System.out.println("Tárgyakat tanítók:");
        mapKiiras(TargyakatTanitok());
    }

    private void statisztika() {
        String fn = "statisztika.txt";
        try{

            Files.write(Path.of(fn, String.valueOf(getTargyakKreditSzerint())));
       } catch (IOException e){
          System.err.println(e);
      }
    }

    private void mapKiiras(Map<String, String> stringStringMap) {
        for (Map.Entry<String, String> entry : stringStringMap.entrySet()) {
            String kulcs = entry.getKey();
            String ertek = entry.getValue();
            System.out.printf("%s: %s\n",kulcs,ertek);
        }

    }

    private Map<String, String>TargyakatTanitok() {
        Map<String,String> mm = new HashMap<>();
        szakok.forEach(szak -> {
            String kulcs = szak.getNev();
            if(mm.containsKey(kulcs)){
               mm.put(kulcs, String.valueOf(szak.getTanarok()));
            } else {
                mm.put(kulcs, String.valueOf(szak.getTanarok()));
            }
        });
        return mm;
    }


    private List<Integer> kredit() {
        List<Integer> kreditek = new ArrayList<>();
        for (Szak szak : szakok) {
            kreditek.add(szak.getKredit());
        }
        return kreditek;
    }


    private double osszesKredit() {
        double osszeg = 0;
        for (Szak szak : szakok) {
            osszeg += szak.getKredit();
        }
        return osszeg;
    }

    private HashSet<String> VizsgaTargyak() {
            HashSet<String> vizsgaTargyak = new HashSet<>();
            szakok.forEach(szak -> {
                if(!(szak.getVizsga() == "nem")){
                    vizsgaTargyak.add(szak.getNev());
                }
            });
            return vizsgaTargyak;
    }

    private int kulonbozoTargyak() {
        Set<String> kulonbozoTargyak = new HashSet<>();
        for (Szak szak : szakok) {
            kulonbozoTargyak.add(szak.getNev());
        }
        return kulonbozoTargyak.size();
    }

    private List<Szak> getTargyakKreditSzerint() {
        szakok.sort(Szak.kreditSzerintRendez());
        return szakok;
    }

    private List<Szak> getTargyakNevSzerint() {
        szakok.sort(Szak.rendezTargyNev());
        return szakok;
    }

    private void szakKiirasa() {
        try(ObjectOutputStream objKi = new ObjectOutputStream(new FileOutputStream("targyak.dat"))){
            objKi.writeObject(szakok);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void szakBeolvasas() throws IOException {

        List<String> sorok = Files.readAllLines(Path.of("tantargyak.txt"));
        sorok.forEach(s -> {
            if(!(s.contains("NÉV"))){
                 Szak sz = new Szak(s);
                 szakok.add(sz);
            }
        });
    }
}
