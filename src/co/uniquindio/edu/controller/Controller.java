package co.uniquindio.edu.controller;


import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import co.uniquindio.edu.model.Archivo;
import co.uniquindio.edu.model.MyFile;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import lombok.Data;

@Data
public class Controller {

    private Archivo archivo;

    private ObservableList<MyFile> objetos = FXCollections.observableArrayList();

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button btnBajarNivel;

    @FXML
    private Button btnIrARuta;

    @FXML
    private Button btnRaiz;

    @FXML
    private Button btnSubirNivel;

    @FXML
    private TableColumn<MyFile, String> colObjetos;

    @FXML
    private TableView<MyFile> tblDirectorio;

    @FXML
    private TextField txtRuta;

    @FXML
    void bajarNivel(ActionEvent event) {
        MyFile fileSelected = tblDirectorio.getSelectionModel().getSelectedItem();
        String name = "/"+fileSelected.getName();
        if(archivo.verificarDirectory(name)){
            archivo.cambiarRuta(archivo.obtenerRuta()+name);
            actualizarTabla();
        }else {
            mostrarAlert("No te puedes mover a algo que no sea un directorio");
        }
    }

    @FXML
    void irARuta(ActionEvent event) {
        archivo.cambiarRuta(txtRuta.getText());
        txtRuta.setText("");
        actualizarTabla();
    }

    @FXML
    void irRaiz(ActionEvent event) {
        archivo.cambiarRuta("/");
        actualizarTabla();
    }

    @FXML
    void subirNivel(ActionEvent event) {

        String ruta = archivo.obtenerRuta();

        if(ruta.equals("/")){
            mostrarAlert("No puedes subir mas de la raiz");
        }else{
            boolean flag = true;
            char[] rutaAux = ruta.toCharArray();
            for (int i = ruta.length()-1; 0 < i && flag ; i--) {
                if (rutaAux[i] == '/') {
                    flag =false;
                }else {
                    rutaAux[i] = ' ';
                }
            }

            String rutaAuxiliar = "";
            for (char aux: rutaAux) {
                if(aux != ' ')rutaAuxiliar += aux;
            }

            archivo.cambiarRuta(rutaAuxiliar);

            actualizarTabla();
        }



    }

    @FXML
    void initialize() {

    colObjetos.setCellValueFactory(new PropertyValueFactory<MyFile, String>("name"));

    }

    public void actualizarTabla(){
        objetos.clear();
        objetos.addAll(archivo.getDirectories());
        tblDirectorio.getItems().clear();
        tblDirectorio.getItems().addAll(objetos);
        tblDirectorio.refresh();
    }


    public void imprimirObjeto(){
        MyFile objeto = tblDirectorio.getSelectionModel().getSelectedItem();
        mostrarAlert(objeto.getName());
        actualizarTabla();
    }


    @FXML
    private void mostrarAlert(String mensaje) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Info");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }

    public void inicializar() {
        archivo.setController(this);
    }
}