package appli.user;

import appli.accueil.StartApplication;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Utilisateur;
import repository.UtilisateurRepository;

public class ModificationUserController {

    private Utilisateur utilisateurSel;

    @FXML private TextField nomField;
    @FXML private TextField prenomField;
    @FXML private TextField emailField;
    @FXML private TextField roleField;

    private UtilisateurRepository utilisateurRepository = new UtilisateurRepository();

    public void initData(Utilisateur utilisateur) {
        this.utilisateurSel = utilisateur;
        nomField.setText(utilisateur.getNom());
        prenomField.setText(utilisateur.getPrenom());
        emailField.setText(utilisateur.getEmail());
        roleField.setText(utilisateur.getRole());
    }

    @FXML
    void modifierUtilisateur() {

        utilisateurSel.setNom(nomField.getText());
        utilisateurSel.setPrenom(prenomField.getText());
        utilisateurSel.setEmail(emailField.getText());
        utilisateurSel.setRole(roleField.getText());

        utilisateurRepository.update(utilisateurSel);

        try {
            StartApplication.changeScene("user/GestionUser");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
