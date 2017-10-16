package puissance4.modele;import puissance4.interfaces.*;import java.util.ArrayList;/** * La classe représentant le modèle puissance4 */public class Modele implements ModelePuissance4 {    private static final int NB_COL = 7;    private static final int NB_LIG = 6;    private final ArrayList<ArrayList<Pion>> modele;    public Modele() {        modele = new ArrayList<ArrayList<Pion>>();        for (int col = 0; col < NB_COL; col++) {            modele.add(new ArrayList<Pion>(NB_LIG));        }    }    /**     * Permet de jouer un coup dans une colonne     *     * @param col : la colonne jouee     * @param pion : le pion joue     * @throws ExceptionMauvaisNumeroDeColonne     * @throws ExceptionColonnePleine     */    public void lacherPionDansColonne(int col, Pion pion) throws            ExceptionMauvaisNumeroDeColonne,ExceptionColonnePleine {        assurerBonNumeroDeColonne(col);        assurerColonneNonPleine(col);        ArrayList<Pion> colonne = modele.get(col - 1);        if (colonne.size() < NB_LIG) {            colonne.add(pion);        }    }    public void lacherPionDansColonne(int col, puissance4.interfaces.Pion pion) throws puissance4.interfaces.ExceptionMauvaisNumeroDeColonne, puissance4.interfaces.ExceptionColonnePleine {    }    /**     * Permet de retirer le pion le plus haut dans la colonne col     *     * @param col     * @throws ExceptionMauvaisNumeroDeColonne     */    public void retirerPionDeLaColonne(int col) throws ExceptionMauvaisNumeroDeColonne {        assurerBonNumeroDeColonne(col);        ArrayList<Pion> colonne = modele.get(col - 1);        if (colonne.size() > 0) {            colonne.remove(colonne.size() - 1);        }    }    /**     * Permet d'avoir le contenu du jeu aux coordonnées lig, col     *     * @param lig     * @param col     * @return     * @throws ExceptionMauvaisNumeroDeColonne     * @throws ExceptionMauvaisNumeroDeLigne     * @throws ExceptionColonnePleine     */    public Contenu pionEnPosition(int lig, int col) throws            ExceptionMauvaisNumeroDeColonne, ExceptionMauvaisNumeroDeLigne, ExceptionColonnePleine {        assurerBonNumeroDeLigne(lig);        assurerBonNumeroDeColonne(col);        ArrayList<Pion> colonne = modele.get(col - 1);        if (lig <= colonne.size()) {            return colonne.get(lig - 1);        }        return CaseVide.CASE_VIDE;    }    /**     * Permet de savoir si le colonne col est remplie     *     * @param col     * @return true si la colonne est remplie     */    public boolean colonnePleine(int col) throws ExceptionMauvaisNumeroDeColonne {        assurerBonNumeroDeColonne(col);        return modele.get(col - 1).size() == NB_LIG;    }    /**     * Permet de connaitre le nombre de pions joues     *     * @return nombre de pions joues     */    public int nbPionsJoues() {        int n = 0;        for (ArrayList<Pion> col : modele) {            n += col.size();        }        return n;    }    /**     *     * @return la representation du damier     */    public String toString() {        StringBuilder jeu = new StringBuilder();        for (int lig = NB_LIG; lig >= 1; lig--) {            for (int col = 1; col <= NB_COL; col++) {                String pion = " ";                try {                    if  (this.pionEnPosition(lig, col) == Pion.ROUGE ) {                        pion = "R";                    }                    else if (this.pionEnPosition(lig, col) == Pion.JAUNE ) {                        pion = "J";                    }                } catch (Exception e) {                }                jeu.append(pion);            }            jeu.append('\n');        }        return jeu.toString();    }    /**     * Permet de vider le modele     */    public void vider() {        for (ArrayList<Pion> col : modele) {            col.clear();        }    }    /**     * Retour le nombre de colonnes du damier     * @return le nombre de colonnes     */    @Override    public int nbColonnes() {        return NB_COL;    }    /**     * Retour le nombre de lignes du damier     * @return le nombre de ligne     */    @Override    public int nbLignes() {        return NB_LIG;    }    /**     * Renvoi une exception si la colonne col n'existe pas dans le damier     * @param col     * @throws ExceptionMauvaisNumeroDeColonne     */    private void assurerBonNumeroDeColonne(int col) throws ExceptionMauvaisNumeroDeColonne {        if (col < 1 || col > NB_COL) {            throw new ExceptionMauvaisNumeroDeColonne("Mauvais numéro de colonne : " + col);        }    }    /**     * Renovi une exception si la ligne lig n'existe pas dans le damier     * @param lig     * @throws ExceptionMauvaisNumeroDeLigne     */    private void assurerBonNumeroDeLigne(int lig) throws ExceptionMauvaisNumeroDeLigne {        if (lig < 1 || lig > NB_LIG) {            throw new ExceptionMauvaisNumeroDeLigne("Mauvais numéro de ligne : " + lig);        }    }    /**     * Renvoi une exception si la colonne col est remplie     * @param col     * @throws ExceptionColonnePleine     * @throws ExceptionMauvaisNumeroDeColonne     */    private void assurerColonneNonPleine(int col) throws ExceptionColonnePleine,            ExceptionMauvaisNumeroDeColonne {        assurerBonNumeroDeColonne(col);        ArrayList<Pion> colonne = modele.get(col - 1);        if (colonnePleine(col))            throw new ExceptionColonnePleine("La colonne " + col + " est pleine");    }}