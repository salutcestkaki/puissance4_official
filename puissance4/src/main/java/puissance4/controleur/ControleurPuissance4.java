package puissance4.controleur;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import puissance4.interfaces.*;
import static puissance4.interfaces.Pion.JAUNE;

/**
 *  Classe controleur
 *  METHODE1 :  couche de separation entre la vue et le modele
 *  Utilisation d'interfaces ModelePuissance4 et VuePuissance4
 */
public class ControleurPuissance4 implements EventHandler {

    private ModelePuissance4 modele;
    private VuePuissance4 vue;
    private Pion pionActif = JAUNE;

    /**
     * Permet d'avoir le modele sous forme d'interface
     * @return modele
     */
    public ModelePuissance4 getModele() {
        return modele;
    }

    /**
     * Permet de connecter le modele au controleur grace à une interface
     * @param modele
     */
    public void setModele(ModelePuissance4 modele) {
        this.modele = modele;
    }

    /**
     * Permet d'avoir la vue connectee sous forme d'interface
     * @return vue
     */
    public VuePuissance4 getVue() {
        return vue;
    }

    /**
     * Permet de connecter la vue au controleur grace à une interface
     * @param vue
     */
    public void setVue(VuePuissance4 vue) {
        this.vue = vue;
        // lier la gestions des boutons de la vue au controleur (this)
        vue.ajouterEcouteurBoutons( this );

    }

    /**
     * Permet de dessiner la representation complete du modele sur la vue
     *
     * @throws ExceptionMauvaisNumeroDeColonne
     * @throws ExceptionMauvaisNumeroDeLigne
     * @throws ExceptionColonnePleine
     */
    public void dessinerModeleSurVue() throws ExceptionColonnePleine {
        for( int col=1; col<= modele.nbColonnes(); col++) {
            for( int lig=1; lig<= modele.nbLignes(); lig++) {
                //Dessiner le modele sur la vue avec la fonction dessineContenu de la vue
                try {
                    Contenu contenu = modele.pionEnPosition( lig , col );
                    vue.dessineContenu( lig , col , contenu );
                } catch (ExceptionMauvaisNumeroDeColonne exceptionMauvaisNumeroDeColonne) {
                    exceptionMauvaisNumeroDeColonne.printStackTrace();
                } catch (ExceptionMauvaisNumeroDeLigne exceptionMauvaisNumeroDeLigne) {
                    exceptionMauvaisNumeroDeLigne.printStackTrace();
                }
            }
        }
    }

    /**
     * Permet de controler les evenements en provenance de la vue
     * @param event
     */
    @Override
    public void handle(Event event) {
        // Controle de la validité du coup
        if ( ((Button)event.getSource()).getText().equals("Jouer le coup"))
            controleEtJoueCoup();
        if ( ((Button)event.getSource()).getText().equals("Nouvelle partie"))
            nouvellePartie();

    }

    /**
     * Permet vider le modele puis de le redessiner sur la vue
     */
    private void nouvellePartie() {
        modele.vider();
        try {
            dessinerModeleSurVue();
        } catch (ExceptionColonnePleine exceptionColonnePleine) {
            exceptionColonnePleine.printStackTrace();
        }
        vue.nouveauCoup();
    }

    /**
     *  Permet de jouer le coup dans la colonne pointee par le pion a jouer
     */
    private void controleEtJoueCoup() {
        // implementer le coup sur la colonne jouee sur la vue
        // recuperation de la colonne jouee
        int colonneJoue = vue.colonneJouee();
        // lacher du pion actif
        try {
            modele.lacherPionDansColonne( colonneJoue , pionActif );
            dessinerModeleSurVue();
            vue.nouveauCoup();
        } catch (ExceptionMauvaisNumeroDeColonne exceptionMauvaisNumeroDeColonne) {
            exceptionMauvaisNumeroDeColonne.printStackTrace();
        } catch (ExceptionColonnePleine exceptionColonnePleine) {
            exceptionColonnePleine.printStackTrace();
        }
        // preparer un nouveau coup
        // traiter le cas colonne pleine


    }


}
