/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package auto.chincho;

import chincho.merinde.*;

public class Main {
    public static void main(String[] args) {
        System.out.println("Programa de Mati");
        new MainWindow();
    }
    
    public static void gameOver(String winner) {
        System.out.println(winner + " Ganó");
        System.exit(0);
    }
}
