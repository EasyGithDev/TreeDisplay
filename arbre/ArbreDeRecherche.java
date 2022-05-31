package arbre;

public class ArbreDeRecherche<T extends Comparable<T>> extends ArbreBinaire<T> {

    public Object min() {

        Noeud<T> courant = this.racine;

        while (courant.existeFilsGauche()) {
            courant = courant.filsGauche();
        }

        return (courant != null) ? courant.valeur() : null;
    }

    public Object max() {

        Noeud<T> courant = this.racine;

        while (courant.existeFilsDroit()) {
            courant = courant.filsDroit();
        }

        return (courant != null) ? courant.valeur() : null;
    }

    public Noeud<T> inserer(Noeud<T> a, T valeur) {
        if (a == null) {
            return new Noeud<T>(valeur);
        }

        // if s1 > s2, it returns positive number
        // if s1 < s2, it returns negative number
        // if s1 == s2, it returns 0

        if (a.compare(a, valeur) > 0) {
            a.filsGauche(inserer(a.filsGauche(), valeur));
        } else if (a.compare(a, valeur) < 0) {
            a.filsDroit(inserer(a.filsDroit(), valeur));
        }

        return a;
    }

    public void insererIte(Noeud<T> noeud) {

        boolean insertion = false;
        Noeud<T> courant = this.racine;

        if (courant == null) {
            this.racine = noeud;
            insertion = true;
        }

        while (!insertion) {
            T valeur = courant.valeur();
            T v = noeud.valeur();
            if (v == valeur) {
                insertion = true;
            } else if (courant.compare(courant, noeud) < 0) {
                if (courant.filsDroit() != null) {
                    courant = courant.filsDroit();
                } else {
                    courant.filsDroit(noeud);
                    insertion = true;
                }

            } else if (courant.compare(courant, noeud) > 0) {
                if (courant.filsGauche() != null) {
                    courant = courant.filsGauche();
                } else {
                    courant.filsGauche(noeud);
                    insertion = true;
                }
            }
        }

    }

    public boolean chercher(int valeur) {

        Noeud<T> courant = this.racine;
        boolean trouve = false;

        while (courant != null) {
            int v = (int) courant.valeur();
            if (valeur == v) {
                trouve = true;
                courant = null;
            } else if (valeur > v) {
                courant = courant.filsDroit();
            } else if (valeur < v) {
                courant = courant.filsGauche();
            }
        }
        return trouve;
    }

    public Noeud<T> supprimer(int x, Noeud<T> a) {
        if (a == null)
            return a;
        int v = (int) a.v;
        if (x == v)
            return supprimerRacine(a);
        if (x < v)
            a.g = supprimer(x, a.g);
        else
            a.d = supprimer(x, a.d);
        return a;
    }

    public Noeud<T> supprimerRacine(Noeud<T> a) {

        if (a.g == null)
            return a.d;
        if (a.d == null)
            return a.g;
        Noeud<T> f = dernierDescendant(a.g);
        a.v = f.v;
        a.g = supprimer((int) f.v, a.g);
        return a;
    }

    public Noeud<T> dernierDescendant(Noeud<T> a) {
        if (a.d == null)
            return a;
        return dernierDescendant(a.d);
    }

}