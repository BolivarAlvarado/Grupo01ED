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
import main.modelo.ConfiguracionJuego;
import main.modelo.Jugador;
import static util.VentanaUtil.abrirVentana;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class PrincipalController implements Initializable {

    @FXML
    private Button btnJvsJ;
    @FXML
    private Button btnJvsP;
    @FXML
    private Button btnPvsP;
    
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
    private void jugarVsJugador(ActionEvent event) {
        this.j2.setEsPc(false);            // Resetear a jugador normal
        this.j2.setNickname("Jugador 2");  // Nombre estándar
        this.config.setJugador2(j2);
        abrirVentana("Ajustes", event);
    }

    @FXML
    private void jugarVsPC(ActionEvent event) {
        this.j2.setEsPc(true);
        this.j2.setNickname("PC");
        this.config.setJugador2(j2);
        abrirVentana("Ajustes", event);
    }


    @FXML
    private void jugarPCvsPC(ActionEvent event) {
        this.j2.setNickname("PC 2");
        this.j2.setEsPc(true);
        this.config.setJugador2(j2);
        
        this.j1.setNickname("PC 1");
        this.j1.setEsPc(true);
        this.config.setJugador2(j1);
        abrirVentana("Ajustes", event);
    }
}
