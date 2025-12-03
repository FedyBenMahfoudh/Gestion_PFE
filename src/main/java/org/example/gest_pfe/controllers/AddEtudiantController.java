package org.example.gest_pfe.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.example.gest_pfe.models.Etudiant;
import org.example.gest_pfe.services.EtudiantService;

import java.time.LocalDate;

public class AddEtudiantController {
    @FXML private TextField prenomField;
    @FXML private TextField nomField;
    @FXML private DatePicker dateNaissancePicker;
    @FXML private TextField emailField;
    @FXML private TextField specialiteField;
    @FXML
    private ComboBox<String> parcoursCombo;
    @FXML private Label errorLabel;
    @FXML private TextField matriculeField;

    private EtudiantService etudiantService = new EtudiantService();

    private Stage dialogStage;
    private Runnable onSuccess; // callback after successful add

    @FXML
    public void initialize() {
        parcoursCombo.getSelectionModel().selectFirst(); // option par défaut
    }

    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void setOnSuccess(Runnable callback) {
        this.onSuccess = callback;
    }

    @FXML
    private void handleAdd() {
        String prenom = prenomField.getText();
        String nom = nomField.getText();
        LocalDate dateNaissance = dateNaissancePicker.getValue();
        String email = emailField.getText();
        String specialite = specialiteField.getText();
        String parcours = parcoursCombo.getValue();
        String matricule = matriculeField.getText();

        if (matricule.isEmpty() || prenom.isEmpty() || nom.isEmpty() || dateNaissance == null || email.isEmpty() ||
                specialite.isEmpty() || parcours.isEmpty()) {
            errorLabel.setText("⚠️ Tous les champs sont obligatoires.");
            errorLabel.setVisible(true);
            return;
        }

        if (!email.contains("@")) {
            errorLabel.setText("⚠️ Email invalide.");
            errorLabel.setVisible(true);
            return;
        }

        Etudiant e = new Etudiant(Integer.parseInt(matricule), prenom, nom, dateNaissance, email, specialite, parcours);
        try{
            boolean success = etudiantService.addEtudiant(e);

            if (success) {
                if (onSuccess != null) onSuccess.run();
                dialogStage.close();
            }
        } catch (Exception ex) {
            errorLabel.setText(ex.getMessage());
            errorLabel.setVisible(true);
        }
    }

    @FXML
    private void handleCancel() {
        dialogStage.close();
    }
}
