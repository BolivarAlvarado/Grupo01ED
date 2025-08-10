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
import javafx.scene.layout.GridPane;
import main.modelo.ConfiguracionJuego;
import main.modelo.Jugador;
import main.modelo.Tablero;
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
    @FXML
    private GridPane gridTablero;

    private ConfiguracionJuego config;
    private Jugador j1;
    private Jugador j2;
    private Tablero tablero;
    private Button[][] btnCeldas;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        config = ConfiguracionJuego.getInstancia();
        j1 = config.getJugador1();
        j2 = config.getJugador2();
        tablero = new Tablero();
        cargarConfiguraciones();
    }

    private void cargarConfiguraciones() {
        actualizarLablelJugador();
        configTablero();
    }

    @FXML
    private void regresar(ActionEvent event) {
        abrirVentana("Ajustes", event);
    }    

    private void configTablero() {
        btnCeldas = new Button[Tablero.SIZE][Tablero.SIZE];
        for (int fila = 0; fila < Tablero.SIZE; fila++) {
            for (int col = 0; col < Tablero.SIZE; col++) {
                Button celda = new Button();
                celda.getStyleClass().add("cell-button");
                btnCeldas[fila][col] = celda;
                int f = fila, c = col;
                gridTablero.add(celda, col, fila);
                celda.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
                celda.setOnAction(e -> jugar(f, c));
            }
        }
    }

    private Button obtenerBotonPorCoordenadas(int fila, int columna) {
        return btnCeldas[fila][columna];
    }

    private void jugar(int fila, int columna) {
        Button boton = obtenerBotonPorCoordenadas(fila, columna);

        if (boton.getGraphic() == null) { 
            String imagen;
            if (j1.isJugando()) { 
                tablero.colocarMarca(fila, columna, j1.getCaracter());
                imagen = "recursos/img/" + j1.getCaracter() + ".png";
                cambiarTurno();
            } else {
                tablero.colocarMarca(fila, columna, j2.getCaracter());
                imagen = "recursos/img/" + j2.getCaracter() + ".png";
                cambiarTurno();
            }

            boton.setStyle(
                    "-fx-background-image: url('" + imagen + "');"
                    + "-fx-background-size: cover;"
                    + "-fx-background-position: center;"
                    + "-fx-background-repeat: no-repeat;"
                    + "-fx-background-size: 80% 80%;"
            );
            
            boton.setDisable(true);
        }
    }

    private void cambiarTurno() {
        if (!tablero.estaLleno()) {
            if (j1.isJugando()) {
                j1.setJugando(false);
                j2.setJugando(true);
                actualizarLablelJugador();
            } else {
                j2.setJugando(false);
                j1.setJugando(true);
                actualizarLablelJugador();
            }
        }

    }

    private void actualizarLablelJugador() {
        if (j1.isJugando()) {
            lblJugador.setText(j1.getNickname() + "(" + j1.getCaracter() + ")");
        } else {
            lblJugador.setText(j2.getNickname() + "(" + j2.getCaracter() + ")");
        }
    }
}
