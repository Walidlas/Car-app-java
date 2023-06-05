package ma.emsi.gui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import ma.emsi.entities.Voiture;
import ma.emsi.services.VoitureService;

import java.io.IOException;
import java.util.List;

public class VoitureController {
    @FXML
    private TableView<Voiture> tableView;
    @FXML
    private TableColumn<Voiture, String> matColumn;
    @FXML
    private TableColumn<Voiture, String> marqueColumn;
    @FXML
    private TableColumn<Voiture, String> couleurColumn;
    @FXML
    private TableColumn<Voiture, Double> prixColumn;
    @FXML
    private TableColumn<Voiture, Double> kilometrageColumn;
    @FXML
    private TableColumn<Voiture, Double> vitesseColumn;
    @FXML
    private TableColumn<Voiture, Voiture> deleteColumn;
    @FXML
    private TableColumn<Voiture, Voiture> updateColumn;
    @FXML
    private Button addVoitureButton;
    @FXML
    private Button importTXTButton;
    @FXML
    private Button exportTXTButton;
    @FXML
    private Button importExcelButton;
    @FXML
    private Button exportExcelButton;

    VoitureService voitureService = new VoitureService();

    private void openUpdateDialog(String voitureId) {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("updateVoiture.fxml"));
        try {
            Parent root = loader.load();
            UpdateItemController updateController = loader.getController();
            updateController.setVoitureId(voitureId);

            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();

            List<Voiture> updatedDishList = voitureService.findAll();
            tableView.setItems(FXCollections.observableArrayList(updatedDishList));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private void refreshTableContent() {
        List<Voiture> dishList = voitureService.findAll();
        tableView.setItems(FXCollections.observableArrayList(dishList));
    }
    public void initialize() {
        matColumn.setCellValueFactory(new PropertyValueFactory<>("matricule"));
        marqueColumn.setCellValueFactory(new PropertyValueFactory<>("marque"));
        couleurColumn.setCellValueFactory(new PropertyValueFactory<>("couleur"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<>("prix"));
        kilometrageColumn.setCellValueFactory(new PropertyValueFactory<>("kilometrage"));
        vitesseColumn.setCellValueFactory(new PropertyValueFactory<>("vitesse"));
        importTXTButton.setOnAction(event -> {
            try {
                voitureService.extractTXT("src/main/resources/inputData.txt");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            refreshTableContent();
        });
        exportTXTButton.setOnAction(event -> {
            try {
                voitureService.writeTXT("src/main/resources/inputData.txt");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            refreshTableContent();
        });
        importExcelButton.setOnAction(event -> {
            try {
                voitureService.extractXLSX("src/main/resources/inputDataExcel.xlsx");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            refreshTableContent();
        });
        exportExcelButton.setOnAction(event -> {
            try {
                voitureService.writeXLSX("src/main/resources/inputDataExcel.xlsx");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            refreshTableContent();
        });

        updateColumn.setCellFactory(param -> new TableCell<>() {
            private final Button updateButton = new Button("Update");

            {
                updateButton.setOnAction(event -> {
                    Voiture voiture = getTableRow().getItem();
                    System.out.println(voiture);
                    if (voiture != null) {
                        openUpdateDialog(voiture.getMatricule());
                    }
                });
            }

            @Override
            protected void updateItem(Voiture item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(updateButton);
                }
            }
        });

        deleteColumn.setCellFactory(param -> new TableCell<>() {
            private final Button deleteButton = new Button("Delete");

            {
                deleteButton.setOnAction(event -> {
                    Voiture voiture = getTableRow().getItem();
                    if (voiture != null) {
                        voitureService.remove(voiture.getMatricule());
                        tableView.getItems().remove(voiture);
                    }
                });
            }

            @Override
            protected void updateItem(Voiture item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(deleteButton);
                }
            }
        });

        refreshTableContent();
        addVoitureButton.setOnAction(event -> {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
            try {
                Parent root = loader.load();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();

                refreshTableContent();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
