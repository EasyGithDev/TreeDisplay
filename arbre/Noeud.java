package arbre;

import java.util.Comparator;

public class Noeud<T extends Comparable<T>> implements Comparator<Noeud<T>> {

    /**
     * Fils gauche et Fils droit
     */
    protected Noeud<T> g, d;

    /**
     * Valeur du noeud
     */
    protected T v;

    /*
     * Coordonnées du noeud dans un plan
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

    /**
     * 
     * @return Le filsG du noeud
     */
    public Noeud<T> filsGauche() {
        return g;
    }

    /**
     * 
     * @return Le filsD du noeud
     */
    public Noeud<T> filsDroit() {
        return d;
    }

    /**
     * 
     * @return Assigne un filsG au noeud
     */
    public void filsGauche(Noeud<T> g) {
        this.g = g;
    }

    /**
     * 
     * @return Assigne un filsD au noeud
     */
    public void filsDroit(Noeud<T> d) {
        this.d = d;
    }

    /**
     * 
     * @return vrai si le nojeud ne possède pas de filsG et filsD
     */
    public boolean estFeuille() {
        return (g == null && d == null);
    }

    /**
     * 
     * @return vrai si le noeud possède un filsG
     */
    public boolean existeFilsGauche() {
        return (g != null);
    }

    /**
     * 
     * @return vrai si le noeud possède un filsD
     */
    public boolean existeFilsDroit() {
        return (d != null);
    }

    /**
     * Calcul la hauteur d'un arbre
     * 
     * @return Le maximun des hauteurs des sous arbre gauche et droit + 1
     */
    public int hauteur() {
        int gauche = (existeFilsGauche()) ? this.filsGauche().hauteur() : 0;
        int droit = (existeFilsDroit()) ? this.filsDroit().hauteur() : 0;
        return ((gauche >= droit) ? gauche : droit) + 1;
    }

    /**
     * Calcul le nombre de noeud d'un arbre
     * 
     * @return le nombre de noeud du sous arbre droit + sous arbre gauche + 1
     */
    public int taille() {
        int gauche = (existeFilsGauche()) ? this.filsGauche().taille() : 0;
        int droit = (existeFilsDroit()) ? this.filsDroit().taille() : 0;
        return gauche + droit + 1;
    }

    /*
     * Accès à la position X dans le plan
     */
    public int X() {
        return x;
    }

    /*
     * Accès à la position Y dans le plan
     */
    public int Y() {
        return y;
    }

    public void fixerPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Rotation gauche du noeud
     * 
     * @return Le noeud devient le filsG de son filsD
     */
    public Noeud<T> rotationG() {
        Noeud<T> b = this.d;
        this.d = b.g;
        b.g = this;
        return b;
    }

    /**
     * Rotation droite du noeud
     * 
     * @return Le noeud devient le filsD de son filsG
     */
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