package puissance4.modele;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.equalTo;
import static puissance4.modele.Pion.JAUNE;
import static puissance4.modele.Pion.ROUGE;
import static puissance4.modele.Vide.CASEVIDE;

public class TestModele {

    private Modele modele;

    @Before
    public void creationModele()
    {
        modele = new Modele();
    }

    @Test
    public void testEmpilementEtPositionsPions() throws ExceptionMauvaisNumeroDeColonne, ExceptionPionInvalide, ExceptionMauvaisNumeroDeLigne, ExceptionColonnePleine {

        modele.lacherPionDansColonne(JAUNE, 2);
        modele.lacherPionDansColonne(ROUGE, 2);
        modele.lacherPionDansColonne(JAUNE, 2);
        modele.lacherPionDansColonne(JAUNE, 5);
        modele.lacherPionDansColonne(ROUGE, 5);
        assertTrue(modele.pionEnPosition(1, 5) == JAUNE);
        assertTrue(modele.pionEnPosition(2, 5) == ROUGE);
        assertTrue(modele.pionEnPosition(1, 2) == JAUNE);
        assertTrue(modele.pionEnPosition(2, 2) == ROUGE);
        assertTrue(modele.pionEnPosition(6, 2) == CASEVIDE);


    }

    @Test
    public void testViderModele() throws ExceptionMauvaisNumeroDeColonne, ExceptionPionInvalide, ExceptionColonnePleine {

        modele.lacherPionDansColonne(JAUNE, 2);
        modele.lacherPionDansColonne(ROUGE, 2);
        modele.lacherPionDansColonne(JAUNE, 2);
        modele.vider();
        assertTrue(modele.nbPionsJoues() == 0);

    }

    @Test
    public void testNbPionsJoues() throws ExceptionMauvaisNumeroDeColonne, ExceptionPionInvalide, ExceptionColonnePleine {

        modele.lacherPionDansColonne(JAUNE, 2);
        modele.lacherPionDansColonne(ROUGE, 3);
        modele.lacherPionDansColonne(JAUNE, 4);

        assertTrue(modele.nbPionsJoues() == 3);
    }


    @Test
    public void testToStringModele() throws ExceptionMauvaisNumeroDeColonne, ExceptionPionInvalide, ExceptionColonnePleine {
        modele.lacherPionDansColonne( JAUNE , 1 );
        modele.lacherPionDansColonne( JAUNE , 1 );
        modele.lacherPionDansColonne( JAUNE , 1 );
        modele.lacherPionDansColonne( ROUGE , 7 );
        modele.lacherPionDansColonne( ROUGE , 7 );
        modele.lacherPionDansColonne( ROUGE , 7 );

        String attendu =
                 ".......\n"+
                 ".......\n"+
                 ".......\n"+
                 "J.....R\n"+
                 "J.....R\n"+
                 "J.....R\n";


        assertThat( modele.toString() , equalTo( attendu) );

    }



    @Test( expected = ExceptionColonnePleine.class)
    public void testExceptionColonnePleine() throws ExceptionMauvaisNumeroDeColonne, ExceptionPionInvalide, ExceptionColonnePleine {

        for( int i=1; i<= Modele.NB_LIG+1; i++ )
            modele.lacherPionDansColonne( JAUNE , 2 );
    }
    @Test( expected = ExceptionMauvaisNumeroDeColonne.class)
    public void testExceptionMauvaisNumeroDeColonne() throws ExceptionMauvaisNumeroDeColonne, ExceptionPionInvalide, ExceptionColonnePleine {

        modele.lacherPionDansColonne( JAUNE , Modele.NB_COLS+1 );
    }
    @Test( expected = ExceptionMauvaisNumeroDeLigne.class)
    public void testExceptionMauvaisNumeroDeLigne() throws ExceptionMauvaisNumeroDeColonne, ExceptionPionInvalide, ExceptionColonnePleine, ExceptionMauvaisNumeroDeLigne {

        modele.pionEnPosition( Modele.NB_LIG+1, 1);
    }

}
