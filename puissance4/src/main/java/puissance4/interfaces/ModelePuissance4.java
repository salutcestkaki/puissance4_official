package puissance4.interfaces;

public interface ModelePuissance4 {
    public void lacherPionDansColonne(int col, Pion pion) throws
            ExceptionMauvaisNumeroDeColonne,  ExceptionColonnePleine;

    public void retirerPionDeLaColonne(int col) throws ExceptionMauvaisNumeroDeColonne, puissance4.modele.ExceptionMauvaisNumeroDeColonne;

    public Contenu pionEnPosition(int lig, int col) throws
            ExceptionMauvaisNumeroDeColonne, ExceptionMauvaisNumeroDeLigne, ExceptionColonnePleine, puissance4.modele.ExceptionMauvaisNumeroDeColonne, puissance4.modele.ExceptionMauvaisNumeroDeLigne, puissance4.modele.ExceptionColonnePleine;

    public boolean colonnePleine(int col) throws ExceptionMauvaisNumeroDeColonne, puissance4.modele.ExceptionMauvaisNumeroDeColonne;

    public int nbPionsJoues();

    public void vider();

    public int nbColonnes();
    public int nbLignes();
}
