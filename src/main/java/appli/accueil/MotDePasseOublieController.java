package appli.accueil;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import service.EmailService;

public class MotDePasseOublieController {

    @FXML
    private TextField emailField;

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
    }
}
