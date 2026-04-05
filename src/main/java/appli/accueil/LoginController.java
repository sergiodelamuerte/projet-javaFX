package appli.accueil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class LoginController {

    @FXML
    private Button InscriptionBouton;

    @FXML
    private Text confirmationLabel;

    @FXML
    private TextField confirmationTexte;

    @FXML
    private Text emailLabel;

    @FXML
    private TextField emailTexte;

    @FXML
    private Text erreurtext;

    @FXML
    private Text inscriptionLabel;

    @FXML
    private Text mdpLabel;

    @FXML
    private TextField mdptexte;

    @FXML
    private Text nomLabel;

    @FXML
    private TextField nomtexte;

    @FXML
    private Text prenomLabel;

    @FXML
    private TextField prenomtexte;

    @FXML
    void retour(ActionEvent event) {

    }

}
