/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package estructuras.arbol;

import java.util.ArrayList;
import java.util.List;
import main.modelo.Tablero;

/**
 *
 * @author Bolivar
 */
public class Nodo {
    
        public Tablero estado;
        public int filaMovimiento;    // Movimiento que llevó a este estado
        public int colMovimiento;
        public List<Nodo> hijos;
        public int valorMinimax;

        public Nodo(Tablero estado, int filaMovimiento, int colMovimiento) {
            this.estado = estado;
            this.filaMovimiento = filaMovimiento;
            this.colMovimiento = colMovimiento;
            this.hijos = new ArrayList<>();
            this.valorMinimax = 0;
        }

        public void agregarHijo(Nodo hijo) {
            hijos.add(hijo);
        }
}
