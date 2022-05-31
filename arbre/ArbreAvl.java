package arbre;

public class ArbreAvl<T extends Comparable<T>> extends ArbreBinaire<T> {

    public Noeud<T> equilibrer(Noeud<T> a) {

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

    public Noeud<T> inserer(Noeud<T> a, T valeur) {
        if (a == null) {
            return new Noeud<T>(valeur);
        }

        if (a.compare(a, valeur) < 0) {
            a.filsGauche(inserer(a.filsGauche(), valeur));
        } else if (a.compare(a, valeur) > 0) {
            a.filsDroit(inserer(a.filsDroit(), valeur));
        }

        return equilibrer(a);
    }

}