package ohtu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class Ostoskori {
    
    HashMap<Tuote,Ostos> ostoskori = new HashMap<>();
 
    public int tavaroitaKorissa() {
        // kertoo korissa olevien tavaroiden lukumäärän
        // eli jos koriin lisätty 2 kpl tuotetta "maito", 
        //   tulee metodin palauttaa 2 
        // jos korissa on 1 kpl tuotetta "maito" ja 1 kpl tuotetta "juusto", 
        //   tulee metodin palauttaa 2   
        int yhteensa = 0;
        for (Ostos ostos : ostoskori.values()) {
            yhteensa += ostos.lukumaara();
        }
        return yhteensa;
    }
 
    public int hinta() {
        int hinta = 0;
        for (Ostos ostos : ostoskori.values()) {
            hinta += ostos.hinta();
        }
        return hinta;
    }
 
    public void lisaaTuote(Tuote lisattava) {
        if (ostoskori.containsKey(lisattava)) {
            Ostos paivitettava = ostoskori.get(lisattava);
            paivitettava.muutaLukumaaraa(1);
        } else {
            ostoskori.put(lisattava, new Ostos(lisattava));
        }
        
    }
 
    public void poista(Tuote poistettava) {
        // poistaa tuotteen
    }
 
    public List<Ostos> ostokset() {
        List<Ostos> ostokset = new ArrayList<Ostos>(ostoskori.values());
        return ostokset;
    }
 
    public void tyhjenna() {
        // tyhjentää korin
    }
}
