
package ohtu.verkkokauppa;

/**
 *
 * @author mlkul
 */
public interface Rahalaitos {

    boolean tilisiirto(String nimi, int viitenumero, String tililta, String tilille, int summa);
    
}
