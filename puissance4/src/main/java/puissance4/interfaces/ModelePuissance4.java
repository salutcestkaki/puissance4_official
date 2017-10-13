package puissance4.interfaces;

import puissance4.modele.*;

public interface ModelePuissance4 {
    public void lacherPionDansColonne(Pion pion, int col) throws ExceptionMauvaisNumeroDeColonne,
            ExceptionColonnePleine;
    public void vider();
    public Contenu pionEnPosition( int lig , int col ) throws ExceptionMauvaisNumeroDeLigne, ExceptionMauvaisNumeroDeColonne;
}