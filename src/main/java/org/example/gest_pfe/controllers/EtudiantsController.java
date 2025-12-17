package org.example.gest_pfe.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.example.gest_pfe.models.Etudiant;
import org.example.gest_pfe.services.EtudiantService;

import java.net.URL;
import java.time.LocalDate;
import java.util.List;

public class EtudiantsController {
    private EtudiantService etudiantService = new EtudiantService();

    @FXML
    private TableView<Etudiant> tableEtudiants;

    @FXML
    private TableColumn<Etudiant, Integer> colMatricule;

    @FXML
    private TableColumn<Etudiant, String> colNom;

    @FXML
    private TableColumn<Etudiant, String> colPrenom;

    @FXML
    private TableColumn<Etudiant, LocalDate> colDateNaissance;

    @FXML
    private TableColumn<Etudiant, String> colEmail;

    @FXML
    private TableColumn<Etudiant, String> colSpecialite;

    @FXML
    private TableColumn<Etudiant, String> colParcours;

    @FXML
    private TableColumn<Etudiant, Void> colActions;

    @FXML
    private TextField matriculeField;

    @FXML
    private Label errorLabel;

    private ObservableList<Etudiant> etudiantsList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Initialize table columns
        colMatricule.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
        colDateNaissance.setCellValueFactory(new PropertyValueFactory<>("dateNaissance"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
        colSpecialite.setCellValueFactory(new PropertyValueFactory<>("specialite"));
        colParcours.setCellValueFactory(new PropertyValueFactory<>("parcours"));

        // Configure actions column (Modify/Delete buttons per row)
        colActions.setCellFactory(col -> new TableCell<>() {
            private final Button btnModify = new Button("Modifier");
            private final Button btnDelete = new Button("Supprimer");
            private final HBox container = new HBox(5, btnModify, btnDelete);

            {
                btnModify.setOnAction(event -> {
                    Etudiant etudiant = getTableView().getItems().get(getIndex());
                    openModifierDialog(etudiant);
                });

                btnDelete.setOnAction(event -> {
                    Etudiant etudiant = getTableView().getItems().get(getIndex());
                    if (etudiant != null) {
                        try {
                            boolean response = etudiantService.deleteEtudiant(etudiant);
                            if (response) {
                                etudiantsList.remove(etudiant);
                            }
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(container);
                }
            }
        });

        // Load students
        loadEtudiants();

        System.out.println("Etudiants loaded: " + etudiantsList.size());

        // Bind list to table
        tableEtudiants.setItems(etudiantsList);
    }

    private void loadEtudiants() {
        try{
            List<Etudiant> student_list = etudiantService.getAllEtudiants();
            etudiantsList.setAll(student_list);
        }catch(Exception e){
            System.out.println("Etudiants loading failed: " + e.getMessage());
        }
    }

    @FXML
    public void handleAjouter() {
        try {
            URL url = getClass().getResource("/org/example/gest_pfe/addStudent.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Ajouter Étudiant");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // blocks main window

            AddEtudiantController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setOnSuccess(this::loadEtudiants); // reload table after add

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleModifier() {
        Etudiant selected = tableEtudiants.getSelectionModel().getSelectedItem();
        openModifierDialog(selected);
    }

    private void openModifierDialog(Etudiant etudiant) {
        if (etudiant == null) {
            return;
        }
        try {
            URL url = getClass().getResource("/org/example/gest_pfe/updateStudent.fxml");
            FXMLLoader loader = new FXMLLoader(url);
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Modify Etudiant");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL); // blocks main window

            UpdateStudentController controller = loader.getController();
            controller.setDialogStage(stage);
            controller.setOnSuccess(this::loadEtudiants); // reload table after update
            controller.setEtudiant(etudiant);

            stage.showAndWait();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleSupprimer() {
        Etudiant selected = tableEtudiants.getSelectionModel().getSelectedItem();
        if (selected != null) {
            etudiantsList.remove(selected);
        }
    }

    @FXML
    public void handleRechercher() {
        errorLabel.setVisible(false);

        if (matriculeField.getText().isEmpty()) {
            errorLabel.setText("Veuillez entrer un matricule");
            errorLabel.setVisible(true);
            return;
        }

        try {
            int matricule = Integer.parseInt(matriculeField.getText());
            Etudiant e = etudiantService.getEtudiant(matricule);
            System.out.println(e);
            if (e != null) {
                etudiantsList.setAll(e);
            } else {
                etudiantsList.clear();
                errorLabel.setText("Aucun étudiant trouvé");
                errorLabel.setVisible(true);
            }

        } catch (NumberFormatException ex) {
            errorLabel.setText("Matricule invalide");
            errorLabel.setVisible(true);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void handleCancel() {
        matriculeField.clear();
        loadEtudiants();
    }
}