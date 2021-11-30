package ohtu.verkkokauppa;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    
    private Pankki pankki;
    private Viitegeneraattori viite;
    private Varasto varasto;
    private Kauppa k;

    @Before
    public void serUp() {
        // luodaan ensin mock-oliot
        pankki = mock(Pankki.class);

        viite = mock(Viitegeneraattori.class);
        // m‰‰ritell‰‰n ett‰ viitegeneraattori palauttaa viitten 42
        when(viite.uusi()).thenReturn(42);
        
        varasto = mock(Varasto.class);
        
        // sitten testattava kauppa 
        k = new Kauppa(varasto, pankki, viite);
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei v‰litetty kutsussa k‰ytetyist‰ parametreista
    }
    
    @Test
    public void tilisiirtoTapahtuuOikeillaParametreilla() {
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on 
        // kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"), eq(5));   

    }
    
    @Test
    public void tilisiirtoTapahtuuOikeallaNimellaTilillaJaSummalla() {
        when(varasto.saldo(1)).thenReturn(3); 
        when(varasto.saldo(2)).thenReturn(3); 
        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5
        // m‰‰ritell‰‰n ett‰ tuote numero 2 on leip‰ jonka hinta on 7
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 7));
        
        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli leip‰
        k.tilimaksu("marja", "54321");
        
        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on 
        // kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("marja"), eq(42), eq("54321"), eq("33333-44455"), eq(12));
    }
          
    @Test
    public void kahdenOstoksenTilisiirtoTapahtuuOikeallaNimellaTilillaJaSummalla() {
        when(varasto.saldo(1)).thenReturn(3); 
        // m‰‰ritell‰‰n ett‰ tuote numero 1 on juusto jonka hinta on 10
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "juusto", 10));

 
        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli juusto
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli toinen juusto
        k.tilimaksu("kalle", "45454");
        
        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on 
        // kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("kalle"), eq(42), eq("45454"), eq("33333-44455"), eq(20));
    }
    
    @Test
    public void ostoksenTilisiirtoOikeinKunToinenTuoteOnLoppu() {
        when(varasto.saldo(1)).thenReturn(3); 
        when(varasto.saldo(2)).thenReturn(0); 
        // m‰‰ritell‰‰n ett‰ tuote numero 1 on maito jonka hinta on 5
        // m‰‰ritell‰‰n ett‰ tuote numero 2 on leip‰ jonka hinta on 7
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 7));

        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli juusto
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 1 eli toinen juusto
        k.tilimaksu("armi", "37373");
        
        // sitten suoritetaan varmistus, ett‰ pankin metodia tilisiirto on 
        // kutsuttu oikeilla parametreilla
        verify(pankki).tilisiirto(eq("armi"), eq(42), eq("37373"), eq("33333-44455"), eq(5));
    }
    
    @Test
    public void edellisetOstoksetNollautuvatKunAsiointiAloitetaan() {
        when(varasto.saldo(1)).thenReturn(5); 
        when(varasto.saldo(2)).thenReturn(5); 

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 7));
        
        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("lasse", "12121");
        
        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli leip‰
        k.tilimaksu("maija", "34343");
        verify(pankki).tilisiirto(eq("maija"), eq(42), eq("34343"), eq("33333-44455"), eq(7));
    }
    
    @Test
    public void jokaMaksutapahtumaSaaOmanViitenumeron() {
        when(viite.uusi())
            .thenReturn(1)
            .thenReturn(2)
            .thenReturn(3);
        
        when(varasto.saldo(1)).thenReturn(5); 
        when(varasto.saldo(2)).thenReturn(5); 

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 7));
        
        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("lasse", "12121");
        
        verify(pankki).tilisiirto(anyString(), eq(1), anyString(), anyString(), anyInt());
        
        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(2);     // ostetaan tuotetta numero 2 eli leip‰
        k.tilimaksu("maija", "34343");
        verify(pankki).tilisiirto(anyString(), eq(2), anyString(), anyString(), anyInt());
        
        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 2 eli leip‰
        k.tilimaksu("armi", "67676");
        verify(pankki).tilisiirto(anyString(), eq(3), anyString(), anyString(), anyInt());
    }
    
    @Test
    public void poistoOstoskorista() {
        when(varasto.saldo(1)).thenReturn(5); 
        when(varasto.saldo(2)).thenReturn(5); 

        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 7));
        
        // tehd‰‰n ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.lisaaKoriin(2);     // ostetaan viel‰ leip‰‰
        k.poistaKorista(1);
        k.tilimaksu("kalle", "99999");
        
        verify(pankki).tilisiirto(eq("kalle"), eq(42), eq("99999"), eq("33333-44455"), eq(7));
    }
    
    
}
 