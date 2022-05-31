import javax.swing.JFrame;

import arbre.ArbreBinaire;
import arbre.ArbreDeRecherche;
import arbre.ArbrePosition;
import arbre.Noeud;

public class Main {

    public static void afficheArbre(String titre, ArbrePosition<String> arbre) {
        TreePanel panneau = new TreePanel(arbre);

        JFrame cadre = new JFrame(titre);
        cadre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cadre.setBounds(100, 100, 600, 400);
        cadre.setContentPane(panneau);
        cadre.setVisible(true);
    }

    /*
     * Programme principal: on crée un cadre et on y place
     * un panneau réprsentant un arbre
     */
    public static void main(String[] args) {

        // ArbreBinaire<String> ab = new ArbreBinaire<String>(new Noeud<String>(
        // new Noeud<String>(
        // new Noeud<String>("D"),
        // "B",
        // new Noeud<String>("E")),
        // "A",
        // new Noeud<String>(
        // null,
        // "C",
        // new Noeud<String>("G"))));

        // afficheArbre("Arbre d'expression", new ArbrePosition<String>((ab.racine())));

        int[] tab = { 15, 12, 20, 8, 14, 16, 21, 10, 13, 17 };

        ArbreDeRecherche<Integer> abr = new ArbreDeRecherche<Integer>();

        for (int i = 0; i < tab.length; i++) {
            abr.insererIte(new Noeud<Integer>(tab[i]));
        }
        abr.largeur(abr.racine());

System.out.println();

Noeud<Integer> n    =    abr.supprimer(13,abr.racine());
//abr.racine(n);
abr.largeur(abr.racine());


        /*
         * ArbreBinaire ab = new ArbreBinaire(new Noeud<String>(
         * new Noeud<String>(
         * new Noeud<String>(11),
         * 4,
         * new Noeud<String>(13)),
         * 6,
         * new Noeud<String>(12)));
         * 
         * // int[] tab = { 7, 15, 3, 8, 11, 9, 4, 5, 2, 13, 6 };
         * // int[] tab = { 20, 15, 11, 9, 6, 5, 2, -1 };
         * int[] tab = { 8, 5, 4, 2, 1, -1 };
         * 
         * ArbreDeRecherche abr = new ArbreDeRecherche();
         * ArbreAvl avl = new ArbreAvl();
         * 
         * for (int i = 0; i < tab.length; i++) {
         * abr.inserer(new Noeud<String>(tab[i]));
         * avl.racine = avl.inserer(avl.racine, tab[i]);
         * }
         * 
         * ArbreAvl.largeur(avl.racine);
         * 
         * abr.prefix(abr.racine);
         * System.out.println();
         * 
         * abr.infix(abr.racine);
         * System.out.println();
         * 
         * abr.postfix(abr.racine);
         * System.out.println();
         * 
         * System.out.println("Abr hauteur " + abr.hauteur());
         * System.out.println("Abr taille " + abr.taille());
         * System.out.println("Min " + abr.min());
         * System.out.println("Max " + abr.max());
         * 
         * String expr = "3 4 + 5 * 7 /";
         * ArbreExpression ae = ArbreExpression.creer(expr.trim().split(" "));
         * System.out.println(ArbreExpression.calculer(ae.racine));
         * 
         * ArbrePosition p1 = new ArbrePosition(ArbreConvertion.convertion(ab.racine,
         * null));
         * ArbrePosition p2 = new ArbrePosition(ArbreConvertion.convertion(abr.racine,
         * null));
         * ArbrePosition p3 = new ArbrePosition(ArbreConvertion.convertion(avl.racine,
         * null));
         * ArbrePosition p4 = new ArbrePosition(ArbreConvertion.convertion(ae.racine,
         * null));
         * 
         * afficheArbre("Arbre binaire", p1);
         * afficheArbre("Arbre binaire de recherche", p2);
         * afficheArbre("Arbre Avl", p3);
         * afficheArbre("Arbre d'expression", p4);
         */
    }
}
