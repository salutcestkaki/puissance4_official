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
    public void testEmpilementEtPositionsPions() throws ExceptionMauvaisNumeroDeColonne,  ExceptionMauvaisNumeroDeLigne, ExceptionColonnePleine {

        modele.lacherPionDansColonne(2,JAUNE);
        modele.lacherPionDansColonne(2,ROUGE);
        modele.lacherPionDansColonne(2,JAUNE);
        modele.lacherPionDansColonne(5,JAUNE);
        modele.lacherPionDansColonne(5,ROUGE);
        assertTrue(modele.pionEnPosition(1, 5) == JAUNE);
        assertTrue(modele.pionEnPosition(2, 5) == ROUGE);
        assertTrue(modele.pionEnPosition(1, 2) == JAUNE);
        assertTrue(modele.pionEnPosition(2, 2) == ROUGE);
        assertTrue(modele.pionEnPosition(6, 2) == CASEVIDE);


    }

    @Test
    public void testViderModele() throws ExceptionMauvaisNumeroDeColonne, ExceptionColonnePleine {

        modele.lacherPionDansColonne(2,JAUNE);
        modele.lacherPionDansColonne(2,ROUGE);
        modele.lacherPionDansColonne(2,JAUNE);
        modele.vider();
        assertTrue(modele.nbPionsJoues() == 0);

    }

    @Test
    public void testNbPionsJoues() throws ExceptionMauvaisNumeroDeColonne, ExceptionColonnePleine {

        modele.lacherPionDansColonne(2,JAUNE);
        modele.lacherPionDansColonne(3,ROUGE);
        modele.lacherPionDansColonne(4,JAUNE);

        assertTrue(modele.nbPionsJoues() == 3);
    }


    @Test
    public void testToStringModele() throws ExceptionMauvaisNumeroDeColonne, ExceptionColonnePleine {
        modele.lacherPionDansColonne( 1 ,JAUNE );
        modele.lacherPionDansColonne( 1 ,JAUNE );
        modele.lacherPionDansColonne( 1 ,JAUNE );
        modele.lacherPionDansColonne(7, ROUGE  );
        modele.lacherPionDansColonne(7, ROUGE  );
        modele.lacherPionDansColonne(7, ROUGE  );
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
    public void testExceptionColonnePleine() throws ExceptionMauvaisNumeroDeColonne, ExceptionColonnePleine {

        for( int i=1; i<= Modele.NB_LIG+1; i++ )
            modele.lacherPionDansColonne( 2,JAUNE );
    }
    @Test( expected = ExceptionMauvaisNumeroDeColonne.class)
    public void testExceptionMauvaisNumeroDeColonne() throws ExceptionMauvaisNumeroDeColonne, ExceptionColonnePleine {

        modele.lacherPionDansColonne( JAUNE , Modele.NB_COLS+1 );
    }
    @Test( expected = ExceptionMauvaisNumeroDeLigne.class)
    public void testExceptionMauvaisNumeroDeLigne() throws ExceptionMauvaisNumeroDeColonne, ExceptionColonnePleine, ExceptionMauvaisNumeroDeLigne {

        modele.pionEnPosition( Modele.NB_LIG+1, 1);
    }

}
