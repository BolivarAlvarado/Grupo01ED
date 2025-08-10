/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main.modelo;

/**
 *
 * @author Michelle
 */
public class ConfiguracionJuego {

    private static ConfiguracionJuego instancia;

    private Jugador jugador1;
    private Jugador jugador2;

    private int turnoInicial;
    

    private ConfiguracionJuego() {
        turnoInicial = 1;
        jugador1 = new Jugador("Jugador 1", 'X');
        jugador2 = new Jugador("Jugador 2", 'O');
    }

    public static ConfiguracionJuego getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracionJuego();
        }
        return instancia;
    }

    public Jugador getJugador1() {
        return jugador1;
    }

    public Jugador getJugador2() {
        return jugador2;
    }

    public void setJugador1(Jugador jugador1) {
        this.jugador1 = jugador1;
    }

    public void setJugador2(Jugador jugador2) {
        this.jugador2 = jugador2;
    }

    public int getTurnoInicial() {
        return turnoInicial;
    }

    public void setTurnoInicial(int turnoInicial) {
        this.turnoInicial = turnoInicial;
    }
}