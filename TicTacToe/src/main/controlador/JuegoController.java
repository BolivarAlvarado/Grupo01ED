/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import main.App;
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
    private boolean hayGanador;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        cargarConfiguraciones();
    }

    private void cargarConfiguraciones() {
        config = ConfiguracionJuego.getInstancia();
        j1 = config.getJugador1();
        j2 = config.getJugador2();
        tablero = new Tablero();
        this.hayGanador = false;
        actualizarLablelJugador();
        configTablero();
    }

    @FXML
    private void regresar(ActionEvent event) {
        if(hayGanador || this.tablero.estaLleno()){ 
           abrirVentana("Principal", event); 
        }else 
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
                validarJuego(fila, columna, j1);
                imagen = "recursos/img/" + j1.getCaracter() + ".png";
                cambiarTurno();
            } else {
                validarJuego(fila, columna, j2);
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
        if (!tablero.estaLleno() && !this.hayGanador) {
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

    private void validarJuego(int fila, int columna, Jugador j) {
        tablero.colocarMarca(fila, columna, j.getCaracter());
        hayGanador = tablero.revisarTablero();
        this.gridTablero.setDisable(hayGanador);
        if (hayGanador) {            
            if(j1.isJugando())
               mostrarMensaje(j1,j2,false);
            else if(j2.isJugando())
                mostrarMensaje(j2,j1,false);
        }else if(tablero.estaLleno()){ 
            mostrarMensaje(j1,j2,true);
        }
    }

    private void mostrarMensaje(Jugador ganador, Jugador perdedor, boolean esEmpate) {
        try {
            FXMLLoader loader = new FXMLLoader(App.class.getResource("vista/Mensaje.fxml"));
            Parent root = loader.load();
            MensajeController mensajeController = loader.getController();
            mensajeController.recibirParametros(ganador, perdedor, esEmpate);
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("Resultado");
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reiniciarPartida(ActionEvent event) {
    }
}
