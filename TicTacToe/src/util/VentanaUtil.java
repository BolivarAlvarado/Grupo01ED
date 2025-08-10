/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;
import main.App;
import main.controlador.PrincipalController;

/**
 *
 * @author Michelle
 */
public class VentanaUtil {

    public static void abrirVentana(String nombre, ActionEvent event) {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("vista/" + nombre + ".fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load());
            Stage newStage = new Stage();
            newStage.setScene(scene);
            newStage.setResizable(false);
            newStage.show();

            // Cerrar la ventana actual
            Stage currentStage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            currentStage.close();

        } catch (IOException ex) {
            System.getLogger(PrincipalController.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }

    public static boolean mostrarAlertaConfirmacion(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle(titulo);
        alert.setHeaderText("Confirmar");
        alert.setContentText(mensaje);

        ButtonType resultado = alert.showAndWait().orElse(ButtonType.CANCEL);

        return resultado == ButtonType.OK;
    }

    public static void mostrarAlertaInformacion(String titulo, String mensaje) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(titulo);
        alert.setHeaderText("Informacion");
        alert.setContentText(mensaje);
        alert.showAndWait();
    }
}
