package arbre;

import java.util.Comparator;

public class Noeud<T extends Comparable<T>> implements Comparator<Noeud<T>> {

    protected Noeud<T> g, d;
    protected T v;

    /*
     * Coordonnées du noeud
     */
    private int x, y;

    public Noeud(Noeud<T> g, T v, Noeud<T> d) {
        this.g = g;
        this.v = v;
        this.d = d;
    }

    public Noeud(T v) {
        this(null, v, null);
    }

    public T valeur() {
        return v;
    }

    public void valeur(T v) {
        this.v = v;
    }

    public Noeud<T> filsGauche() {
        return g;
    }

    public Noeud<T> filsDroit() {
        return d;
    }

    public void filsGauche(Noeud<T> g) {
        this.g = g;
    }

    public void filsDroit(Noeud<T> d) {
        this.d = d;
    }

    public boolean estFeuille() {
        return (g == null && d == null);
    }

    public boolean existeFilsGauche() {
        return (g != null);
    }

    public boolean existeFilsDroit() {
        return (d != null);
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

    public Noeud<T> rotationG() {
        Noeud<T> b = this.d;
        this.d = b.g;
        b.g = this;
        return b;
    }

    public Noeud<T> rotationD() {
        Noeud<T> b = this.g;
        this.g = b.d;
        b.d = this;
        return b;
    }

    // Overriding the compareTo method
    public int compare(Noeud<T> a, Noeud<T> b) {
        return a.valeur().compareTo(b.valeur());
    }

    public int compare(Noeud<T> a, T b) {
        return a.valeur().compareTo(b);
    }

}