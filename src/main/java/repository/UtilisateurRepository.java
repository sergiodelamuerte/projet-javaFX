package repository;
import database.Database;
import java.sql.Connection;
import model.Utilisateur;
import java.sql.*;
import java.util.ArrayList;
public class UtilisateurRepository {
    private Connection connection;
    public UtilisateurRepository() {
        connection = Database.getConnexion();
    }
    public void ajouterUtilisateur(Utilisateur utilisateur) {
        String sql = "INSERT INTO utilisateurs (nom, prenom, email, mdp, role) VALUES (?, ?, ?, ?, ?)";
        try {
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, utilisateur.getNom());
            stmt.setString(2, utilisateur.getPrenom());
            stmt.setString(3, utilisateur.getEmail());
            stmt.setString(4, utilisateur.getMdp());
            stmt.setString(5, utilisateur.getRole());
            stmt.executeUpdate();
            System.out.println("Utilisateur ajouté avec succès !");
        } catch (SQLException e) {
            System.out.println("Erreur lors de l'ajout de l'utilisateur : " + e.getMessage());
        }
    }
public Utilisateur getUtilisateurParEmail(String email) {
        String sql = "SELECT * FROM utilisateurs WHERE email = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
           ResultSet Rs = stmt.executeQuery();
            if (Rs.next()){
                Utilisateur user = new Utilisateur(Rs.getInt("id"),Rs.getString ("nom"), Rs.getString("prenom"), Rs.getString("email"), Rs.getString("mdp"));
                return user;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    return null;
}
    public ArrayList<Utilisateur> getTousLesUtilisateurs() {
        String sql = "SELECT * FROM utilisateurs";
        ArrayList<Utilisateur> utilisateurs = new ArrayList<>();
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet Rs = stmt.executeQuery();
            while (Rs.next()){
                Utilisateur user = new Utilisateur(Rs.getInt("id"),Rs.getString ("nom"), Rs.getString("prenom"), Rs.getString("email"), Rs.getString("mdp"));
                utilisateurs.add(user);


            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return utilisateurs;

    }
    public void supprimerUtilisateurParEmail(String email) {
        String sql = "Delete FROM utilisateurs WHERE email = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, email);
             stmt.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
