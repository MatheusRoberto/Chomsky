/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chomsky;

import javax.swing.JOptionPane;

/**
 *
 * @author matheus
 */
public class Read {

    public char lerCaracter() {
        String caracter;
        while (true) {
            caracter = JOptionPane.showInputDialog(null, "Digite um caracter: ");
            if (!caracter.isEmpty() && caracter.length() < 2) {
                break;
            }
        }
        return caracter.charAt(0);
    }
    
    public String lerString(){
        String s;
        while (true) {            
            s = JOptionPane.showInputDialog(null, "Digite o corpo: ");
            if(!s.isEmpty())
                break;
        }
        return s;
    }

}
