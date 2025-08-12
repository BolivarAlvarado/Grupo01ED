/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package main.controlador;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.stage.Stage;
import main.App;
import main.modelo.ConfiguracionJuego;
import main.modelo.Jugador;
import main.modelo.Tablero;
import util.VentanaUtil;
import static util.VentanaUtil.abrirVentana;
import main.modelo.Minimax;
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
    @FXML
    private StackPane stackTablero;

    private ConfiguracionJuego config;
    private Jugador j1;
    private Jugador j2;
    private Tablero tablero;
    private Button[][] btnCeldas;
    private boolean hayGanador;
    private Pane panelLinea;
    private ScheduledExecutorService scheduler;
    private int segundosTranscurridos;
    private Minimax minimaxIA;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO        
        cargarConfiguraciones();
        Platform.runLater(() -> {
            Stage stage = (Stage) gridTablero.getScene().getWindow();
            stage.setOnCloseRequest(event -> {
                stop();
            });
        });
    }

    private void cargarConfiguraciones() {
        stop();
        empezarCronometro();
        config = ConfiguracionJuego.getInstancia();
        j1 = config.getJugador1();
        j2 = config.getJugador2();
        tablero = new Tablero();
        this.hayGanador = false;
        if (config.getTurnoInicial() == 1) {
            this.j1.setJugando(true);
            this.j2.setJugando(false);
        } else {
            this.j1.setJugando(false);
            this.j2.setJugando(true);
        }
            if (j2.isEsPc()) {
            minimaxIA = new Minimax(j2.getCaracter(), j1.getCaracter());
        } else {
            minimaxIA = null;
        }
        // Si inicia PC, hacer que juegue ya
        if (j2.isEsPc() && j2.isJugando()) {
            jugarTurnoPCConRetraso();
        }
        limpiarLineas();
        actualizarLablelJugador();
        configTablero();
    }

    @FXML
    private void regresar(ActionEvent event) {
        if (hayGanador || this.tablero.estaLleno()) {
            abrirVentana("Principal", event);
        } else {
            abrirVentana("Ajustes", event);
        }
        stop();
    }

    private void stop() {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
    }

    private void configTablero() {
        this.gridTablero.setDisable(false);
        this.gridTablero.getChildren().clear();
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

        if (boton.getGraphic() == null && !hayGanador && tablero.getCasilla(fila, columna) == ' ') {
            Jugador jugadorActual = j1.isJugando() ? j1 : j2;
            validarJuego(fila, columna, jugadorActual);

            String imagen = "recursos/img/" + jugadorActual.getCaracter() + ".png";
            boton.setStyle(
                "-fx-background-image: url('" + imagen + "');" +
                "-fx-background-size: cover;" +
                "-fx-background-position: center;" +
                "-fx-background-repeat: no-repeat;" +
                "-fx-background-size: 80% 80%;"
            );
            boton.setDisable(true);

            if (!hayGanador && !tablero.estaLleno()) {
                if (jugadorActual == j1) {
                    cambiarTurno();
                    if (j2.isEsPc() && j2.isJugando()) {
                        jugarTurnoPCConRetraso();
                    }
                } else {
                    cambiarTurno();
                }
            }
        }
    }

    private void cambiarTurno() {
        if (!tablero.estaLleno() && !hayGanador) {
            if (j1.isJugando()) {
                j1.setJugando(false);
                j2.setJugando(true);
            } else {
                j2.setJugando(false);
                j1.setJugando(true);
            }
            actualizarLablelJugador();
        }
    }
    
    private void jugarTurnoPCConRetraso() {
        gridTablero.setDisable(true);
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        executor.schedule(() -> {
            Platform.runLater(() -> {
                jugarTurnoPC();
                executor.shutdown();
            });
        }, 500, TimeUnit.MILLISECONDS);
    }

    private void jugarTurnoPC() {
        if (minimaxIA == null || hayGanador || tablero.estaLleno()) return;

        int[] movimiento = minimaxIA.mejorMovimiento(tablero);
        int fila = movimiento[0];
        int columna = movimiento[1];

        if (fila >= 0 && columna >= 0) {
            jugar(fila, columna);
        }

        // Habilitar el tablero si el juego sigue
        if (!hayGanador && !tablero.estaLleno()) {
            gridTablero.setDisable(false);
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
        int[][] casillasGanadoras = tablero.revisarTablero();
        hayGanador = casillasGanadoras != null;
        this.gridTablero.setDisable(hayGanador);
        if (hayGanador) {
            resaltarLineaGanadora(casillasGanadoras);

            if (j1.isJugando()) {
                mostrarMensaje(j1, j2, false);
            } else if (j2.isJugando()) {
                mostrarMensaje(j2, j1, false);
            }
        } else if (tablero.estaLleno()) {
            mostrarMensaje(j1, j2, true);
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
            stage.setOnCloseRequest(event -> {
                if (gridTablero.getScene() == null || gridTablero.getScene().getWindow() == null || !gridTablero.getScene().getWindow().isShowing()) {
                    stop();
                }
            });
            stage.show();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void reiniciarPartida(ActionEvent event) {
        if (!this.tablero.estaLleno() && !this.hayGanador) {
            boolean conf = VentanaUtil.mostrarAlertaConfirmacion("Esta seguro de reiniciar la partida?", "Al reiniciar la partida no se guardaran los cambios");
            if (conf) {
                cargarConfiguraciones();
            } else {
                VentanaUtil.mostrarAlertaInformacion("No se reinicio la partida", "Puede seguir jugando");
            }
        }
        if (this.hayGanador) {
            cargarConfiguraciones();
        }
    }

    private void resaltarLineaGanadora(int[][] casillasGanadoras) {
        if (panelLinea != null) {
            stackTablero.getChildren().remove(panelLinea);
        }

        Line lineaGanadora = new Line();

        double gridWidth = gridTablero.getWidth();
        double gridHeight = gridTablero.getHeight();

        double cellWidth = gridWidth / Tablero.SIZE;
        double cellHeight = gridHeight / Tablero.SIZE;

        // Coordenadas de las casillas ganadoras
        int fila1 = casillasGanadoras[0][0];
        int col1 = casillasGanadoras[0][1];
        int fila2 = casillasGanadoras[casillasGanadoras.length - 1][0];
        int col2 = casillasGanadoras[casillasGanadoras.length - 1][1];

        // Calculando las posiciones correctas para la línea
        double x1 = col1 * cellWidth + (cellWidth / 2);
        double y1 = fila1 * cellHeight + (cellHeight / 2);
        double x2 = col2 * cellWidth + (cellWidth / 2);
        double y2 = fila2 * cellHeight + (cellHeight / 2);

        lineaGanadora.setStartX(x1);
        lineaGanadora.setStartY(y1);
        lineaGanadora.setEndX(x2);
        lineaGanadora.setEndY(y2);

        lineaGanadora.setStrokeWidth(5);
        lineaGanadora.setStroke(Color.RED);

        panelLinea = new Pane();
        panelLinea.getChildren().add(lineaGanadora);
        stackTablero.getChildren().add(panelLinea);
    }

    private void limpiarLineas() {
        if (panelLinea != null) {
            stackTablero.getChildren().remove(panelLinea);
            panelLinea = null;
        }
    }

    private void empezarCronometro() {
        if (scheduler != null) {
            scheduler.shutdownNow();
        }
        segundosTranscurridos = 0;
        scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.scheduleAtFixedRate(() -> {
            if (!hayGanador && !tablero.estaLleno()) {
                segundosTranscurridos++;
                long segundos = segundosTranscurridos % 60;
                long minutos = (segundosTranscurridos / 60) % 60;
                long horas = (segundosTranscurridos / 3600) % 24;
                long dias = segundosTranscurridos / (3600 * 24);
                String tiempoFormateado;
                if (dias > 0) {
                    tiempoFormateado = String.format("%dd %02d:%02d:%02d", dias, horas, minutos, segundos);
                } else {
                    tiempoFormateado = String.format("%02d:%02d:%02d", horas, minutos, segundos);
                }
                Platform.runLater(() -> lblTiempo.setText(tiempoFormateado));
            } else {
                scheduler.shutdown();
            }
        }, 0, 1, TimeUnit.SECONDS);
    }
}
