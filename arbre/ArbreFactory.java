package arbre;

import java.util.LinkedList;
import java.util.Queue;

public class ArbreFactory {

    public static ArbreBinaire<String> binaireFromArray(String[] tab) {

        Queue<Noeud<String>> queue = new LinkedList<Noeud<String>>();
        int i = 0;
        Noeud<String> noeud = new Noeud<String>(tab[i]);
        ArbreBinaire<String> abr = new ArbreBinaire<String>();
        abr.racine = noeud;
        queue.add(noeud);
        while (queue.size() != 0) {
            noeud = queue.remove();

            if (i * 2 + 1 < tab.length) {
                Noeud<String> g = new Noeud<String>(tab[i * 2 + 1]);
                noeud.filsGauche(g);
                queue.add(g);
            }
            if (i * 2 + 2 < tab.length) {
                Noeud<String> d = new Noeud<String>(tab[i * 2 + 2]);
                noeud.filsDroit(d);
                queue.add(d);
            }
            i++;
        }

        return abr;
    }

    public static ArbreAvl<String> avlFromArray(String[] tab) {

        ArbreAvl<String> avl = new ArbreAvl<String>();

        for (int i = 0; i < tab.length; i++) {
            avl.racine = avl.inserer(avl.racine, tab[i]);
        }
        return avl;
    }

    public static ArbreAvl<Integer> avlFromArray(Integer[] tab) {

        ArbreAvl<Integer> avl = new ArbreAvl<Integer>();

        for (int i = 0; i < tab.length; i++) {
            avl.racine = avl.inserer(avl.racine, tab[i]);
        }
        return avl;
    }

    public static ArbreDeRecherche<String> abrFromArray(String[] tab) {

        ArbreDeRecherche<String> abr = new ArbreDeRecherche<String>();

        for (int i = 0; i < tab.length; i++) {
            abr.racine = abr.inserer(abr.racine, tab[i]);
        }
        return abr;
    }
}
