package appli.accueil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Utilisateur;
import repository.UtilisateurRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class InscriptionController {

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @FXML private TextField nom;
    @FXML private TextField prenom;
    @FXML private TextField email;
    @FXML private PasswordField mdp;
    @FXML private PasswordField confirmation;
    @FXML private Label labelErreurInsc;

    @FXML
    protected void onInscrireButtonClick(ActionEvent event) {

        String n = nom.getText();
        String p = prenom.getText();
        String e = email.getText();
        String m = mdp.getText();
        String c = confirmation.getText();

        if (n.isEmpty() || p.isEmpty() || e.isEmpty() || m.isEmpty() || c.isEmpty()) {
            labelErreurInsc.setText("Les champs sont vides...");
            return;
        }

        if (!m.equals(c)) {
            labelErreurInsc.setText("Les mots de passe ne correspondent pas");
            return;
        }

        if (utilisateurRepository.getUtilisateurParEmail(e) != null) {
            labelErreurInsc.setText("Un utilisateur existe déjà avec cet email");
            return;
        }

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hash = encoder.encode(m);

        Utilisateur utilisateur = new Utilisateur(n, p, e, hash);
        utilisateurRepository.ajouterUtilisateur(utilisateur);

        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    protected void onRetourButtonClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("LoginView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
