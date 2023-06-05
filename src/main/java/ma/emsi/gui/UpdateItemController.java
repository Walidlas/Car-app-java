package ma.emsi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.emsi.entities.Voiture;
import ma.emsi.services.VoitureService;

public class UpdateItemController {
    @FXML
    private TextField matriculeTextField;
    @FXML
    private TextField marqueTextField;
    @FXML
    private TextField couleurTextField;
    @FXML
    private TextField prixTextField;
    @FXML
    private TextField kilometrageTextField;
    @FXML
    private TextField vitesseTextField;
    @FXML
    private ChoiceBox<String> mealChoiceBox;
    @FXML
    private Button updateButton;

    private Voiture voiture;
    private VoitureService voitureService = new VoitureService();
    private String voitureMat;

    public void initialize() {
        voiture = voitureService.findByMatt(voitureMat);
        if (voiture != null) {
            matriculeTextField.setText(voiture.getMatricule());
            marqueTextField.setText(voiture.getMarque());
            couleurTextField.setText(voiture.getCouleur());
            prixTextField.setText(String.valueOf(voiture.getPrix()));
            kilometrageTextField.setText(String.valueOf(voiture.getKilometrage()));
            vitesseTextField.setText(String.valueOf(voiture.getVitesse()));
            updateButton.setOnAction(event -> {
                voiture.setMatricule(matriculeTextField.getText());
                voiture.setMarque(marqueTextField.getText());
                voiture.setCouleur(couleurTextField.getText());
                voiture.setPrix(Double.parseDouble(prixTextField.getText()));
                voiture.setKilometrage(Double.parseDouble(kilometrageTextField.getText()));
                voiture.setVitesse(Double.parseDouble(vitesseTextField.getText()));
                voitureService.update(voiture);
                updateButton.getScene().getWindow().hide();
            });
        } else {
            System.out.println("Voiture non existante : " + voitureMat);
        }
    }
    @FXML
    protected void cancel() {
        Stage stage = (Stage) matriculeTextField.getScene().getWindow();
        stage.close();
    }
    public void setVoitureId(String voitureMat) {
        this.voitureMat = voitureMat;
        initialize();
    }
}
