/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
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
    @FXML
    private TextField txtJugador1;
    @FXML
    private TextField txtJugador2;

    private ConfiguracionJuego config;
    private Jugador j1;
    private Jugador j2;
    @FXML
    private Label lblJugado2;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        resetUI();
    }

    private void configInicial() {
        // Configuración inicial de turnos
        if (config.getTurnoInicial() == 1) {
            btnJugador1.setDisable(true);
            btnJugador2.setDisable(false);
            this.j1.setJugando(true);
            this.j2.setJugando(false);
        } else {
            btnJugador1.setDisable(false);
            btnJugador2.setDisable(true);
            this.j1.setJugando(false);
            this.j2.setJugando(true);
        }

        // Configuración símbolos
        if (j1.getCaracter() == 'X') {
            X1.setSelected(true);
            O2.setSelected(true);
        } else {
            O1.setSelected(true);
            X2.setSelected(true);
        }

        // Si es PC, bloqueo el campo y pongo el nombre automáticamente
        if (j2.isEsPc()) {
            txtJugador2.setText("PC");
            txtJugador2.setDisable(true);
        }
        if (j1.isEsPc()) {
            txtJugador1.setText("PC");
            txtJugador1.setDisable(true);
        }

        Platform.runLater(() -> txtJugador1.requestFocus());
    }

    private void actualizarConfiguracion() {
        // Jugador 1
        if (txtJugador1.getText().isBlank()) {
            j1.setNickname(j1.isEsPc() ? "PC" : "Jugador 1");
        } else {
            j1.setNickname(txtJugador1.getText());
        }

        // Jugador 2
        if (txtJugador2.getText().isBlank()) {
            j2.setNickname(j2.isEsPc() ? "PC" : "Jugador 2");
        } else {
            j2.setNickname(txtJugador2.getText());
        }

        config.setJugador1(j1);
        config.setJugador2(j2);
    }


    @FXML
    private void regresar(ActionEvent event) {
        actualizarConfiguracion();
        abrirVentana("Principal", event);
    }

    @FXML
    private void cambiarJugador(ActionEvent event) {
        if (event.getSource() == btnJugador1) {
            config.setTurnoInicial(1);
            btnJugador1.setDisable(true);
            btnJugador2.setDisable(false);
            this.j1.setJugando(true);
            this.j2.setJugando(false);           
        } else {
            config.setTurnoInicial(2);
            btnJugador1.setDisable(false);
            btnJugador2.setDisable(true);
            this.j2.setJugando(true);
            this.j1.setJugando(false);            
        }
    }
    
    public void resetUI() {
        config = ConfiguracionJuego.getInstancia();
        j1 = config.getJugador1();
        j2 = config.getJugador2();

        btnJugador1.setDisable(false);
        btnJugador2.setDisable(false);
        txtJugador1.setDisable(false);
        txtJugador2.setDisable(false);

        configInicial();
    }



    @FXML
    private void actualizarSimbolo(ActionEvent event) {
        Object source = event.getSource();

        if (source == X1) {
            j1.setCaracter('X');
            j2.setCaracter('O');
            O2.setSelected(true);
        } else if (source == O1) {
            j1.setCaracter('O');
            j2.setCaracter('X');
            X2.setSelected(true);
        } else if (source == X2) {
            j2.setCaracter('X');
            j1.setCaracter('O');
            O1.setSelected(true);
        } else if (source == O2) {
            j2.setCaracter('O');
            j1.setCaracter('X');
            X1.setSelected(true);
        }
        actualizarConfiguracion();
    }

    @FXML
    private void avanzar(ActionEvent event) {
        actualizarConfiguracion();
        abrirVentana("Juego", event);
    }
}
