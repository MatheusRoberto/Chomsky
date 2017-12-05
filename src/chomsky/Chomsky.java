/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chomsky;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author matheus
 */
public class Chomsky {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Read r = new Read();
        GLC glc = new GLC();
        Chms chms = new Chms();
        char car;
        boolean flag;
        ArrayList<Character> simbolos;

        //Adicionar terminais
        JOptionPane.showMessageDialog(null, "Digite os Terminais entre a-z minuscula, USE & como palavra vazia");

        do {
            flag = true;
            car = r.lerCaracter();
            if ((car >= 'a' && car <= 'z') || (car == '&')) {
                for (Character cara : glc.getTerminais()) {
                    if (cara.equals(car)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    glc.getTerminais().add(car);
                } else {
                    JOptionPane.showMessageDialog(null, "Terminal ja existe!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error! Digite os TERMINAIS entre a-z minuscula, 0-9");
            }
        } while (JOptionPane.showConfirmDialog(null, "Deseja adicionar mais Terminais?", "",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        //Adicionar Variaveis
        JOptionPane.showMessageDialog(null, "Digite ás variaveis entre A-Z maiuscula");

        do {
            flag = true;
            car = r.lerCaracter();
            if (car >= 'A' && car <= 'Z') {
                for (Character cara : glc.getVariaveis()) {
                    if (cara.equals(car)) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    glc.getVariaveis().add(car);
                } else {
                    JOptionPane.showMessageDialog(null, "Variavel ja existe!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error! Digite ás variaveis entre A-Z maiuscula");
            }
        } while (JOptionPane.showConfirmDialog(null, "Deseja adicionar mais variaveis?", "",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        //Adicionar Variaveis
        String variaveis = "";
        for (Character cara : glc.getVariaveis()) {
            variaveis = variaveis + " " + cara + " -";
        }

        do {
            JOptionPane.showMessageDialog(null, "Digite ás variaveis Inicial :" + variaveis);
            flag = false;
            car = r.lerCaracter();
            if (car >= 'A' && car <= 'Z') {
                for (Character cara : glc.getVariaveis()) {
                    if (cara.equals(car)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    glc.setInicial(car);
                } else {
                    JOptionPane.showMessageDialog(null, "Variavel NAO existe!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error! Digite ás variaveis entre A-Z maiuscula");
            }
        } while (glc.getInicial().equals('@'));

        String terminais = "";
        for (Character cara : glc.getTerminais()) {
            terminais = terminais + " " + cara + " -";
        }

        do {
            JOptionPane.showMessageDialog(null, "Selecione uma variaveis:" + variaveis);
            flag = false;
            car = r.lerCaracter();
            if (car >= 'A' && car <= 'Z') {
                for (Character cara : glc.getVariaveis()) {
                    if (cara.equals(car)) {
                        flag = true;
                        break;
                    }
                }
                if (flag) {
                    JOptionPane.showMessageDialog(null, "Digite o corpo da variavel: " + variaveis + " ou terminais: " + terminais);
                    String corpo;
                    corpo = r.lerString();
                    simbolos = new ArrayList<>();
                    for (Character terminai : glc.getTerminais()) {
                        simbolos.add(terminai);
                    }
                    for (Character carac : glc.getVariaveis()) {
                        simbolos.add(carac);
                    }
                    for (int i = 0; i < corpo.length(); i++) {
                        String cor = String.valueOf(corpo.charAt(i));
                        for (Character simbolo : simbolos) {
                            String sim = String.valueOf(simbolo);
                            if (cor.equals(sim)) {
                                flag = true;
                                break;
                            } else {
                                flag = false;
                            }
                        }
                    }
                    if (!flag) {
                        JOptionPane.showMessageDialog(null, "Corpo Invalido");
                        break;
                    } else {
                        ArrayList<String> funcoes;
                        if (glc.getPrograma().containsKey(car)) {
                            funcoes = glc.getPrograma().get(car);
                            funcoes.add(corpo);
                        } else {
                            funcoes = new ArrayList<>();
                            funcoes.add(corpo);
                        }
                        glc.getPrograma().put(car, funcoes);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Variavel NAO existe!");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Error! Digite ás variaveis entre A-Z maiuscula");
            }
        } while (JOptionPane.showConfirmDialog(
                null, "Deseja adicionar mais funcoes?", "",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION);

        for (Map.Entry<Character, ArrayList<String>> entry : glc.getPrograma().entrySet()) {
            Object key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            for (String string : value) {
                System.err.println(key + "->" + value);
            }

        }

        if (glc.palavraVazia()) {
            JOptionPane.showMessageDialog(null, "Linguagem não simplificada, palavra vazia");
        }

        if (glc.variavelUnica()) {
            JOptionPane.showMessageDialog(null, "Linguagem não simplificada, Fecho de variavel");
        }

        if (glc.variavelInatigivel()) {
            JOptionPane.showMessageDialog(null, "Linguagem não simplificada, Variavel inatigivel");
        }

        /*
        System.err.println("Gramatica de Entrada: \n");
        glc.getPrograma().entrySet().forEach((entry) -> {
            Character chara = entry.getKey();
            ArrayList<String> value = entry.getValue();
            for (String string : value) {
                System.err.println(chara + " -> " + string);
            }
        });

        System.err.println("---------------------------------------------------------------------------------- \n");
         */
        chms.setPrograma(glc.getPrograma());
        chms.setTerminais(glc.getTerminais());
        chms.setVariaveis(glc.getVariaveis());

        chms.transTam2();

        System.err.println("Parte 2: Gramatica de Normalizada de Chomsky: \n");
        chms.getPrograma().entrySet().forEach((entry) -> {
            Character chara = entry.getKey();
            ArrayList<String> value = entry.getValue();
            for (String string : value) {
                System.err.println(chara + " -> " + string);
            }
        });

        chms.transTam3();

        System.err.println("Parte 3: Gramatica de Normalizada de Chomsky: \n");
        chms.getPrograma().entrySet().forEach((entry) -> {
            Character chara = entry.getKey();
            ArrayList<String> value = entry.getValue();
            for (String string : value) {
                System.err.println(chara + " -> " + string);
            }
        });
    }

}
