package arbre;

import java.util.Comparator;
import java.lang.Comparable;

public class NoeudComparator <T extends Comparable<T>> implements Comparator<Noeud<T>> {
    public int compare(Noeud<T> a, Noeud<T> b) {
        return a.valeur().compareTo(b.valeur());
    }
}
