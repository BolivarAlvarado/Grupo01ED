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
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import main.modelo.ConfiguracionJuego;
import main.modelo.Jugador;
import static util.VentanaUtil.abrirVentana;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class AjustesController implements Initializable {

    @FXML
    private Button btnJugador1;
    @FXML
    private Button btnJugador2;
    @FXML
    private ToggleGroup tglSimbolo1;
    @FXML
    private ToggleGroup tglSimbolo2;
    @FXML
    private RadioButton X1;
    @FXML
    private RadioButton O1;
    @FXML
    private RadioButton X2;
    @FXML
    private RadioButton O2;

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
        configInicial();
    }

    private void configInicial() {
        // Configuración de quién inicia
        if (config.getTurnoInicial() == 1) {
            btnJugador1.setDisable(true);
            btnJugador2.setDisable(false);
        } else {
            btnJugador1.setDisable(false);
            btnJugador2.setDisable(true);
        }
        System.out.println(config.getJugador1());

        // Configuración de símbolos según jugador 1
        if (j1.getCaracter() == 'X') {
            X1.setSelected(true);
            O2.setSelected(true);
        } else {
            O1.setSelected(true);
            X2.setSelected(true);
        }
    }

    @FXML
    private void regresar(ActionEvent event) {
        abrirVentana("Principal", event);
    }

    @FXML
    private void cambiarJugador(ActionEvent event) {
        if (event.getSource() == btnJugador1) {
            config.setTurnoInicial(1);
            btnJugador1.setDisable(true);
            btnJugador2.setDisable(false);
        } else {
            config.setTurnoInicial(2);
            btnJugador1.setDisable(false);
            btnJugador2.setDisable(true);
        }
    }

    private void actualizarConfiguracion() {
        config.setJugador1(j1);
        config.setJugador2(j2);
    }

    @FXML
    private void actualizarSimbolo(ActionEvent event) {
        if (X1.isSelected()) {
            j1.setCaracter('X');
            j2.setCaracter('O');
            O2.setSelected(true);
        } else {
            j2.setCaracter('X');
            j1.setCaracter('O');
            X2.setSelected(true);
        }
        if (X2.isSelected()) {
            j2.setCaracter('X');
            j1.setCaracter('O');
            O1.setSelected(true);
        } else {
            j1.setCaracter('X');
            j2.setCaracter('O');
            X1.setSelected(true);
        }
        actualizarConfiguracion();
    }
}
