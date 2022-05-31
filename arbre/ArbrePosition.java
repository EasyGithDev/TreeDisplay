package arbre;

import java.util.Queue;

public class ArbrePosition<T extends Comparable<T>> {

    protected NoeudPosition<T> racine;

    public ArbrePosition() {
        this.racine = null;
    }

    public ArbrePosition(NoeudPosition<T> racine) {
        this.racine = racine;
    }

    public ArbrePosition(Noeud<T> racine) {
        this.racine = ArbreConvertion.convertion(racine);
    }

    public ArbrePosition(NoeudAvl<T> racine) {
        this.racine = ArbreConvertion.convertion(racine);
    }

    public NoeudPosition<T> racine() {
        return this.racine;
    }

    /*
     * Calcul de la position d'un noeud dans le plan
     */
    public int calculerPositions(NoeudPosition<T> p, int xCourant, int yCourant) {
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

    public void infix(NoeudPosition<T> courant, Queue<Object> queue) {
        if (courant == null)
            return;

        infix(courant.filsGauche(), queue);
        System.out.print(courant.valeur() + " ");
        queue.add(courant.valeur());
        infix(courant.filsDroit(), queue);
    }

    public void prefix(NoeudPosition<T> courant, Queue<Object> queue) {
        if (courant == null)
            return;
        queue.add(courant.valeur());
        System.out.print(courant.valeur() + " ");
        prefix(courant.filsGauche(), queue);
        prefix(courant.filsDroit(), queue);
    }

    public void postfix(NoeudPosition<T> courant, Queue<Object> queue) {
        if (courant == null)
            return;

        postfix(courant.filsGauche(), queue);
        postfix(courant.filsDroit(), queue);
        queue.add(courant.valeur());

        System.out.print(courant.valeur() + " ");
    }

    public int hauteur() {
        return this.racine.hauteur();
    }

    public int taille() {
        return this.racine.taille();
    }
}
