package VIEW;

import CONTROLER.DAOTarif;
import MODEL.CategoriesTarif;
import MODEL.Tarif;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.Optional;

public class ControlerListeTarifs {

    @FXML   private TableView<Tarif> tableView;
    @FXML   private TableColumn<Tarif,String> idColumn;
    @FXML   private TableColumn<Tarif,String> codeColumn;
    @FXML   private TableColumn<Tarif,String> prixColumn;
    @FXML   private TableColumn<Tarif, CategoriesTarif> categorieColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Tarif, String>("ID"));
        codeColumn.setCellValueFactory(new PropertyValueFactory<Tarif, String>("code"));
        prixColumn.setCellValueFactory(new PropertyValueFactory<Tarif, String>("prix"));
        categorieColumn.setCellValueFactory(new PropertyValueFactory<Tarif, CategoriesTarif>("categoriesTarif"));

        DAOTarif daoTarif = new DAOTarif();
        tableView.getItems().setAll(daoTarif.getAll());
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

    public void listTarifButton(ActionEvent actionEvent){
    }

    public void rechercheConsomationButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/RechercheConsomation.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void ajouterButton(ActionEvent actionEvent) {
        Parent root;
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/AjouterTarif.fxml").toURI().toURL());
            root = fxmlLoader.load();
            stage.setTitle("Ajouter un tarif");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        stage.setOnHiding(event -> {
            DAOTarif daoTarif = new DAOTarif();
            tableView.getItems().setAll(daoTarif.getAll());
        });
    }

    public void modifierButton(ActionEvent actionEvent) {
        ControlerModifierTarif.tarifSelectionne = tableView.getSelectionModel().getSelectedItem();
        Parent root;
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ModifierTarif.fxml").toURI().toURL());
            root = fxmlLoader.load();
            stage.setTitle("Modifier un tarif");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        stage.setOnHiding(event -> {
            DAOTarif daoTarif = new DAOTarif();
            tableView.getItems().setAll(daoTarif.getAll());
        });
    }

    public void supprimerButton(ActionEvent actionEvent) {
        Tarif tarifASupprimer = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes-vous sur de vouloir supprimer le tarif : " + tarifASupprimer.getCode() + " ?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            DAOTarif daoTarif = new DAOTarif();
            daoTarif.delete(tarifASupprimer);
            tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        }
    }

    public void rechercheTarifButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/RechercheParTarif.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }
}
