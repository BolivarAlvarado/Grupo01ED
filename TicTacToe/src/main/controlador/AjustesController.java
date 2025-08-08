/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.controlador;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author Michelle
 */
public class AjustesController implements Initializable {

    @FXML
    private ToggleButton btnX;
    @FXML
    private ToggleButton btnO;
    @FXML
    private ToggleButton btnJugador1;
    @FXML
    private ToggleButton btnJugador2;
    @FXML
    private ToggleGroup tglJugador;
    @FXML
    private ToggleGroup tglSimbolo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
