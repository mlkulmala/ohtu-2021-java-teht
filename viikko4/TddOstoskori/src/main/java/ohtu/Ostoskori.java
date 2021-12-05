package ohtu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Ostoskori {
    
    HashMap<Tuote,Ostos> ostoskori = new HashMap<>();
 
    public int tavaroitaKorissa() {
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
        if (ostoskori.containsKey(poistettava)) {
            Ostos paivitettava = ostoskori.get(poistettava);
            if (paivitettava.lukumaara() <= 1) {
                ostoskori.remove(poistettava);
            } else {
                paivitettava.muutaLukumaaraa(-1);
            }   
        } 
    }
 
    public List<Ostos> ostokset() {
        List<Ostos> ostokset = new ArrayList<Ostos>(ostoskori.values());
        return ostokset;
    }
 
    public void tyhjenna() {
        ostoskori = new HashMap<>();
    }
}
