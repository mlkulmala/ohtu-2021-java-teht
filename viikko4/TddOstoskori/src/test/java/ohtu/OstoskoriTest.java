package ohtu;

import java.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OstoskoriTest {

    Ostoskori kori;

    @Before
    public void setUp() {
        kori = new Ostoskori();
    }

    // step 1
    @Test
    public void ostoskorinHintaJaTavaroidenMaaraAlussa() { 
        assertEquals(0, kori.hinta());
        assertEquals(0, kori.tavaroitaKorissa());
    }

    @Test
    public void yhdenTuotteenLisaamisenJalkeenKorissaYksiTuote() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        
        assertEquals(1, kori.tavaroitaKorissa());
    }
    
    @Test
    public void yhdenTuotteenLisaamisenJalkeenOstoskorinHintaOnTuotteenHinta() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        
        assertEquals(3, kori.hinta());
    }
    
    @Test
    public void kahdenTuotteenLisaamisenJalkeenKorissaKaksiTuotetta() {
        Tuote maito = new Tuote("maito", 3);
        Tuote leipa = new Tuote("leipa", 5);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(leipa);
        
        assertEquals(2, kori.tavaroitaKorissa());
    }
    
    @Test
    public void kahdenTuotteenJalkeenOstoskorinHintaOnTuotteidenYhteenlaskettuHinta() {
        Tuote maito = new Tuote("maito", 3);
        Tuote leipa = new Tuote("leipa", 5);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(leipa);
        
        assertEquals(8, kori.hinta());
    }
    
    @Test
    public void kahdenSamanTuotteenLisaamisenJalkeenKorissaKaksiTuotetta() {
        Tuote maito = new Tuote("maito", 3);
        kori.lisaaTuote(maito);
        kori.lisaaTuote(maito);
        
        assertEquals(2, kori.tavaroitaKorissa());
    }
}
