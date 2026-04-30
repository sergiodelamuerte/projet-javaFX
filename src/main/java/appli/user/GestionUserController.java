package appli.user;

import appli.accueil.StartApplication;
import javafx.fxml.Initializable;
import javafx.scene.input.MouseEvent;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Utilisateur;
import repository.UtilisateurRepository;

public class GestionUserController implements Initializable {
    @FXML
    private TableView<Utilisateur> tableauUser;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        initColonnes();
        chargerUtilisateurs();
    }

    private void ChargerUtilisateurs() {
    }

    private void initColonnes() {

        String[][] colonnes = {
                {"Id Utilisateur", "idUser"},
                {"Nom", "nom"},
                {"Prénom", "prenom"},
                {"Email", "mail"},
                {"Rôle", "role"}
        };

        for (String[] col : colonnes) {
            TableColumn<Utilisateur, String> c = new TableColumn<>(col[0]);
            c.setCellValueFactory(new PropertyValueFactory<>(col[1]));
            tableauUser.getColumns().add(c);
        }


    }
    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    private void chargerUtilisateurs() {
        tableauUser.getItems().clear();
        tableauUser.getItems().addAll(utilisateurRepository.getAllUsers());
    }
    @FXML
    private Button supprimer;

    @FXML
    void cliqueTableauEvent(MouseEvent event) {
        Utilisateur selection = tableauUser.getSelectionModel().getSelectedItem();
       supprimer.setDisable(selection == null);
    }
    @FXML
    private Button btnSupprimer;

    @FXML
    void supprimerUser(ActionEvent event) {

        Utilisateur selection = tableauUser.getSelectionModel().getSelectedItem();

        if (selection == null) {
            return;
        }

        boolean ok = utilisateurRepository.supprimerUtilisateurParEmail(selection.getIdUser());

        if (ok) {
            tableauUser.getItems().remove(selection);
        }
    }

    @FXML
    void CliqueTableauEvent(MouseEvent event) {
        Utilisateur selection = tableauUser.getSelectionModel().getSelectedItem();
        btnSupprimer.setDisable(selection == null);

        if (event.getClickCount() == 2 && selection != null) {
            try {
                StartApplication.changeScene("user/modificationUser");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ModificationUserController controller =
                    (ModificationUserController) StartApplication.getControllerFromStage();
            controller.initData(selection);
        }
    }


}



