package co.uniquindio.edu.application;

import co.uniquindio.edu.controller.Controller;
import co.uniquindio.edu.model.Archivo;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    private Archivo archivo;

    public static void main(String[] args) {
        launch();
    }


    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/Ventana.fxml"));
        Parent root = loader.load();
        Controller controller = loader.getController();
        this.archivo = new Archivo("/Users/alejandroarias/Desktop");
        controller.setArchivo(this.archivo);
        controller.actualizarTabla();
        controller.inicializar();
        Scene scene = new Scene(root);
        stage.setMaxWidth(700);
        stage.setMaxHeight(700);
        stage.setScene(scene);
        stage.show();
    }
}
