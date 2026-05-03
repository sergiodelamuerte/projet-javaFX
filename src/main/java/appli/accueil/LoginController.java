package appli.accueil;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import repository.UtilisateurRepository;
import model.Utilisateur;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import session.SessionUtilisateur;

public class LoginController {

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @FXML
    private Button InscriptionBouton;

    @FXML
    private Text confirmationLabel;

    @FXML
    private TextField confirmationTexte;

    @FXML
    private Text emailLabel;

    @FXML
    private TextField emailField;

    @FXML
    private Text erreurtext;

    @FXML
    private Text inscriptionLabel;

    @FXML
    private Text mdpLabel;

    @FXML
    private TextField mdpField;

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

    @FXML
    void connexion(ActionEvent event) {

        String email = emailField.getText();
        String mdp = mdpField.getText();

        Utilisateur utilisateur = utilisateurRepository.getUtilisateurParEmail(email);

        if (utilisateur == null) {
            erreurtext.setText("Cet Email n'appartient à aucun utilisateur");
            return;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        if (!encoder.matches(mdp, utilisateur.getMotDePasse())) {
            erreurtext.setText("Email ou mot de passe incorrect");
            return;
        }

        try {
            Parent root = FXMLLoader.load(getClass().getResource("GestionUserView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void inscription(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("InscriptionView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @FXML
    void mdpOublie(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("MotDePasseOublieView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
