package org.example.gest_pfe.services;

import org.example.gest_pfe.config.Database;
import org.example.gest_pfe.models.Etudiant;
import org.postgresql.util.PSQLException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtudiantService {
    public List<Etudiant> getAllEtudiants() {
        List<Etudiant> etudiants = new ArrayList<>();
        try {
            Connection conn = Database.getConnection();
            Statement stmt = conn.createStatement();
            String query = "SELECT matricule, prenom, nom, date_naissance, email, specialite, parcours FROM etudiants";
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                Etudiant temp = new Etudiant(
                        rs.getInt("matricule"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getDate("date_naissance").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("specialite"),
                        rs.getString("parcours")
                );
                etudiants.add(temp);
            }
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion a la base de données");
            ex.printStackTrace();
        }

        return etudiants;
    }

    public boolean addEtudiant(Etudiant e) throws Exception {
        String query = "INSERT INTO etudiants(matricule,prenom, nom, date_naissance, email, specialite, parcours) VALUES(?,?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {
            ps.setInt(1, e.getMatricule());
            ps.setString(2, e.getPrenom());
            ps.setString(3, e.getNom());
            ps.setDate(4, Date.valueOf(e.getDateNaissance()));
            ps.setString(5, e.getEmail());
            ps.setString(6, e.getSpecialite());
            ps.setString(7, e.getParcours());

            return ps.executeUpdate() > 0;

        } catch (PSQLException error) {

            // Detect duplicate key constraint violation
            if (error.getSQLState().equals("23505")) { // 23505 = unique_violation
                throw new RuntimeException("Étudiant avec cette matricule existe déjà.");
            }
            throw new RuntimeException("Erreur lors de l'ajout : " + error.getMessage());
        } catch (SQLException err) {
            throw new RuntimeException("Erreur SQL : " + err.getMessage());
        }
    }

    public boolean updateEtudiant(Etudiant e) throws Exception {
        String query = "UPDATE etudiants " +
                "SET nom = ?, prenom = ?, date_naissance = ?, email = ?, specialite = ?, parcours = ? " +
                "WHERE matricule = ?";


        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setString(1, e.getNom());
            ps.setString(2, e.getPrenom());
            ps.setDate(3, Date.valueOf(e.getDateNaissance()));
            ps.setString(4, e.getEmail());
            ps.setString(5, e.getSpecialite());
            ps.setString(6, e.getParcours());
            ps.setInt(7, e.getMatricule());
            return ps.executeUpdate() > 0;
        } catch (Exception ex) {
            System.out.println("Erreur de connexion a la base de données");
            ex.printStackTrace();
            throw new RuntimeException("Erreur lors de la modification : " + ex.getMessage());
        }
    }

    public boolean deleteEtudiant(Etudiant e){
        String query = "DELETE FROM etudiants WHERE matricule = ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, e.getMatricule());
            return ps.executeUpdate() > 0;
        } catch (SQLException ex) {
            System.out.println("Erreur de connexion a la base de données");
            ex.printStackTrace();
            return false;
        }
    }

    public Etudiant getEtudiant(int matricule){
        String query = "SELECT * FROM etudiants WHERE matricule = ?";

        try {
            Connection conn = Database.getConnection();
            PreparedStatement ps = conn.prepareStatement(query);
            ps.setInt(1, matricule);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Etudiant temp = new Etudiant(
                        rs.getInt("matricule"),
                        rs.getString("prenom"),
                        rs.getString("nom"),
                        rs.getDate("date_naissance").toLocalDate(),
                        rs.getString("email"),
                        rs.getString("specialite"),
                        rs.getString("parcours")
                );
                return temp;
            }
        }catch (Exception ex) {
            System.out.println("Erreur de connexion a la base de données");
            ex.printStackTrace();
        }

        return null;
    }
}
