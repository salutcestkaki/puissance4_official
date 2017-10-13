package puissance4.modele;

import puissance4.interfaces.ModelePuissance4;

import java.util.ArrayList;
import static puissance4.modele.Pion.JAUNE;
import static puissance4.modele.Pion.ROUGE;
import static puissance4.modele.Vide.CASEVIDE;

public class Modele implements ModelePuissance4 {

    public static final int NB_COLS = 7;
    public static final int NB_LIG = 6;


    public static final int CASE_VIDE = 0;
    public static final int PION_JAUNE = 1;
    public static final int PION_ROUGE = 2;

    // Une liste de liste d'Integer
    ArrayList<ArrayList<Pion>> modele;


    public Modele()
    {
        modele = new ArrayList<ArrayList<Pion>>();
        for( int col=0; col < NB_COLS; col++ )
        {
            modele.add( new ArrayList<Pion>());
        }

    }

    public void lacherPionDansColonne( Pion pion , int col ) throws ExceptionMauvaisNumeroDeColonne, ExceptionColonnePleine {
        assurerBonNumeroDeCol( col );
        assurerColonneNonPleine( col );
        if ( modele.get( col - 1).size() < NB_LIG )
       {
           modele.get( col - 1).add( pion );
       }

    }

    public Contenu pionEnPosition( int lig , int col ) throws ExceptionMauvaisNumeroDeLigne, ExceptionMauvaisNumeroDeColonne {

        assurerBonNumeroDeCol( col );
        assurerBonNumeroDeLigne( lig );

        ArrayList<Pion> colonne = modele.get( col - 1 );
        if ( colonne.size() > lig - 1 ){
            return colonne.get( lig - 1 );
        }
        else
        {
            return CASEVIDE;
        }

    }

    public void vider()
    {
        for( ArrayList<Pion> col : modele )
        {
            col.clear();
        }
    }

    public int nbPionsJoues()
    {
        int nb = 0;
        for( ArrayList<Pion> col : modele )
        {
            nb+= col.size();
        }
        return nb;
    }


    public boolean colonnePleine( int col ) throws ExceptionMauvaisNumeroDeColonne {
        assurerBonNumeroDeCol( col );
        return modele.get( col - 1).size() == NB_LIG;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for( int lig=NB_LIG; lig > 0 ; lig-- ) {
            for (int col = 1; col <= NB_COLS; col++) {
                Contenu pion = null;
                try {
                    pion = pionEnPosition( lig , col );
                } catch (ExceptionMauvaisNumeroDeLigne exceptionMauvaisNumeroDeLigne) {
                    exceptionMauvaisNumeroDeLigne.printStackTrace();
                } catch (ExceptionMauvaisNumeroDeColonne exceptionMauvaisNumeroDeColonne) {
                    exceptionMauvaisNumeroDeColonne.printStackTrace();
                }
                if( pion.equals(JAUNE))
                    sb.append("J");
                else if( pion.equals(ROUGE))
                    sb.append("R");
                else
                    sb.append(".");


            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void assurerBonNumeroDeLigne( int lig ) throws ExceptionMauvaisNumeroDeLigne {
        if ( lig < 1 || lig > NB_LIG)
            throw new ExceptionMauvaisNumeroDeLigne("Mauvais numero de ligne :" + lig );
    }

    public void assurerBonNumeroDeCol( int col ) throws ExceptionMauvaisNumeroDeColonne {
        if ( col < 1 || col > NB_COLS)
            throw new ExceptionMauvaisNumeroDeColonne("Mauvais numero de colonne :" +  col );
    }

    public void assurerColonneNonPleine( int col ) throws ExceptionColonnePleine, ExceptionMauvaisNumeroDeColonne {
        if ( colonnePleine( col ) )
            throw new ExceptionColonnePleine("Colonne pleine :" + col );
    }



}
