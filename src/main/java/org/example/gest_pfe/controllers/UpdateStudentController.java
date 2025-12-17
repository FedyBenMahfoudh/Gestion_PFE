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

public class UpdateStudentController {

    @FXML private TextField matriculeField;
    @FXML private TextField prenomField;
    @FXML private TextField nomField;
    @FXML private DatePicker dateNaissancePicker;
    @FXML private TextField emailField;
    @FXML private TextField specialiteField;
    @FXML private ComboBox<String> parcoursCombo;
    @FXML private Label errorLabel;

    private EtudiantService etudiantService = new EtudiantService();

    private Stage dialogStage;
    private Runnable onSuccess;   // callback after successful update

    private Etudiant etudiant;    // the student being edited

    @FXML
    public void initialize() {
        parcoursCombo.getSelectionModel().selectFirst();
    }

    // Called by the opening window
    public void setDialogStage(Stage stage) {
        this.dialogStage = stage;
    }

    public void setOnSuccess(Runnable callback) {
        this.onSuccess = callback;
    }

    // Called when opening the Modify window
    public void setEtudiant(Etudiant e) {
        this.etudiant = e;

        matriculeField.setText(String.valueOf(e.getMatricule()));
        matriculeField.setEditable(false); // cannot change primary key
        prenomField.setText(e.getPrenom());
        nomField.setText(e.getNom());
        dateNaissancePicker.setValue(e.getDateNaissance());
        emailField.setText(e.getEmail());
        specialiteField.setText(e.getSpecialite());
        parcoursCombo.setValue(e.getParcours());
    }

    @FXML
    private void handleUpdate() {
        String prenom = prenomField.getText();
        String nom = nomField.getText();
        LocalDate dateNaissance = dateNaissancePicker.getValue();
        String email = emailField.getText();
        String specialite = specialiteField.getText();
        String parcours = parcoursCombo.getValue();

        if (prenom.isEmpty() || nom.isEmpty() || dateNaissance == null || email.isEmpty()
                || specialite.isEmpty() || parcours.isEmpty()) {
            errorLabel.setText("⚠️ Tous les champs sont obligatoires.");
            errorLabel.setVisible(true);
            return;
        }

        if (!email.contains("@")) {
            errorLabel.setText("⚠️ Email invalide.");
            errorLabel.setVisible(true);
            return;
        }

        // Update object
        etudiant.setPrenom(prenom);
        etudiant.setNom(nom);
        etudiant.setDateNaissance(dateNaissance);
        etudiant.setEmail(email);
        etudiant.setSpecialite(specialite);
        etudiant.setParcours(parcours);

        try {
            boolean success = etudiantService.updateEtudiant(etudiant);

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
