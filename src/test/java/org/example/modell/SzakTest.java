package org.example.modell;

import org.example.modell.Szak;
import org.example.modell.Tantargy;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.List;

// vége
class SzakTest {
    Szak szak;
    @BeforeEach
    void ini(){
        szak = new Szak("egy szak neve");
    }

    @Test
    void testSzakTargyNevek(){
        for (Tantargy tantargy : szak.targyak) {
            Assertions.assertTrue(tantargy.getNev().length() > 3);
        }
    }

    @Test
    void testGetTargyak(){
        List<Tantargy> targyak = szak.getTargyak();
        int eredeti = targyak.size();
        targyak.add(new Tantargy());
        Assertions.assertTrue(eredeti == szak.getTargyak().size());
    }

    @Test
    void szerializalhato(){
        Assertions.assertTrue(Serializable.class.isAssignableFrom(Szak.class),"Nem szerializhalto");
    }

    @Test
    void egyforma(){
        Szak sz1 = new Szak("matematika",5,"Szekeres Barnabás","Roffer Rozália",3,"nem");
        Szak sz2 = new Szak("matematika",5,"Szekeres Barnabás","Roffer Rozália",3,"nem");
        Assertions.assertEquals(sz1.hashCode(),sz2.hashCode());
        Assertions.assertEquals(sz1,sz2);
    }

}