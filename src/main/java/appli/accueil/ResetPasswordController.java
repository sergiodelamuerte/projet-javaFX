package appli.accueil;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class ResetPasswordController {

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Label messageErreur;

    @FXML
    private void valider() {
        String pass1 = newPasswordField.getText();
        String pass2 = confirmPasswordField.getText();

        if (!pass1.equals(pass2)) {
            messageErreur.setText("Les mots de passe ne correspondent pas.");
            messageErreur.setStyle("-fx-text-fill: red;");
            return;
        }

        // TODO : mise à jour du mot de passe en base
        messageErreur.setStyle("-fx-text-fill: green;");
        messageErreur.setText("Mot de passe mis à jour !");
    }
}
