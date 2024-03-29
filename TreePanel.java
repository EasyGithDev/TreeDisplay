
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

    /**
     * Conversion d'une coordonnée en pixel
     * 
     * @param val
     * @return
     */
    private int convX(int val) {
        return (int) (dx * val + mx);
    }

    private int convY(int val) {
        return (int) (dy * val + my);
    }

    /*
     * Dessin effectif
     * Si fG existe, tracer la ligne entre le noeud et son fG, descendre dans le fG
     * Si fD existe, tracer la ligne entre le noeud et son fD, descendre dans le fD
     * Enfin, dessiner la valeur du noeud
     */
    private void dessiner(Noeud<T> a, Graphics g) {
        final int LARG = 10;
        final int HAUT = 10;

        int x1 = convX(a.X());
        int y1 = convY(a.Y());

        if (a.existeFilsGauche()) {
            Noeud<T> f = a.filsGauche();
            g.drawLine(x1, y1, convX(f.X()), convY(f.Y()));
            dessiner(f, g);
        }
        if (a.existeFilsDroit()) {
            Noeud<T> f = a.filsDroit();
            g.drawLine(x1, y1, convX(f.X()), convY(f.Y()));
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