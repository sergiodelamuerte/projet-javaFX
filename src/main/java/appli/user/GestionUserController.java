package appli.user;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.Utilisateur;
import repository.UtilisateurRepository;

import java.net.URL;
import java.util.ResourceBundle;

public class GestionUserController implements Initializable {

    @FXML private TableView<Utilisateur> tableauUser;
    @FXML private Button btnSupprimer;

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColonnes();
        chargerUtilisateurs();
        btnSupprimer.setDisable(true);
    }
    private void initColonnes() {
        String[][] colonnes = {
                {"Id", "id"},
                {"Nom", "nom"},
                {"Prénom", "prenom"},
                {"Email", "email"}

        };

        for (String[] col : colonnes) {
            TableColumn<Utilisateur, String> c = new TableColumn<>(col[0]);
            c.setCellValueFactory(new PropertyValueFactory<>(col[1]));
            tableauUser.getColumns().add(c);
        }
    }


    private void chargerUtilisateurs() {
        tableauUser.getItems().clear();
        tableauUser.getItems().addAll(utilisateurRepository.getTousLesUtilisateurs());
    }

    @FXML
    void cliqueTableauEvent(MouseEvent event) {
        Utilisateur selection = tableauUser.getSelectionModel().getSelectedItem();
        btnSupprimer.setDisable(selection == null);
    }

    @FXML
    void supprimerUser() {
        Utilisateur selection = tableauUser.getSelectionModel().getSelectedItem();

        if (selection == null) return;

        utilisateurRepository.supprimerUtilisateurParEmail(selection.getEmail());
        tableauUser.getItems().remove(selection);
        btnSupprimer.setDisable(true);
    }

    @FXML
    void deconnexion() {
        try {
            FXMLLoader loader = new FXMLLoader(
                    getClass().getResource("/appli/accueil/LoginView.fxml")
            );
            Parent root = loader.load();
            Stage stage = (Stage) tableauUser.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.sizeToScene();
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

