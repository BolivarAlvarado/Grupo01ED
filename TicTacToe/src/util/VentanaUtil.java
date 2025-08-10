/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package util;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

}
