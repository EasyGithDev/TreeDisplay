package arbre;

import java.util.Comparator;

public class NoeudPosition<T extends Comparable<T>> implements Comparator<NoeudPosition<T>> {

    protected NoeudPosition<T> g, d;
    protected T v;

    public NoeudPosition(NoeudPosition<T> g, T v, NoeudPosition<T> d) {
        this.g = g;
        this.v = v;
        this.d = d;
    }

    public NoeudPosition(T v) {
        this(null, v, null);
    }

    /*
     * Coordonnées du noeud
     */
    private int x, y;

    /*
     * Accès à la position dans le plan
     */
    public int X() {
        return x;
    }

    public int Y() {
        return y;
    }

    public void fixerPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public T valeur() {
        return v;
    }

    public NoeudPosition<T> filsGauche() {
        return g;
    }

    public NoeudPosition<T> filsDroit() {
        return d;
    }

    public void filsGauche(NoeudPosition<T> g) {
        this.g = g;
    }

    public void filsDroit(NoeudPosition<T> d) {
        this.d = d;
    }

    public boolean existeFilsGauche() {
        return (g != null);
    }

    public boolean existeFilsDroit() {
        return (d != null);
    }

    public boolean estFeuille() {
        return (g == null && d == null);
    }

    public int hauteur() {
        int gauche = (existeFilsGauche()) ? this.filsGauche().hauteur() : 0;
        int droit = (existeFilsDroit()) ? this.filsDroit().hauteur() : 0;
        return ((gauche >= droit) ? gauche : droit) + 1;
    }

    public int taille() {
        int gauche = (existeFilsGauche()) ? this.filsGauche().taille() : 0;
        int droit = (existeFilsDroit()) ? this.filsDroit().taille() : 0;
        return gauche + droit + 1;
    }

    // Overriding the compareTo method
    public int compare(NoeudPosition<T> a, NoeudPosition<T> b) {
        return a.valeur().compareTo(b.valeur());
    }

    public int compare(NoeudPosition<T> a, T b) {
        return a.valeur().compareTo(b);
    }
}