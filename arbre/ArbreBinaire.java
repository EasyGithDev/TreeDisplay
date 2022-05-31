package arbre;

import java.util.LinkedList;
import java.util.Queue;

public class ArbreBinaire<T extends Comparable<T>> {

    protected Noeud<T> racine;

    public ArbreBinaire() {
        this(null);
    }

    public ArbreBinaire(Noeud<T> racine) {
        this.racine = racine;
    }

    public boolean estVide() {
        return (racine == null);
    }

    public Noeud<T> racine() {
        return this.racine;
    }

    public void racine(Noeud<T> racine) {
        this.racine = racine;
    }

    public void infix(Noeud<T> courant) {
        if (courant == null)
            return;

        infix(courant.filsGauche());
        System.out.print(courant.valeur() + " ");
        infix(courant.filsDroit());
    }

    public void prefix(Noeud<T> courant) {
        if (courant == null)
            return;

        System.out.print(courant.valeur() + " ");
        prefix(courant.filsGauche());
        prefix(courant.filsDroit());
    }

    public void postfix(Noeud<T> courant) {
        if (courant == null)
            return;

        postfix(courant.filsGauche());
        postfix(courant.filsDroit());
        System.out.print(courant.valeur() + " ");
    }

    public void largeur(Noeud<T> a) {

        Queue<Noeud<T>> queue = new LinkedList<Noeud<T>>();

        queue.add(a);
        while (queue.size() != 0) {
            a = queue.remove();

            System.out.print(String.valueOf(a.valeur()) + ' ');

            if (a.filsGauche() != null) {
                queue.add(a.filsGauche());
            }
            if (a.filsDroit() != null) {
                queue.add(a.filsDroit());
            }
        }
    }

    public int hauteur() {
        return this.racine.hauteur();
    }

    public int taille() {
        return this.racine.taille();
    }

}