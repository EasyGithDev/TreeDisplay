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

    public void infix(Noeud<T> courant, Queue<Object> queue) {
        if (courant == null)
            return;

        infix(courant.filsGauche(), queue);
        System.out.print(courant.valeur() + " ");
        queue.add(courant.valeur());
        infix(courant.filsDroit(), queue);
    }

    public void prefix(Noeud<T> courant, Queue<Object> queue) {
        if (courant == null)
            return;
        queue.add(courant.valeur());
        System.out.print(courant.valeur() + " ");
        prefix(courant.filsGauche(), queue);
        prefix(courant.filsDroit(), queue);
    }

    public void postfix(Noeud<T> courant, Queue<Object> queue) {
        if (courant == null)
            return;

        postfix(courant.filsGauche(), queue);
        postfix(courant.filsDroit(), queue);
        queue.add(courant.valeur());

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

    /*
     * Calcul de la position d'un noeud dans le plan
     */
    public int calculerPositions(Noeud<T> p, int xCourant, int yCourant) {
        /* on augmente xCourant afin d'accueillir toute la largeur du fils gauche */
        if (p.existeFilsGauche()) {
            xCourant = calculerPositions(p.filsGauche(), xCourant, yCourant + 1);
        }

        /* la valeur de xCourant est maintenant l'abscisse de notre noeud */
        p.fixerPosition(xCourant, yCourant);
        xCourant = xCourant + 1;

        /* on deplace xCourant afin d'accueillir toute la largeur du fils droit */
        if (p.existeFilsDroit())
            xCourant = calculerPositions(p.filsDroit(), xCourant, yCourant + 1);

        /* la valeur de xCourant comprend maintenant la largeur de notre arbre */
        return xCourant;
    }

}