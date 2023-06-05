package ma.emsi.gui;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import ma.emsi.entities.Voiture;
import ma.emsi.services.VoitureService;

public class HelloController{
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

    VoitureService voitureService = new VoitureService();

    @FXML
    protected void onHelloButtonClick() {
        String inputMat = matriculeTextField.getText();
        String inputMar = marqueTextField.getText();
        String inputCoul = couleurTextField.getText();
        Double inputPrix = Double.valueOf(prixTextField.getText());
        Double inputKm = Double.valueOf(kilometrageTextField.getText());
        Double inputVit = Double.valueOf(vitesseTextField.getText());



        Voiture voiture = new Voiture(inputMat, inputMar, inputCoul, inputPrix, inputKm, inputVit);
        voitureService.save(voiture);
        Stage stage = (Stage) matriculeTextField.getScene().getWindow();
        stage.close();

    }
    @FXML
    protected void cancel() {
        Stage stage = (Stage) matriculeTextField.getScene().getWindow();
        stage.close();
    }
}