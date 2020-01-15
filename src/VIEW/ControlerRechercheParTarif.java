package VIEW;

import CONTROLER.DAOTarif;
import CONTROLER.Recherche;
import MODEL.RelationTarifPlageHoraire;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

public class ControlerRechercheParTarif {

    @FXML   private TextField codeTarif;
    @FXML   private TableView<RelationTarifPlageHoraire> tableView;
    @FXML   private TableColumn<RelationTarifPlageHoraire, Date> dateColumn;
    @FXML   private TableColumn<RelationTarifPlageHoraire, String> debutColumn;
    @FXML   private TableColumn<RelationTarifPlageHoraire, LocalTime> finColumn;
    @FXML   private TableColumn<RelationTarifPlageHoraire, String> consomationColumn;
    @FXML   private TableColumn<RelationTarifPlageHoraire, String> prixColumn;

    @FXML
    public void initialize() throws SQLException {
        DAOTarif daoTarif = new DAOTarif();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH.mm");

        dateColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getPlageHoraire().getDate()));
        debutColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getPlageHoraire().getHeureDebut()));
        finColumn.setCellValueFactory(cellData -> new SimpleObjectProperty(cellData.getValue().getPlageHoraire().getHeureFin()));
        consomationColumn.setCellValueFactory(new PropertyValueFactory<RelationTarifPlageHoraire, String>("consommation"));
        prixColumn.setCellValueFactory(cellData -> new SimpleStringProperty(String.valueOf(cellData.getValue().getConsommation()*cellData.getValue().getTarif().getPrix())));
    }

    public void ListUtiButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListeUtilisateur.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void listCompButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListeCompteur.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void plageConsomationButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListePlageConsomation.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void listTarifButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListeTarifs.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void rechercheTarifButton(ActionEvent actionEvent){
    }

    public void rechercheConsomationButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/RechercheConsomation.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void rechercherButton(ActionEvent actionEvent) {
        if(!codeTarif.getText().isEmpty() && Pattern.matches("[0-9]++$", codeTarif.getText())) {
            codeTarif.setStyle("");
            DAOTarif daoTarif = new DAOTarif();
            tableView.getItems().setAll(Recherche.rechercheParTarif(daoTarif.findNum(codeTarif.getText())));
        }
        else
            codeTarif.setStyle("-fx-border-color: red ;");
    }
}
