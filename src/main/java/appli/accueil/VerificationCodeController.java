package appli.accueil;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class VerificationCodeController {

    @FXML
    private TextField codeField;

    @FXML
    private Label messageErreur;

    private String codeAttendu;

    public void setCodeAttendu(String code) {
        this.codeAttendu = code;
    }

    @FXML
    private void validerCode() {
        String codeSaisi = codeField.getText();

        if (codeSaisi.equals(codeAttendu)) {
            messageErreur.setText("Code correct !");
            ouvrirFenetreReinitialisation();
        } else {
            messageErreur.setText("Code incorrect.");
        }
    }

    private void ouvrirFenetreReinitialisation() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Réinitialisation du mot de passe");
            stage.setScene(new Scene(root));
            stage.show();

            // Fermer la fenêtre actuelle
            ((Stage) codeField.getScene().getWindow()).close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
