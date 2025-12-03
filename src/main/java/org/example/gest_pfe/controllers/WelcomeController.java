package org.example.gest_pfe.controllers;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.net.URL;
import java.util.ResourceBundle;

public class WelcomeController implements Initializable {

    @FXML private VBox mainContainer;
    @FXML private Label titleLabel;
    @FXML private Label subtitleLabel;
    @FXML private Label descriptionLabel;
    @FXML private HBox buttonsContainer;
    @FXML private GridPane featuresGrid;
    @FXML private ImageView heroImage;

    @FXML private VBox studentCard;
    @FXML private VBox teacherCard;
    @FXML private VBox companyCard;
    @FXML private VBox adminCard;

    @FXML private ImageView studentIcon;
    @FXML private ImageView teacherIcon;
    @FXML private ImageView companyIcon;
    @FXML private ImageView adminIcon;

    @FXML private VBox feature1;
    @FXML private VBox feature2;
    @FXML private VBox feature3;
    @FXML private VBox feature4;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setupAnimations();
        setupHoverEffects();
        setupIconClipping();
    }

    private void setupIconClipping() {
        // Make icons circular
        clipCircular(studentIcon, 40);
        clipCircular(teacherIcon, 40);
        clipCircular(companyIcon, 40);
        clipCircular(adminIcon, 40);
    }

    private void clipCircular(ImageView imageView, double radius) {
        Circle clip = new Circle(radius, radius, radius);
        imageView.setClip(clip);
    }

    private void setupAnimations() {
        // Hero section animations
        animateFadeInUp(titleLabel, 0.3, 50);
        animateFadeInUp(subtitleLabel, 0.5, 50);
        animateFadeInUp(descriptionLabel, 0.7, 50);

        // Hero image animation
        animateScale(heroImage, 0.9, 1.0);
        animateFadeIn(heroImage, 1.0);

        // Cards animation with stagger
        animateCardEntry(studentCard, 1.0);
        animateCardEntry(teacherCard, 1.2);
        animateCardEntry(companyCard, 1.4);
        animateCardEntry(adminCard, 1.6);

        // Features animation
        animateFadeInUp(feature1, 1.8, 30);
        animateFadeInUp(feature2, 2.0, 30);
        animateFadeInUp(feature3, 2.2, 30);
        animateFadeInUp(feature4, 2.4, 30);
    }

    private void setupHoverEffects() {
        setupCardHover(studentCard, "#6366F1");
        setupCardHover(teacherCard, "#8B5CF6");
        setupCardHover(companyCard, "#EC4899");
        setupCardHover(adminCard, "#F59E0B");
    }

    private void setupCardHover(VBox card, String accentColor) {
        String originalStyle = card.getStyle();

        card.setOnMouseEntered(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
            st.setToX(1.05);
            st.setToY(1.05);
            st.play();

            card.setStyle(originalStyle +
                    "-fx-border-color: " + accentColor + ";" +
                    "-fx-border-width: 2;");
        });

        card.setOnMouseExited(e -> {
            ScaleTransition st = new ScaleTransition(Duration.millis(200), card);
            st.setToX(1.0);
            st.setToY(1.0);
            st.play();

            card.setStyle(originalStyle);
        });
    }

    private void animateFadeIn(javafx.scene.Node node, double delay) {
        node.setOpacity(0);
        FadeTransition ft = new FadeTransition(Duration.seconds(1), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setDelay(Duration.seconds(delay));
        ft.play();
    }

    private void animateFadeInUp(javafx.scene.Node node, double delay, double distance) {
        node.setOpacity(0);
        node.setTranslateY(distance);

        FadeTransition ft = new FadeTransition(Duration.millis(800), node);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setDelay(Duration.seconds(delay));

        TranslateTransition tt = new TranslateTransition(Duration.millis(800), node);
        tt.setFromY(distance);
        tt.setToY(0);
        tt.setDelay(Duration.seconds(delay));

        ParallelTransition pt = new ParallelTransition(ft, tt);
        pt.play();
    }

    private void animateCardEntry(VBox card, double delay) {
        card.setOpacity(0);
        card.setScaleX(0.8);
        card.setScaleY(0.8);

        FadeTransition ft = new FadeTransition(Duration.millis(600), card);
        ft.setFromValue(0);
        ft.setToValue(1);
        ft.setDelay(Duration.seconds(delay));

        ScaleTransition st = new ScaleTransition(Duration.millis(600), card);
        st.setFromX(0.8);
        st.setFromY(0.8);
        st.setToX(1.0);
        st.setToY(1.0);
        st.setDelay(Duration.seconds(delay));

        ParallelTransition pt = new ParallelTransition(ft, st);
        pt.play();
    }

    private void animateScale(javafx.scene.Node node, double from, double to) {
        node.setScaleX(from);
        node.setScaleY(from);

        ScaleTransition st = new ScaleTransition(Duration.seconds(1.5), node);
        st.setFromX(from);
        st.setFromY(from);
        st.setToX(to);
        st.setToY(to);
        st.setDelay(Duration.seconds(0.5));
        st.play();
    }

    @FXML
    private void handleStudentLogin() {
        System.out.println("Connexion Étudiant");
        animateButtonClick(studentCard);
    }

    @FXML
    private void handleTeacherLogin() {
        System.out.println("Connexion Enseignant");
        animateButtonClick(teacherCard);
    }

    @FXML
    private void handleCompanyLogin() {
        System.out.println("Connexion Entreprise");
        animateButtonClick(companyCard);
    }

    @FXML
    private void handleAdminLogin() {
        System.out.println("Connexion Administrateur");
        animateButtonClick(adminCard);
    }

    private void animateButtonClick(VBox card) {
        ScaleTransition st = new ScaleTransition(Duration.millis(100), card);
        st.setToX(0.95);
        st.setToY(0.95);
        st.setAutoReverse(true);
        st.setCycleCount(2);
        st.play();
    }
}
