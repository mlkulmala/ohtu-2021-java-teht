package ohtu;

import java.util.ArrayList;
import java.util.List;

public class Ostoskori {
    
    ArrayList<Ostos> ostokset = new ArrayList<>();
 
    public int tavaroitaKorissa() {
        // kertoo korissa olevien tavaroiden lukumäärän
        // eli jos koriin lisätty 2 kpl tuotetta "maito", 
        //   tulee metodin palauttaa 2 
        // jos korissa on 1 kpl tuotetta "maito" ja 1 kpl tuotetta "juusto", 
        //   tulee metodin palauttaa 2   
        int yhteensa = 0;
        for (Ostos ostos : ostokset) {
            yhteensa += ostos.lukumaara();
        }
        return yhteensa;
    }
 
    public int hinta() {
        int hinta = 0;
        for (Ostos ostos : ostokset) {
            hinta += ostos.hinta();
        }
        return hinta;
    }
 
    public void lisaaTuote(Tuote lisattava) {
        // lisää tuotteen
    }
 
    public void poista(Tuote poistettava) {
        // poistaa tuotteen
    }
 
    public List<Ostos> ostokset() {
        // palauttaa listan jossa on korissa olevat ostokset
 
        return null;
    }
 
    public void tyhjenna() {
        // tyhjentää korin
    }
}
