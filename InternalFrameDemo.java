/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;
import javax.swing.JMenuBar;
import javax.swing.JFrame;
import javax.swing.KeyStroke;

import arbre.ArbreAvl;
import arbre.ArbreBinaire;
import arbre.ArbreDeRecherche;
import arbre.ArbreExpression;
import arbre.ArbreFactory;
import arbre.ArbrePosition;

import java.awt.event.*;
import java.awt.*;

/*
 * InternalFrameDemo.java requires:
 *   MyInternalFrame.java
 */
public class InternalFrameDemo extends JFrame
        implements ActionListener {

    public static String AVL = "avl";
    public static String AB = "ab";
    public static String ABR = "abr";
    public static String EXPR = "expr";

    public static int FRAME_WIDTH = 800;
    public static int FRAME_HEIGTH = 600;

    JDesktopPane desktop;

    public InternalFrameDemo() {
        super("InternalFrameDemo");

        // Make the big window be indented 50 pixels from each edge
        // of the screen.
        int inset = 50;
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        setBounds(inset, inset,
                screenSize.width - inset * 2,
                screenSize.height - inset * 2);

        // Set up the GUI.
        desktop = new JDesktopPane(); // a specialized layered pane
        // createFrame(); // create first "window"
        setContentPane(desktop);
        setJMenuBar(createMenuBar());

        // Make dragging a little faster but perhaps uglier.
        desktop.setDragMode(JDesktopPane.OUTLINE_DRAG_MODE);
    }

    protected JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // Set up the lone menu.
        JMenu menu = new JMenu("Document");
        menu.setMnemonic(KeyEvent.VK_D);
        menuBar.add(menu);

        // Set up the first menu item.
        JMenuItem menuItem = new JMenuItem("New");
        menuItem.setMnemonic(KeyEvent.VK_N);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_N, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("new");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        // Set up the Ab menu item.
        menuItem = new JMenuItem("Arbre binaire");
        menuItem.setActionCommand(AB);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        // Set up the Abr menu item.
        menuItem = new JMenuItem("Abre binaire de recherche");
        menuItem.setActionCommand(ABR);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        // Set up the Avl menu item.
        menuItem = new JMenuItem("Arbre Avl");
        menuItem.setActionCommand(AVL);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        // Set up the Expr menu item.
        menuItem = new JMenuItem("Abre d'expression");
        menuItem.setActionCommand(EXPR);
        menuItem.addActionListener(this);
        menu.add(menuItem);

        // Set up the second menu item.
        menuItem = new JMenuItem("Quit");
        menuItem.setMnemonic(KeyEvent.VK_Q);
        menuItem.setAccelerator(KeyStroke.getKeyStroke(
                KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.setActionCommand("quit");
        menuItem.addActionListener(this);
        menu.add(menuItem);

        return menuBar;
    }

    // React to menu selections.
    public void actionPerformed(ActionEvent e) {
        if ("new".equals(e.getActionCommand())) { // new
            createFrame();
        } else if (AB.equals(e.getActionCommand())) { // new
            createAbFrame();
        } else if (ABR.equals(e.getActionCommand())) { // new
            createAbrFrame();
        } else if (AVL.equals(e.getActionCommand())) { // new
            createAvlFrame();
        } else if (EXPR.equals(e.getActionCommand())) { // new
            createExprFrame();
        } else { // quit
            quit();
        }
    }

    // Create a new internal frame.
    protected void createFrame() {
        MyInternalFrame frame = new MyInternalFrame();
        // frame.setContentPane(new InternalPanel());
        frame.setVisible(true); // necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    // Create a Ab internal frame.
    protected void createAbFrame() {

        String tree = JOptionPane.showInputDialog("Please enter your tree: ");
        String[] words = tree.trim().split(",");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }

        ArbreBinaire<String> ab = ArbreFactory.binaireFromArray(words);
        ArbrePosition<String> ap = new ArbrePosition<String>(ab.racine());
        TreePanel treePanel = new TreePanel(ap);

        MyInternalFrame frame = new MyInternalFrame(600, 400, "Arbre binaire");
        frame.setContentPane(new InternalPanel(treePanel));
        frame.setVisible(true); // necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    // Create a Ab internal frame.
    protected void createExprFrame() {

        String tree = JOptionPane.showInputDialog("Please enter your tree: ");

        ArbreExpression ae = ArbreExpression.creer(tree.trim().split(" "));
        ArbrePosition<String> ap = new ArbrePosition<String>(ae.racine());
        TreePanel treePanel = new TreePanel(ap);

        InternalPanel ip = new InternalPanel(treePanel);
        JButton compute = ip.makeNavigationButton("Play-icon", null,
                "Compute the expression",
                "Compute");
        JToolBar toolBar = ip.getToolBar();

        compute.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String str = "Résultat de l'expression : " + ArbreExpression.calculer(ae.racine());
                ip.getTextArea().append(str);
            }
        });

        toolBar.add(compute);

        MyInternalFrame frame = new MyInternalFrame(FRAME_WIDTH, FRAME_HEIGTH, "Arbre d'expression");
        frame.setContentPane(ip);
        frame.setVisible(true); // necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    // Create a Abr internal frame.
    protected void createAbrFrame() {

        String tree = JOptionPane.showInputDialog("Please enter your tree: ");
        String[] words = tree.trim().split(",");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }

        ArbreDeRecherche<String> abr = ArbreFactory.abrFromArray(words);
        ArbrePosition<String> ap = new ArbrePosition<String>(abr.racine());
        TreePanel treePanel = new TreePanel(ap);

        MyInternalFrame frame = new MyInternalFrame(FRAME_WIDTH, FRAME_HEIGTH, "Arbre binaire de recherche");
        frame.setContentPane(new InternalPanel(treePanel));
        frame.setVisible(true); // necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    // Create a Avl internal frame.
    protected void createAvlFrame() {

        String tree = JOptionPane.showInputDialog("Please enter your tree: ");
        String[] words = tree.trim().split(",");
        for (int i = 0; i < words.length; i++) {
            words[i] = words[i].trim();
        }

        ArbreAvl<String> avl = ArbreFactory.avlFromArray(words);
        ArbrePosition<String> ap = new ArbrePosition<String>(avl.racine());
        TreePanel treePanel = new TreePanel(ap);

        MyInternalFrame frame = new MyInternalFrame(600, 400, "Arbre Avl");
        frame.setContentPane(new InternalPanel(treePanel));
        frame.setVisible(true); // necessary as of 1.3
        desktop.add(frame);
        try {
            frame.setSelected(true);
        } catch (java.beans.PropertyVetoException e) {
        }
    }

    // Quit the application.
    protected void quit() {
        System.exit(0);
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        // Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        // Create and set up the window.
        InternalFrameDemo frame = new InternalFrameDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Display the window.
        frame.setVisible(true);
    }

    public static void main(String[] args) {

        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}