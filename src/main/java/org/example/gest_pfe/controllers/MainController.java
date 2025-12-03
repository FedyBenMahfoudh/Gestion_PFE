package org.example.gest_pfe.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class MainController {

    /* ===========================
     * === TABLE VIEW BINDINGS ===
     * =========================== */

    @FXML private TableView<?> tableEtudiants;
    @FXML private TableView<?> tableEncadrants;
    @FXML private TableView<?> tableProjets;
    @FXML private TableView<?> tableStages;
    @FXML private TableView<?> tableEntreprises;
    @FXML private TableView<?> tableRapports;
    @FXML private TableView<?> tableSoutenances;
    @FXML private TableView<?> tableJury;

    @FXML private TabPane mainTabs;


    /* ===========================
     * ===== INITIALIZATION ======
     * =========================== */

    @FXML
    public void initialize() {
        System.out.println("MainController initialized.");

        loadEtudiants();
        loadEncadrants();
        loadProjets();
        loadStages();
        loadEntreprises();
        loadRapports();
        loadSoutenances();
        loadJurys();
    }


    /* ===========================
     * ===== DATA LOADING ========
     * =========================== */

    private void loadEtudiants() {
        System.out.println("Loading students...");
        // tableEtudiants.setItems(FXCollections.observableList(EtudiantService.getAll()));
    }

    private void loadEncadrants() {
        System.out.println("Loading encadrants...");
        // tableEncadrants.setItems(FXCollections.observableList(EncadrantService.getAll()));
    }

    private void loadProjets() {
        System.out.println("Loading projects...");
    }

    private void loadStages() {
        System.out.println("Loading stages...");
    }

    private void loadEntreprises() {
        System.out.println("Loading entreprises...");
    }

    private void loadRapports() {
        System.out.println("Loading rapports...");
    }

    private void loadSoutenances() {
        System.out.println("Loading soutenances...");
    }

    private void loadJurys() {
        System.out.println("Loading jurys...");
    }


    /* ======================================================
     * =============== BUTTON ACTION HANDLERS ===============
     * ====================================================== */

    // ---------------- ETUDIANTS ------------------

    @FXML
    private void onAddEtudiant() {
        System.out.println("Add Etudiant clicked.");
        // openAddEtudiantDialog();
    }

    @FXML
    private void onEditEtudiant() {
        System.out.println("Edit Etudiant clicked.");
        // get selected student → open edit dialog
    }

    @FXML
    private void onDeleteEtudiant() {
        System.out.println("Delete Etudiant clicked.");
        // confirm → delete
    }


    // ---------------- ENCADRANTS ------------------

    @FXML
    private void onAddEncadrant() {
        System.out.println("Add Encadrant clicked.");
    }

    @FXML
    private void onEditEncadrant() {
        System.out.println("Edit Encadrant clicked.");
    }

    @FXML
    private void onDeleteEncadrant() {
        System.out.println("Delete Encadrant clicked.");
    }


    // ---------------- PROJETS ------------------

    @FXML
    private void onAddProjet() {
        System.out.println("Add Projet clicked.");
    }

    @FXML
    private void onAssignEtudiantProjet() {
        System.out.println("Assign student to project clicked.");
    }

    @FXML
    private void onAssignEncadrantProjet() {
        System.out.println("Assign encadrant to project clicked.");
    }

    @FXML
    private void onDeleteProjet() {
        System.out.println("Delete Projet clicked.");
    }


    // ---------------- STAGES ------------------

    @FXML
    private void onAddStage() {
        System.out.println("Add Stage clicked.");
    }

    @FXML
    private void onEditStage() {
        System.out.println("Edit Stage clicked.");
    }

    @FXML
    private void onDeleteStage() {
        System.out.println("Delete Stage clicked.");
    }


    // ---------------- ENTREPRISES ------------------

    @FXML
    private void onAddEntreprise() {
        System.out.println("Add Entreprise clicked.");
    }

    @FXML
    private void onEditEntreprise() {
        System.out.println("Edit Entreprise clicked.");
    }

    @FXML
    private void onDeleteEntreprise() {
        System.out.println("Delete Entreprise clicked.");
    }


    // ---------------- RAPPORTS ------------------

    @FXML
    private void onDeposerRapport() {
        System.out.println("Déposer Rapport clicked.");
    }

    @FXML
    private void onTelechargerRapport() {
        System.out.println("Télécharger PDF clicked.");
    }

    @FXML
    private void onDeleteRapport() {
        System.out.println("Delete Rapport clicked.");
    }


    // ---------------- SOUTENANCES ------------------

    @FXML
    private void onPlanifierSoutenance() {
        System.out.println("Planifier Soutenance clicked.");
    }

    @FXML
    private void onModifierDateSoutenance() {
        System.out.println("Modifier Date clicked.");
    }

    @FXML
    private void onDeleteSoutenance() {
        System.out.println("Delete Soutenance clicked.");
    }


    // ---------------- JURY ------------------

    @FXML
    private void onCreateJury() {
        System.out.println("Créer Jury clicked.");
    }

    @FXML
    private void onEditJury() {
        System.out.println("Edit Jury clicked.");
    }

    @FXML
    private void onDeleteJury() {
        System.out.println("Delete Jury clicked.");
    }


    /* ===========================
     * ====== MENU ACTIONS =======
     * =========================== */

    @FXML
    private void onAbout() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About");
        alert.setHeaderText("PFE Management System");
        alert.setContentText("Developed by: [Your Name]\nJavaFX + PostgreSQL project.");
        alert.showAndWait();
    }

    @FXML
    private void onExit() {
        Stage stage = (Stage) mainTabs.getScene().getWindow();
        stage.close();
    }
}

