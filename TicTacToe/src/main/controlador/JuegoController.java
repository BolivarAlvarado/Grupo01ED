/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import main.modelo.ConfiguracionJuego;
import main.modelo.Jugador;
import static util.VentanaUtil.abrirVentana;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class JuegoController implements Initializable {

    @FXML
    private Button btnRegresar;
    @FXML
    private Label lblTiempo;
    @FXML
    private Label lblJugador;

    private ConfiguracionJuego config;
    private Jugador j1;
    private Jugador j2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        config = ConfiguracionJuego.getInstancia();
        j1 = config.getJugador1();
        j2 = config.getJugador2();
    }

    @FXML
    private void regresar(ActionEvent event) {
        abrirVentana("Ajustes", event);
    }

}
