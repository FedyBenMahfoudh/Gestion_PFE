package org.example.gest_pfe.models;

import java.time.LocalDate;
import java.util.Objects;

public class Etudiant {
    private int matricule;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private String email;
    private String specialite;
    private String parcours;

    public Etudiant() {
    }

    public Etudiant(int matricule, String prenom, String nom, LocalDate dateNaissance, String email, String specialite, String parcours) {
        this.matricule = matricule;
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
        this.email = email;
        this.specialite = specialite;
        this.parcours = parcours;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialite() {
        return specialite;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate date_naissance) {
        this.dateNaissance = date_naissance;
    }

    public int getMatricule() {
        return matricule;
    }

    public void setMatricule(int matricule) {
        this.matricule = matricule;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getParcours() {
        return parcours;
    }

    public void setParcours(String parcours) {
        this.parcours = parcours;
    }

    @Override
    public String toString() {
        return "Etudiant{" +
                "matricule=" + matricule +
                ", nom='" + nom + " " + prenom + '\'' +
                ", parcours='" + parcours + '\'' +
                '}';
    }
}
