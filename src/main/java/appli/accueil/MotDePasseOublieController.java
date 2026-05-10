package appli.accueil;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.EmailService;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.Parent;

public class MotDePasseOublieController {

    @FXML
    private TextField emailField;
    @FXML private TextField codeField;
    @FXML private Label labelCode;
    @FXML private Label messageLabel;
    @FXML private Button btnVerifier;
    private String codeGenere;

    @FXML
    void envoyerCode() {

        String email = emailField.getText();

        if (email == null || email.isEmpty()) {
            System.out.println("Email vide");
            return;
        }



        codeGenere = EmailService.genererCode();

        String sujet = "Réinitialisation de votre mot de passe";
        String message = "Voici votre code de réinitialisation : " + codeGenere;

        EmailService.envoyerEmail(email, sujet, message);

        System.out.println("Code envoyé à : " + email);
        labelCode.setVisible(true);
        labelCode.setManaged(true);
        codeField.setVisible(true);
        codeField.setManaged(true);
        btnVerifier.setVisible(true);
        btnVerifier.setManaged(true);

        messageLabel.setStyle("-fx-text-fill: green;");
        messageLabel.setText("Code envoyé à : " + email);
    }
    @FXML
    void verifierCode() {
        String codeSaisi = codeField.getText();
        if (codeSaisi.equals(codeGenere)){
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("ResetPassword.fxml"));
                Parent root = loader.load();
                ResetPasswordController controller = loader.getController();
                controller.setEmail(emailField.getText());

                Stage stage = (Stage) codeField.getScene().getWindow();
                stage.setScene(new Scene(root, 400, 300));
                stage.sizeToScene();
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {

            messageLabel.setText("Code incorrect");
        }
    }
}

