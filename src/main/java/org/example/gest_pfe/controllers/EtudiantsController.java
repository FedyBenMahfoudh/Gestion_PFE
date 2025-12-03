package org.example.gest_pfe.controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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

        // Load students
        loadEtudiants();

        System.out.println("Etudiants loaded: " + etudiantsList.size());

        // Bind list to table
        tableEtudiants.setItems(etudiantsList);
    }

    private void loadEtudiants() {
        try{
            etudiantsList.clear();
            List<Etudiant> student_list = etudiantService.getAllEtudiants();
            etudiantsList.addAll(student_list);
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
        System.out.println("Modifier Étudiant clicked");
        // Edit selected student
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
        System.out.println("Rechercher Étudiant clicked");
        // Implement search logic
    }
}