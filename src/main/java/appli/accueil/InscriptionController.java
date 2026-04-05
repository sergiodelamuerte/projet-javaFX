package appli.accueil;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.*;
import javafx.scene.text.Text;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.io.IOException;

public class InscriptionController {
    @FXML private Text InscriptionLabel;

    @FXML private TextField nom;
    @FXML private TextField prenom;
    @FXML private TextField email;
    @FXML private PasswordField mdp;
    @FXML private PasswordField confirmation;
    @FXML private Label labelErreurInsc;

    @FXML
    protected void onInscrireButtonClick() {

        if (nom.getText().isEmpty() || prenom.getText().isEmpty()
                || email.getText().isEmpty()
                || mdp.getText().isEmpty()
                || confirmation.getText().isEmpty()) {

            labelErreurInsc.setText("Les champs sont vides...");

        } else if (!mdp.getText().equals(confirmation.getText())) {

            labelErreurInsc.setText("Les mots de passe ne correspondent pas");

        } else {
            System.out.println("Inscription OK : " + nom.getText());
            labelErreurInsc.setText("");
        }
    }

    @FXML
    protected void onRetourButtonClick(ActionEvent event) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/appli.accueil/LoginView.fxml"));
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}