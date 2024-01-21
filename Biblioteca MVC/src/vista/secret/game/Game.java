/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista.secret.game;


public class Game {
    static MainWindow mw;
    public static void launch() {
        mw = new MainWindow();
    }
    
    public static void gameOver(String winner) {
        mw.dispose();
    }
}
