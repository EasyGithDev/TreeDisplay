package arbre;

import java.util.LinkedList;
import java.util.Queue;

public class ArbreAvl<T extends Comparable<T>> {

    protected NoeudAvl<T> racine;

    public ArbreAvl() {
        this(null);
    }

    public ArbreAvl(NoeudAvl<T> racine) {
        this.racine = racine;
    }

    public NoeudAvl<T> racine() {
        return this.racine;
    }

    public void infix(NoeudAvl<T> courant) {
        if (courant == null)
            return;

        infix(courant.filsGauche());
        System.out.print(courant.valeur() + " ");
        infix(courant.filsDroit());
    }

    public void largeur(NoeudAvl<T> a) {

        Queue<NoeudAvl<T>> queue = new LinkedList<NoeudAvl<T>>();

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

    public NoeudAvl<T> equilibrer(NoeudAvl<T> a) {

        int hg = a.existeFilsGauche() ? a.filsGauche().hauteur() : 0;
        int hd = a.existeFilsDroit() ? a.filsDroit().hauteur() : 0;

        int hgg = (hg > 0) ? ((a.filsGauche().existeFilsGauche()) ? a.filsGauche().filsGauche().hauteur() : 0) : 0;
        int hgd = (hg > 0) ? ((a.filsGauche().existeFilsDroit()) ? a.filsGauche().filsDroit().hauteur() : 0) : 0;

        int hdd = (hd > 0) ? ((a.filsDroit().existeFilsDroit()) ? a.filsDroit().filsDroit().hauteur() : 0) : 0;
        int hdg = (hd > 0) ? ((a.filsDroit().existeFilsGauche()) ? a.filsDroit().filsGauche().hauteur() : 0) : 0;

        if (hg - hd == 2) {
            if (hgg < hgd)
                a.filsGauche(a.filsGauche().rotationG());
            return a.rotationD();
        } // else version symétrique
        if (hg - hd == -2) {
            if (hdd < hdg)
                a.filsDroit(a.filsDroit().rotationD());
            return a.rotationG();
        }

        return a;
    }

    public NoeudAvl<T> inserer(NoeudAvl<T> a, T valeur) {
        if (a == null) {
            return new NoeudAvl<T>(valeur);
        }

        if (a.compare(a, valeur) < 0) {
            a.filsGauche(inserer(a.filsGauche(), valeur));
        } else if (a.compare(a, valeur) > 0) {
            a.filsDroit(inserer(a.filsDroit(), valeur));
        }

        return equilibrer(a);
    }

}