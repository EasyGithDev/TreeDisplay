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

/*
 * ToolBarDemo.java requires the following addditional files:
 * images/Back24.gif
 * images/Forward24.gif
 * images/Up24.gif
 */

import javax.swing.JToolBar;
import javax.swing.JButton;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.plaf.FontUIResource;

import arbre.ArbreAvl;
import arbre.ArbreBinaire;
import arbre.ArbreFactory;
import arbre.ArbreBinaire;
import arbre.Noeud;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.Queue;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

public class InternalPanel extends JPanel
        implements ActionListener {

    TreePanel treePanel;
    JTextArea textArea;
    JScrollPane scrollPane;
    JScrollPane infoScrollPane;
    JToolBar toolBar;

    private final static String newline = "\n";

    static final private String PRINT = "print";
    static final private String SAVE = "save";

    static final private String IMG_DIR = "/Users/florent/Documents";

    public InternalPanel(TreePanel treePanel) {
        super(new BorderLayout());

        this.treePanel = treePanel;

        // Create the toolbar.
        toolBar = new JToolBar("Still draggable");
        toolBar.setLayout(new BoxLayout(toolBar, BoxLayout.Y_AXIS));

        addButtons(toolBar);

        // Create the info panel
        textArea = new JTextArea();
        textArea.setFont(new FontUIResource("Serif", FontUIResource.ITALIC, 16));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);

        // Create the scrollPane
        scrollPane = new JScrollPane(treePanel);
        infoScrollPane = new JScrollPane(textArea);

        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setResizeWeight(1.0);
        splitPane.add(scrollPane);
        splitPane.add(toolBar);

        add(splitPane, BorderLayout.CENTER);
        add(infoScrollPane, BorderLayout.PAGE_END);

        // Show infos
        showInfos();
    }

    protected void addButtons(JToolBar toolBar) {
        JButton button = null;

        // first button
        button = makeNavigationButton("Printer-Ink-icon", PRINT,
                "Print the image",
                "Print");
        // toolBar.add(button);

        // second button
        button = makeNavigationButton("Save-icon", SAVE,
                "Save the image",
                "Save");
        toolBar.add(button);

    }

    public JButton makeNavigationButton(String imageName,
            String actionCommand,
            String toolTipText,
            String altText) {
        // Look for the image.
        String imgLocation = "images/"
                + imageName
                + ".png";

        URL imageURL = InternalPanel.class.getResource(imgLocation);

        // Create and initialize the button.
        JButton button = new JButton();
        button.setActionCommand(actionCommand);
        button.setToolTipText(toolTipText);
        button.addActionListener(this);

        if (imageURL != null) { // image found
            button.setIcon(new ImageIcon(imageURL, altText));
        } else { // no image found
            button.setText(altText);
            System.err.println("Resource not found: "
                    + imgLocation);
        }

        return button;
    }

    public void actionPerformed(ActionEvent e) {
        String cmd = e.getActionCommand();

        // Handle each button.
        if (PRINT.equals(cmd)) { // first button clicked

        } else if (SAVE.equals(cmd)) { // second button clicked

            try {
                BufferedImage image = new BufferedImage(treePanel.getWidth(), treePanel.getHeight(),
                        BufferedImage.TYPE_INT_RGB);
                Graphics2D graphics2D = image.createGraphics();
                treePanel.paint(graphics2D);
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmmss");
                String now = LocalDateTime.now().format(formatter);
                ImageIO.write(image, "jpeg",
                        new File(IMG_DIR + "/tree-" + now + ".jpeg"));
            } catch (Exception exception) {
                // code
                System.err.println(exception);
            }
        }

    }

    private void showInfos() {
/*
        Queue<Object> queue = new LinkedList<Object>();
        ArbreBinaire<String> arbre = treePanel.ArbreBinaire();

        arbre.prefix(arbre.racine(), queue);
        String prefix = treeToStrint(queue);

        arbre.infix(arbre.racine(), queue);
        String infix = treeToStrint(queue);

        arbre.postfix(arbre.racine(), queue);
        String postfix = treeToStrint(queue);

        textArea.append(newline);
        textArea.append("Hauteur : " + arbre.hauteur() + newline + newline);
        textArea.append("Taille : " + arbre.taille() + newline + newline);
        textArea.append("Prefixe : " + prefix + newline + newline);
        textArea.append("Infix : " + infix + newline + newline);
        textArea.append("Postfix : " + postfix + newline + newline);
*/
    }

    private String treeToStrint(Queue<Object> queue) {
        StringBuilder sb = new StringBuilder();
        while (!queue.isEmpty()) {
            sb.append(queue.remove());
            sb.append(" -> ");
        }

        return sb.substring(0, sb.length() - 4).toString();
    }

    public JToolBar getToolBar() {
        return toolBar;
    }

    public JTextArea getTextArea() {
        return textArea;
    }

    /**
     * Create the GUI and show it. For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    private static void createAndShowGUI() {
        // Create and set up the window.
        JFrame frame = new JFrame("TreePanelDemo");
        frame.setPreferredSize(new Dimension(1024, 800));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // binaire
        ArbreBinaire<String> ab = new ArbreBinaire<String>(new Noeud<String>(
                new Noeud<String>(
                        new Noeud<String>("D"),
                        "B",
                        new Noeud<String>("E")),
                "A",
                new Noeud<String>(
                        null,
                        "C",
                        new Noeud<String>("G"))));

        // degénéré
        ab = new ArbreBinaire<String>(new Noeud<String>(null,
                "A",
                new Noeud<String>(
                        new Noeud<String>(null,
                                "C",
                                new Noeud<String>(new Noeud<String>("E"),
                                        "D",
                                        null)),
                        "B",
                        null)));

        // complet
        String[] tab = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O" };
        ab = ArbreFactory.binaireFromArray(tab);

        // parfait
        String[] tab2 = { "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L" };
        ab = ArbreFactory.binaireFromArray(tab2);

        // avl
        // String[] tab3 = { "8", "5", "4", "2", "1", "-1" };
        Integer[] tab3 = { 8, 5, 4, 2, 1, -1 };

        ArbreAvl<Integer> avl = ArbreFactory.avlFromArray(tab3);

        // frame.add(new InternalPanel(new TreePanel(new
        // ArbreBinaire<String>(ab.racine()))));
        frame.add(
                new InternalPanel(
                        new TreePanel(
                                new ArbreBinaire<Integer>(avl.racine()))));

        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    // public static void main(String[] args) {

    //     // Schedule a job for the event dispatch thread:
    //     // creating and showing this application's GUI.
    //     SwingUtilities.invokeLater(new Runnable() {
    //         public void run() {
    //             // Turn off metal's use of bold fonts
    //             UIManager.put("swing.boldMetal", Boolean.FALSE);
    //             createAndShowGUI();
    //         }
    //     });
    // }
}