package arbre;

import java.util.Stack;

public class ArbreExpression extends ArbreBinaire<String> {

    public void infix(Noeud<String> courant) {
        if (courant == null)
            return;

        System.out.print("( ");

        infix(courant.filsGauche());
        System.out.print(" " + courant.valeur() + " ");
        infix(courant.filsDroit());

        System.out.print(" )");

    }

    public static int calculer(Noeud<String> courant) {

        int res = 0;

        if (courant == null)
            return res;

        int g = calculer(courant.filsGauche());
        int d = calculer(courant.filsDroit());

        switch ((String) courant.valeur()) {
            case "+":
                res = g + d;
                break;
            case "-":
                res = g - d;
                break;
            case "*":
                res = g * d;
                break;
            case "/":
                res = g / d;
                break;
            default:
                res = Integer.parseInt((String) courant.valeur());
                break;
        }

        return res;
    }

    public static ArbreExpression creer(String[] postfixe) {

        Stack<Noeud<String>> stack = new Stack<Noeud<String>>();
        Noeud<String> noeud = null;
        ArbreExpression abr = new ArbreExpression();

        for (int k = 0; k < postfixe.length; k++) {
            String x = postfixe[k];

            if (x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/")) {

                noeud = new Noeud<String>(x);
                noeud.filsDroit(stack.pop());
                noeud.filsGauche(stack.pop());

                stack.add(noeud);

            } else {
                stack.add(new Noeud<String>(x));
            }
        }

        abr.racine = stack.pop();

        return abr;
    }

}