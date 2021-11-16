package ohtu.verkkokauppa;

public class Kauppa {

    private Tila tila;
    private Rahalaitos laitos;
    private Ostoskori ostoskori;
    private Generaattori generaattori;
    private String kaupanTili;

    public Kauppa(Rahalaitos laitos, Tila tila, Generaattori generaattori) {
        this.tila = tila;
        this.laitos = laitos;
        this.generaattori = generaattori;
        this.kaupanTili = "33333-44455";
    }

    public void aloitaAsiointi() {
        ostoskori = new Ostoskori();
    }

    public void poistaKorista(int id) {
        Tuote t = tila.haeTuote(id); 
        tila.palautaVarastoon(t);
    }

    public void lisaaKoriin(int id) {
        if (tila.saldo(id)>0) {
            Tuote t = tila.haeTuote(id);             
            ostoskori.lisaa(t);
            tila.otaVarastosta(t);
        }
    }

    public boolean tilimaksu(String nimi, String tiliNumero) {
        int viite = generaattori.uusi();
        int summa = ostoskori.hinta();
        
        return laitos.tilisiirto(nimi, viite, tiliNumero, kaupanTili, summa);
    }

}
