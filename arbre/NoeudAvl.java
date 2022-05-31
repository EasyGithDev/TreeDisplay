package arbre;

import java.util.Comparator;

public class NoeudAvl<T extends Comparable<T>> implements Comparator<NoeudAvl<T>> {

    protected NoeudAvl<T> g, d;
    protected T v;

    public NoeudAvl(NoeudAvl<T> g, T v, NoeudAvl<T> d) {
        this.g = g;
        this.v = v;
        this.d = d;
    }

    public NoeudAvl(T v) {
        this(null, v, null);
    }

    public T valeur() {
        return v;
    }

    public NoeudAvl<T> filsGauche() {
        return g;
    }

    public NoeudAvl<T> filsDroit() {
        return d;
    }

    public void filsGauche(NoeudAvl<T> g) {
        this.g = g;
    }

    public void filsDroit(NoeudAvl<T> d) {
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

    public NoeudAvl<T> rotationG() {
        NoeudAvl<T> b = this.d;
        this.d = b.g;
        b.g = this;
        return b;
    }

    public NoeudAvl<T> rotationD() {
        NoeudAvl<T> b = this.g;
        this.g = b.d;
        b.d = this;
        return b;
    }

    // Overriding the compareTo method
    public int compare(NoeudAvl<T> a, NoeudAvl<T> b) {
        return a.valeur().compareTo(b.valeur());
    }

    public int compare(NoeudAvl<T> a, T b) {
        return a.valeur().compareTo(b);
    }
}