package arbre;

public class ArbreConvertion<T extends Comparable<T>> {

    public static <T extends Comparable<T>> NoeudPosition<T> convertion(Noeud<T> src) {
        return convertion(src, null);
    }

    public static <T extends Comparable<T>> NoeudPosition<T> convertion(Noeud<T> src, NoeudPosition<T> dest) {

        if (dest == null) {
            dest = new NoeudPosition<T>(src.valeur());
        }
        if (src.existeFilsGauche()) {
            dest.filsGauche(new NoeudPosition<T>(src.filsGauche().valeur()));
            convertion(src.filsGauche(), dest.filsGauche());

        }
        if (src.existeFilsDroit()) {
            dest.filsDroit(new NoeudPosition<T>(src.filsDroit().valeur()));
            convertion(src.filsDroit(), dest.filsDroit());
        }

        return dest;
    }

    public static <T extends Comparable<T>> NoeudPosition<T> convertion(NoeudAvl<T> src) {
        return convertion(src, null);
    }

    public static <T extends Comparable<T>> NoeudPosition<T> convertion(NoeudAvl<T> src, NoeudPosition<T> dest) {

        if (dest == null) {
            dest = new NoeudPosition<T>(src.valeur());
        }

        if (src.existeFilsGauche()) {
            dest.filsGauche(new NoeudPosition<T>(src.filsGauche().valeur()));
            convertion(src.filsGauche(), dest.filsGauche());

        }

        if (src.existeFilsDroit()) {
            dest.filsDroit(new NoeudPosition<T>(src.filsDroit().valeur()));
            convertion(src.filsDroit(), dest.filsDroit());
        }

        return dest;
    }

}
