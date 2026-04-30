package session;

import model.Utilisateur;

public class SessionUtilisateur {

    private static SessionUtilisateur instance;
    private Utilisateur utilisateur;

    private SessionUtilisateur() {}

    public static SessionUtilisateur getInstance() {
        if (instance == null) {
            instance = new SessionUtilisateur();
        }
        return instance;
    }

    public static void Utilisateur(Utilisateur utilisateur) {
    }

    public void sauvegardeSession(Utilisateur utilisateur) {
        this.utilisateur = utilisateur;
    }

    public Utilisateur getUtilisateur() {
        return utilisateur;
    }

    public void deconnecter() {
        this.utilisateur =  null;
    }
}
