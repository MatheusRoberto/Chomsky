/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chomsky;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author matheus
 */
public class GLC {

    private Character inicial;
    private ArrayList<Character> terminais;
    private ArrayList<Character> variaveis;
    private Map<Character, ArrayList<String>> programa;

    public GLC() {
        terminais = new ArrayList<>();
        variaveis = new ArrayList<>();
        programa = new HashMap<>();
        inicial = '@';
    }

    public boolean palavraVazia() {
        for (Map.Entry<Character, ArrayList<String>> entry : programa.entrySet()) {
            ArrayList<String> value = entry.getValue();
            for (String string : value) {
                if (string.contains("&")) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean variavelUnica() {
        for (Map.Entry<Character, ArrayList<String>> entry : programa.entrySet()) {
            ArrayList<String> value = entry.getValue();
            for (String string : value) {
                if (string.length() == 1) {
                    for (Character variavei : variaveis) {
                        String var = String.valueOf(variavei);
                        if (string.equals(var)) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    public boolean variavelInatigivel() {
        Map<Character, Boolean> atingivel = new HashMap<>();
        programa.entrySet().stream().map((entry) -> entry.getKey()).forEachOrdered((key) -> {
            atingivel.put(key, Boolean.FALSE);
        });

        atingivel.put(getInicial(), Boolean.TRUE);

        for (Map.Entry<Character, ArrayList<String>> entry : programa.entrySet()) {
            Character key = entry.getKey();
            ArrayList<String> value = entry.getValue();
            value.forEach((string) -> {
                for (int i = 0; i < string.length(); i++) {
                    String s = String.valueOf(string.charAt(i));
                    for (Map.Entry<Character, ArrayList<String>> entrya : programa.entrySet()) {
                        Character keya = entrya.getKey();
                        String k = String.valueOf(keya);  
                        if (s.equals(k)) {
                            atingivel.put(keya, Boolean.TRUE);
                        } else if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z' && !programa.containsKey(string.charAt(i))) {
                            atingivel.put(string.charAt(i), Boolean.FALSE);
                        }
                    }
                }
            });
        }
        /*ArrayList<String> value = programa.get(inicial);
        value.forEach((string) -> {
            for (int i = 0; i < string.length(); i++) {
                String s = String.valueOf(string.charAt(i));
                for (Map.Entry<Character, ArrayList<String>> entry : programa.entrySet()) {
                    Character key = entry.getKey();
                    String k = String.valueOf(key);
                    if (s.equals(k)) {
                        atingivel.put(key, Boolean.TRUE);
                    } else if (string.charAt(i) >= 'A' && string.charAt(i) <= 'Z' && !programa.containsKey(string.charAt(i))) {
                        atingivel.put(string.charAt(i), Boolean.FALSE);
                    }
                }
            }
        });*/

        for (Map.Entry<Character, Boolean> entry : atingivel.entrySet()) {
            Character key = entry.getKey();
            Boolean valuea = entry.getValue();
            if (!valuea) {
                return true;
            }
        }

        return false;
    }

    /**
     * @return the inicial
     */
    public Character getInicial() {
        return inicial;
    }

    /**
     * @param inicial the inicial to set
     */
    public void setInicial(Character inicial) {
        this.inicial = inicial;
    }

    /**
     * @return the terminais
     */
    public ArrayList<Character> getTerminais() {
        return terminais;
    }

    /**
     * @param terminais the terminais to set
     */
    public void setTerminais(ArrayList<Character> terminais) {
        this.terminais = terminais;
    }

    /**
     * @return the variaveis
     */
    public ArrayList<Character> getVariaveis() {
        return variaveis;
    }

    /**
     * @param variaveis the variaveis to set
     */
    public void setVariaveis(ArrayList<Character> variaveis) {
        this.variaveis = variaveis;
    }

    /**
     * @return the programa
     */
    public Map<Character, ArrayList<String>> getPrograma() {
        return programa;
    }

    /**
     * @param programa the programa to set
     */
    public void setPrograma(Map<Character, ArrayList<String>> programa) {
        this.programa = programa;
    }

}
