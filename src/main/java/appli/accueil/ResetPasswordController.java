package appli.accueil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ResetPasswordController {

    @FXML private PasswordField newPasswordField;
    @FXML private PasswordField confirmPasswordField;
    @FXML private Label messageErreur;

    private String email;

    public void setEmail(String email) {
        this.email = email;
    }

    @FXML
    private void valider() {
        String pass1 = newPasswordField.getText();
        String pass2 = confirmPasswordField.getText();

        if (pass1.isEmpty() || pass2.isEmpty()) {
            messageErreur.setStyle("-fx-text-fill: red;");
            messageErreur.setText("Remplir les deux champs");
            return;
        }

        if (!pass1.equals(pass2)) {
            messageErreur.setStyle("-fx-text-fill: red;");
            messageErreur.setText("Les mots de passe ne correspondent pas...");
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("LoginView.fxml")
            );
            Parent root = loader.load();
            Stage stage = (Stage) newPasswordField.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

