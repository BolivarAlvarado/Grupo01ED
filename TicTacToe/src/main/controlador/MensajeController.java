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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import main.modelo.Jugador;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class MensajeController implements Initializable {

    @FXML
    private ImageView img;
    @FXML
    private Label lblMensaje;

    private Jugador ganador;
    private Jugador perdedor;
    private boolean esEmpate;
    @FXML
    private BorderPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO

    }

    public void recibirParametros(Jugador ganador, Jugador perdedor, boolean estado) {
        this.ganador = ganador;
        this.perdedor = perdedor;
        this.esEmpate = estado;
        mostrar();
    }

    private void mostrar() {    
    rootPane.getStyleClass().removeAll("win-message", "lose-message", "empate-window");

    if (esEmpate) {
        rootPane.getStyleClass().add("empate-window");
        lblMensaje.setText("Ha sido un empate");
        img.setImage(new Image(getClass().getResource("../../recursos/img/Empate.png").toExternalForm()));
    } else {
        if (ganador.isEsPc() && !perdedor.isEsPc()) {
            rootPane.getStyleClass().add("lose-message");
            lblMensaje.setText("Perdiste " + perdedor.getNickname() + ", ganó la computadora " + ganador.getNickname());
            img.setImage(new Image(getClass().getResource("../../recursos/img/Perder.png").toExternalForm()));
        } else if (!ganador.isEsPc()) {
            rootPane.getStyleClass().add("win-message");
            lblMensaje.setText("HAS GANADO " + ganador.getNickname());
            img.setImage(new Image(getClass().getResource("../../recursos/img/Trofeo.png").toExternalForm()));
        } else if (ganador.isEsPc() && perdedor.isEsPc()) {
            rootPane.getStyleClass().add("win-message");
            lblMensaje.setText("Ha ganado " + ganador.getNickname());
            img.setImage(new Image(getClass().getResource("../../recursos/img/Trofeo.png").toExternalForm()));
        }
    }
}


    @FXML
    private void cerrar(ActionEvent event) {
        Stage stage = (Stage) img.getScene().getWindow();
        stage.close();
    }
}
