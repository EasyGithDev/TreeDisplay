
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import java.awt.Font;
import java.awt.FontMetrics;

import javax.swing.JPanel;

import arbre.ArbreBinaire;
import arbre.Noeud;

public class TreePanel<T extends Comparable<T>> extends JPanel {

    /*
     * L'arbre en question
     */
    private ArbreBinaire<T> arbre;

    /*
     * dimensions de l'arbre (exprimées en nombres de noeuds)
     */
    private int largeur, hauteur;

    /*
     * Coefficients des transformations pour convertir les coordonnées
     * données en nombre de noeuds en coordonnées données en pixels
     */
    private double dx, dy, mx, my;

    /*
     * On consruit un panneau à partir d'un arbre dont on mémorise
     * une fois pour toutes les dimensions.
     */
    public TreePanel(ArbreBinaire<T> arbre) {
        this.arbre = arbre;
        largeur = arbre.calculerPositions(arbre.racine(), 0, 0);
        hauteur = arbre.hauteur();
        setBackground(Color.WHITE);
    }

    public ArbreBinaire<T> ArbreBinaire() {
        return arbre;
    }

    /*
     * Dessin de l'arbre.
     * La méthode paint est appelée notamment à l'occasion des changements
     * de taille de la fenêtre.
     * C'est donc le bon moment pour calculer les coefficients dx, dy, mx, my
     */
    public void paint(Graphics g) {
        super.paint(g);
        if (arbre != null) {
            Dimension d = getSize();
            dx = ((double) d.width) / largeur;
            dy = ((double) d.height) / hauteur;
            mx = dx / 2;
            my = dy / 2;
            dessiner(arbre.racine(), g);
        }
    }

    /*
     * Dessin effectif
     */
    private void dessiner(Noeud<T> a, Graphics g) {
        final int LARG = 10;
        final int HAUT = 10;

        int x1 = (int) (dx * a.X() + mx);
        int y1 = (int) (dy * a.Y() + my);

        if (a.existeFilsGauche()) {
            Noeud<T> f = a.filsGauche();
            int x2 = (int) (dx * f.X() + mx);
            int y2 = (int) (dy * f.Y() + my);
            g.drawLine(x1, y1, x2, y2);
            dessiner(f, g);
        }
        if (a.existeFilsDroit()) {
            Noeud<T> f = a.filsDroit();
            int x2 = (int) (dx * f.X() + mx);
            int y2 = (int) (dy * f.Y() + my);
            g.drawLine(x1, y1, x2, y2);
            dessiner(f, g);
        }

        Color c = g.getColor();
        g.setColor(a.estFeuille() ? Color.yellow : Color.pink);
        int radius = 4 * LARG;
        // g.fillRect(x1 - LARG, y1 - HAUT, 2 * LARG, 2 * HAUT);
        g.fillOval(x1 - LARG, y1 - HAUT, radius, radius);
        g.setColor(c);
        // g.drawRect(x1 - LARG, y1 - HAUT, 2 * LARG, 2 * HAUT);

        g.drawOval(x1 - LARG, y1 - HAUT, radius, radius);

        String s = "" + a.valeur();
        if (s.length() < 2)
            s = " " + s;
        // g.drawString(s, x1 - LARG + 3, y1 + HAUT - 2);

        FontMetrics fm = g.getFontMetrics();
        Font sanSerifFont = new Font("SanSerif", Font.PLAIN, 12);
        g.setFont(sanSerifFont);
        fm = g.getFontMetrics();
        int w = fm.stringWidth(s);
        int h = fm.getAscent();
        g.drawString(s, x1 + (w / 6), y1 + h + 2);

    }

}